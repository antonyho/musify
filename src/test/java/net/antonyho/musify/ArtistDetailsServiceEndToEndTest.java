package net.antonyho.musify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ArtistDetailsServiceTest {

    private static ArtistDetailsService testSubject;

    @BeforeAll
    static void setup() {
        testSubject = new ArtistDetailsService();
    }

    @Test
    void getArtist() {
        ArtistDetails artistDetails = testSubject.getArtist("");
    }
}