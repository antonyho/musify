package net.antonyho.musify.musicbrainz;

import org.springframework.web.client.RestTemplate;

public final class MusicBrainzApiClient {

    public static final String API_URI = "http://musicbrainz.org/ws/2/artist/{id}?&fmt=json&inc=url-rels+release-groups";

    private static RestTemplate restTemplate = new RestTemplate();

    public MusicBrainzApiClient() {}

    public MusicBrainzApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Artist getArtist(String id) {
        return restTemplate.getForObject(API_URI, Artist.class, id);
    }
}