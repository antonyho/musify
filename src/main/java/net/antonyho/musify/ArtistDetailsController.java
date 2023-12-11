package net.antonyho.musify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistDetailsController {

    private static final Logger log = LoggerFactory.getLogger(ArtistDetailsController.class);

    private final ArtistDetailsService service;

    public ArtistDetailsController(ArtistDetailsService service) {
        this.service = service;
    }

    @GetMapping("/musify/music-artist/details/{mbid}")
    public ResponseEntity<ArtistDetails> getArtistDetails(@PathVariable String mbid) {
        log.debug("Request for [MBID: {}]", mbid);
        ArtistDetails details = service.getArtist(mbid);

        return ResponseEntity.status(HttpStatus.OK).body(details);
    }

}