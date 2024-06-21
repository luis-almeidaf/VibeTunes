package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.model.Artist;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Artist> findByName (@PathVariable String name) {
        Artist artist  = artistService.findArtistByName(name);
        return ResponseEntity.ok(artist);
    }
}
