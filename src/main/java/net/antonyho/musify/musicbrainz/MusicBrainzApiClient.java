package net.antonyho.musify.musicbrainz;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public final class MusicBrainzApiClient {

    private static final String uri = "http://musicbrainz.org/ws/2/artist/{id}?&fmt=json&inc=url-rels+release-groups";

    private static final RestClient restClient = RestClient.create();

    private static MusicBrainzApiClient INSTANCE;

    public static MusicBrainzApiClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MusicBrainzApiClient();
        }
        return INSTANCE;
    }

    public Artist getArtist(String id) {
        ResponseEntity<Artist> apiResponse = restClient.get().uri(uri, id).retrieve().toEntity(Artist.class);

        return apiResponse.getBody();
    }
}