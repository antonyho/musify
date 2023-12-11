package net.antonyho.musify.wikipedia;

import org.springframework.web.client.RestTemplate;

public class WikipediaApiClient {

    public static final String API_URI = "https://en.wikipedia.org/api/rest_v1/page/summary/{title}";

    private static RestTemplate restTemplate = new RestTemplate();

    public WikipediaApiClient() {}

    public WikipediaApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDescription(String title) {
        Summary summary = restTemplate.getForObject(API_URI, Summary.class, title);

        return summary.getExtractHtml();
    }
}
