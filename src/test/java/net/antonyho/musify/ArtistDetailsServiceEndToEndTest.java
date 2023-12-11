package net.antonyho.musify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ArtistDetailsServiceEndToEndTest {

    private static ArtistDetailsService testSubject;

    @BeforeAll
    static void setup() {
        testSubject = new ArtistDetailsService();
    }

    @Test
    void getTheBeatles() {
        String theBeatlesMbId = "b10bbbfc-cf9e-42e0-be17-e2c3e1d2600d";
        ArtistDetails expectedResult = new ArtistDetails(theBeatlesMbId, "The Beatles", null, null, "UK rock band, “The Fab Four”");
        expectedResult.setDescription("<p><b>The Beatles</b> were an English rock band formed in Liverpool in 1960, comprising John Lennon, Paul McCartney, George Harrison and Ringo Starr. They are regarded as the most influential band of all time and were integral to the development of 1960s counterculture and the recognition of popular music as an art form. Rooted in skiffle, beat, and 1950s rock 'n' roll, their sound incorporated elements of classical music and traditional pop in innovative ways. The band also explored music styles ranging from folk and Indian music to psychedelia and hard rock. As pioneers in recording, songwriting and artistic presentation, the Beatles revolutionised many aspects of the music industry and were often publicised as leaders of the era's youth and sociocultural movements.</p>");

        ArtistDetails actualResult = testSubject.getArtist(theBeatlesMbId);

        assertEquals(expectedResult.getMbid(), actualResult.getMbid());
        assertEquals(expectedResult.getName(), actualResult.getName());
        assertEquals(expectedResult.getGender(), actualResult.getGender());
        assertEquals(expectedResult.getCountry(), actualResult.getCountry());
        assertEquals(expectedResult.getDisambiguation(), actualResult.getDisambiguation());
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
        assertThat(actualResult.getAlbums().size()).isEqualTo(25);
    }

    @Test
    void getImagineDragons() {
        String imagineDragonsMbId = "012151a8-0f9a-44c9-997f-ebd68b5389f9";
        ArtistDetails expectedResult = new ArtistDetails(imagineDragonsMbId, "Imagine Dragons", null, "US", "American pop rock band");
        expectedResult.setDescription("<p><b>Imagine Dragons</b> is an American pop rock band based in Las Vegas, Nevada in 2008, and currently consists of lead singer Dan Reynolds, guitarist Wayne Sermon, bassist Ben McKee and drummer Daniel Platzman. The band first gained exposure with the release of their single \"It's Time\", followed by their debut album <i>Night Visions</i> (2012), which resulted in the chart-topping singles \"Radioactive\" and \"Demons\". <i>Rolling Stone</i> named \"Radioactive\", which held the record for most weeks charted on the <span><i>Billboard</i> Hot 100</span>, the \"biggest rock hit of the year\". MTV called them \"the year's biggest breakout band\", and <i>Billboard</i> named them their \"Breakthrough Band of 2013\" and \"Biggest Band of 2017\", and placed them at the top of their \"Year in Rock\" rankings for 2013, 2017, and 2018. Imagine Dragons topped the <i>Billboard</i> Year-End \"Top Artists – Duo/Group\" category in 2018.</p>");

        ArtistDetails actualResult = testSubject.getArtist(imagineDragonsMbId);

        assertEquals(expectedResult.getMbid(), actualResult.getMbid());
        assertEquals(expectedResult.getName(), actualResult.getName());
        assertEquals(expectedResult.getGender(), actualResult.getGender());
        assertEquals(expectedResult.getCountry(), actualResult.getCountry());
        assertEquals(expectedResult.getDisambiguation(), actualResult.getDisambiguation());
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
        assertThat(actualResult.getAlbums().size()).isEqualTo(25);
    }

    @Test
    void getPeterPaulAndMary() {
        String peterPaulAndMaryMbId = "3d621c94-2356-4532-ad52-79e0ef73e20c";
        ArtistDetails expectedResult = new ArtistDetails(peterPaulAndMaryMbId, "Peter, Paul & Mary", null, "US", "");
        expectedResult.setDescription("<p><b>Peter, Paul and Mary</b> were an American folk group formed in New York City in 1961 during the American folk music revival phenomenon. The trio consisted of tenor Peter Yarrow, baritone Paul Stookey, and contralto Mary Travers. The group's repertoire included songs written by Yarrow and Stookey, early songs by Bob Dylan, and covers of other folk musicians. They were enormously successful in the early- and mid-1960s, with their debut album topping the charts for weeks, and helped popularize the folk music revival. After the death of Travers in 2009, Yarrow and Stookey continued to perform as a duo under their individual names.</p>");

        ArtistDetails actualResult = testSubject.getArtist(peterPaulAndMaryMbId);

        assertEquals(expectedResult.getMbid(), actualResult.getMbid());
        assertEquals(expectedResult.getName(), actualResult.getName());
        assertEquals(expectedResult.getGender(), actualResult.getGender());
        assertEquals(expectedResult.getCountry(), actualResult.getCountry());
        assertEquals(expectedResult.getDisambiguation(), actualResult.getDisambiguation());
        assertEquals(expectedResult.getDescription(), actualResult.getDescription());
        assertThat(actualResult.getAlbums().size()).isEqualTo(25);
    }
}