package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.CreatePlaylistDto;
import com.luis.VibeTunes.dto.UpdatePlaylistDto;
import com.luis.VibeTunes.model.Playlist;
import com.luis.VibeTunes.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<CreatePlaylistDto> createPlaylist(@RequestBody CreatePlaylistDto playlistDto) {
        playlistService.newPlaylist(playlistDto);
        return ResponseEntity.ok(playlistDto);
    }

    @PutMapping(value = "id/{id}")
    public ResponseEntity<Playlist> updatePlaylist (@PathVariable Long id, @RequestBody UpdatePlaylistDto updatePlaylistDto) throws Exception {
        playlistService.updatePlaylist(id, updatePlaylistDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value =  "id/{id}")
    public  ResponseEntity<Playlist> deletePlaylist (@PathVariable Long id) throws  Exception {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<?> addSongToPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) throws Exception {
        playlistService.addSongToPlaylist(playlistId, songId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{playlistId}/songs/{songId}")
    public ResponseEntity<?> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) throws Exception {
        playlistService.removeSongFromPlaylist(playlistId, songId);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/playlistName/{playlistName}")
    public ResponseEntity<List<Playlist>> findPlaylistByName (@PathVariable String playlistName) {
        List<Playlist> playlists = playlistService.findPlaylistByName(playlistName);
        return ResponseEntity.ok(playlists);
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<List<Playlist>> findPlaylistByUserUsername (@PathVariable String username) {
        List<Playlist> playlists = playlistService.findPlaylistByUsername(username);
        return ResponseEntity.ok(playlists);
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @GetMapping
    public ResponseEntity<List<Playlist>> findAll () {
        List<Playlist> playlists = playlistService.findAll();
        return ResponseEntity.ok(playlists);
    }

}
