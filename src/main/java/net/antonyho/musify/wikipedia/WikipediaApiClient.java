package net.antonyho.musify.wikipedia;

import org.springframework.web.client.RestTemplate;

public class WikipediaApiClient {

    private static final String uri = "https://en.wikipedia.org/api/rest_v1/page/summary/";

    private static final RestTemplate restTemplate = new RestTemplate();

    private static WikipediaApiClient INSTANCE;

    public static WikipediaApiClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WikipediaApiClient();
        }

        return INSTANCE;
    }

    public String getDescription(String title) {
        Summary summary = restTemplate.getForObject(uri + title, Summary.class);

        return summary.getExtractHtml();
    }
}
