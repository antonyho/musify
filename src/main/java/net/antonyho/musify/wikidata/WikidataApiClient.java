package net.antonyho.musify.wikidata;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

public final class WikidataApiClient {

    private static final RestClient restClient = RestClient.create();

    private static WikidataApiClient INSTANCE;

    public static WikidataApiClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WikidataApiClient();
        }

        return INSTANCE;
    }

    public String getWikipediaTitle(String wikidataUrl) {
        ResponseEntity<Wikidata> apiResponse = restClient.get().uri(wikidataUrl).retrieve().toEntity(Wikidata.class);
        String wikipediaUrl = apiResponse.getBody().getEntities().get(0)
                .getSitelinks().stream()
                .filter(sitelink -> sitelink.getSite().equals("enwiki")).findFirst().orElseThrow()
                .getUrl();


        return wikipediaUrl.substring(wikipediaUrl.lastIndexOf('/') + 1);
    }
}
