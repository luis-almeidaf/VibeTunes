package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.CreateSongDto;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @PostMapping
    public ResponseEntity<CreateSongDto> newSong ( @RequestBody CreateSongDto dto) {
        songService.newSong(dto);
        return ResponseEntity.ok(dto);
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
