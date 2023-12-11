package net.antonyho.musify;

import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;
import net.antonyho.musify.musicbrainz.Artist;
import net.antonyho.musify.musicbrainz.MusicBrainzApiClient;
import net.antonyho.musify.musicbrainz.ReleaseGroup;
import net.antonyho.musify.wikipedia.WikipediaApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.wikidata.wdtk.datamodel.interfaces.EntityDocument;
import org.wikidata.wdtk.datamodel.interfaces.ItemDocument;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ArtistDetailsService {

    private static final Logger log = LoggerFactory.getLogger(ArtistDetailsService.class);

    private static final MusicBrainzApiClient musicBrainzApiClient = MusicBrainzApiClient.getInstance();

    private static final WikibaseDataFetcher wikidataFetcher = WikibaseDataFetcher.getWikidataDataFetcher();

    private static final WikipediaApiClient wikipediaApiClient = WikipediaApiClient.getInstance();

    private static final String WIKISITELINK = "enwiki";

    public ArtistDetails getArtist(String mbid) {
        Artist artist;
        try {
            artist = musicBrainzApiClient.getArtist(mbid);
        } catch (HttpClientErrorException e) {
            log.error("MusicBrainz API call on [ID: {}]", mbid, e);
            throw e;
        }

        ArtistDetails details = new ArtistDetails(
                artist.getId(),
                artist.getName(),
                artist.getGender(),
                artist.getCountry(),
                artist.getDisambiguation()
        );

        String wikidataUrl = artist.getWikidataUrl();
        List<ReleaseGroup> releaseGroups = artist.getAlbums();

        return fillArtistDetails(details, wikidataUrl, releaseGroups);
    }

    private String getWikipediaApiTitle(String mbid, String wikidataUrl) {
        String wikiEntityId = wikidataUrl.substring(wikidataUrl.lastIndexOf('/') + 1);
        wikidataFetcher.getFilter().setSiteLinkFilter(Collections.singleton(WIKISITELINK));

        try {
            EntityDocument wikidataDocument = wikidataFetcher.getEntityDocument(wikiEntityId);
            if (wikidataDocument instanceof ItemDocument) {
                return ((ItemDocument) wikidataDocument).getSiteLinks().get(WIKISITELINK).getPageTitle();
            }
        } catch (MediaWikiApiErrorException | IOException e) {
            log.error("Wikidata API call on [ID: {}] [Entity ID: {}]", mbid, wikiEntityId,  e);
            return "";
        }

        return null;
    }

    private String getWikipediaDescription(String mbid, String wikidataUrl) {
        String wikiTitle = getWikipediaApiTitle(mbid, wikidataUrl);

        try {
            return wikipediaApiClient.getDescription(wikiTitle);
        } catch (HttpClientErrorException | NullPointerException e) {
            log.error("Wikipedia API call on [ID: {}] [Title: {}]", mbid, wikiTitle,  e);
        }

        return "";
    }

    private String getAlbumCoverArtUrl(String mbid, String albumId) {
        try {
            final CoverArtArchiveClient coverArtArchiveClient = new DefaultCoverArtArchiveClient();
            return coverArtArchiveClient.getReleaseGroupByMbid(UUID.fromString(albumId)).getImages().get(0).getImageUrl();
        } catch (HttpClientErrorException e) {
            log.error("CoverArtArchiveApiClient API call on [ID: {}] [Album ID: {}]", mbid, albumId,  e);
        }

        return "";
    }

    // fillArtistDetails fills rest of the details using asynchronous API calls.
    private ArtistDetails fillArtistDetails(ArtistDetails details, String wikidataUrl, List<ReleaseGroup> releaseGroups) {
            CompletableFuture<String> wikipediaDescriptionFuture =  CompletableFuture.supplyAsync(() -> {
            return getWikipediaDescription(details.getMbid(), wikidataUrl);
        });

        List<CompletableFuture<Album>> albumFutures = releaseGroups.stream().map(releaseGroup -> {
            Album album = new Album();
            album.setId(releaseGroup.getId());
            album.setTitle(releaseGroup.getTitle());
            CompletableFuture<Album> albumFuture = CompletableFuture.supplyAsync(() -> {
                String coverArtUrl = getAlbumCoverArtUrl(details.getMbid(), releaseGroup.getId());
                album.setImageUrl(coverArtUrl);
                return album;
            });
            return albumFuture;
        }).toList();

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(
                albumFutures.toArray(new CompletableFuture[0])
        );

        combinedFuture.join();
        try {
            details.setDescription(wikipediaDescriptionFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Get description async call on[ID: {}]", details.getMbid(),  e);
        }

        albumFutures.forEach(albumFuture -> {
            try {
                details.getAlbums().add(albumFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("Retrieve album async call on[ID: {}]", details.getMbid(),  e);
            }
        });

        return details;
    }
}