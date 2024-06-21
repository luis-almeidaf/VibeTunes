package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.CreateSongDto;
import com.luis.VibeTunes.dto.UpdateSongDto;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @PostMapping
    public ResponseEntity<CreateSongDto> newSong ( @RequestBody CreateSongDto dto) {
        songService.newSong(dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @DeleteMapping(value = "id/{id}")
    public ResponseEntity<Song> deleteSong (@PathVariable Long id) throws Exception {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @PutMapping(value = "id/{id}")
    public ResponseEntity<?> updateUser (@PathVariable Long id, @RequestBody UpdateSongDto updatedSong) throws Exception {
        songService.updateSong(id, updatedSong);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<List<Song>> findByTitle(@PathVariable String title) {
        List<Song> songs = songService.findByTitle(title);
        return ResponseEntity.ok(songs);
    }
    @GetMapping(value = "/genre/{genre}")
    public ResponseEntity<List<Song>> findByGenre(@PathVariable String genre) {
        List<Song> songs = songService.findByGenre(genre);
        return ResponseEntity.ok(songs);
    }

}
