package net.antonyho.musify.wikipedia;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WikipediaApiClientTest {

    private static WikipediaApiClient mockWikipediaApiClient;

    private static RestTemplate mockRestTemplate = mock(RestTemplate.class);

    @BeforeAll
    static void setup() {
        mockWikipediaApiClient = new WikipediaApiClient(mockRestTemplate);
    }

    @Test
    void getDescription() {
        String expectedResult = "A successful artist description";

        Summary mockSummary = new Summary();
        mockSummary.setExtractHtml(expectedResult);
        when(mockRestTemplate.getForObject(
                eq(WikipediaApiClient.API_URI),
                any(Class.class),
                ArgumentMatchers.<Object>any()
        )).thenReturn(mockSummary);

        String actualResult = mockWikipediaApiClient.getDescription("wiki-title-string");

        assertEquals(expectedResult, actualResult);
    }
}