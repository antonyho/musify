package net.antonyho.musify.coverartarchive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public class CoverArtArchiveApiClient {

    private static final String uri = "http://coverartarchive.org/release-group/{albumId}";

    private static final RestClient restClient = RestClient.create();

    private static CoverArtArchiveApiClient INSTANCE;

    public static CoverArtArchiveApiClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CoverArtArchiveApiClient();
        }
        return INSTANCE;
    }

    public String getCoverArtUrl(String albumId) {
        ResponseEntity<CoverArt> apiResponse = restClient.get().uri(uri, albumId).retrieve().toEntity(CoverArt.class);

        return apiResponse.getBody().getImages().get(0).getUrl();
    }
}
