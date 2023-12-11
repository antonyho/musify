package net.antonyho.musify.musicbrainz;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MusicBrainzApiClientTest {

    private static MusicBrainzApiClient mockMusicBrainzApiClient;

    private static RestTemplate mockRestTemplate = mock(RestTemplate.class);

    @BeforeAll
    static void setup() {
        mockMusicBrainzApiClient = new MusicBrainzApiClient(mockRestTemplate);
    }

    @Test
    void getArtist() {
        Artist expectedResult = new Artist();

        when(mockRestTemplate.getForObject(
                eq(MusicBrainzApiClient.API_URI),
                any(Class.class),
                ArgumentMatchers.<Object>any()
        )).thenReturn(expectedResult);

        Artist actualResult = mockMusicBrainzApiClient.getArtist("musicbrainz-id-string");

        assertEquals(expectedResult, actualResult);
    }
}