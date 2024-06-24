package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.CreatePlaylistDto;
import com.luis.VibeTunes.dto.UpdatePlaylistDto;
import com.luis.VibeTunes.dto.UpdateSongDto;
import com.luis.VibeTunes.model.Playlist;
import com.luis.VibeTunes.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody CreatePlaylistDto playlistDto) {
        Playlist playlist = playlistService.newPlaylist(playlistDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(playlist);
    }

    @PutMapping(value = "id/{id}")
    public ResponseEntity<?> updatePlaylist (@PathVariable Long id, @RequestBody UpdatePlaylistDto updatePlaylistDto) throws Exception {
        playlistService.updatePlaylist(id, updatePlaylistDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value =  "id/{id}")
    public  ResponseEntity<Playlist> deletePlaylist (@PathVariable Long id) throws  Exception {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();

    }


}
