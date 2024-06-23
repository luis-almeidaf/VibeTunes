package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.CreateArtistDto;
import com.luis.VibeTunes.dto.UpdateArtistDto;
import com.luis.VibeTunes.model.Artist;
import com.luis.VibeTunes.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @PostMapping
    public ResponseEntity<CreateArtistDto> createArtist(@RequestBody CreateArtistDto artistDto) {
        artistService.newArtist(artistDto);
        return ResponseEntity.ok(artistDto);
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @DeleteMapping(value = "id/{id}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable Long id) throws Exception {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @PutMapping(value = "id/{id}")
    public ResponseEntity<?> updateArtist(@PathVariable Long id, @RequestBody UpdateArtistDto updateArtistDto) throws Exception {
        artistService.updateArtist(id, updateArtistDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Artist> findByName(@PathVariable String name) {
        Artist artist = artistService.findArtistByName(name);
        return ResponseEntity.ok(artist);
    }

}
