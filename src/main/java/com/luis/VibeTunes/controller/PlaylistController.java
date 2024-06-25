package com.luis.VibeTunes.controller;

import com.luis.VibeTunes.dto.CreatePlaylistDto;
import com.luis.VibeTunes.dto.UpdatePlaylistDto;
import com.luis.VibeTunes.model.Playlist;
import com.luis.VibeTunes.service.PlaylistService;
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
    public Playlist removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) throws Exception {
        return playlistService.removeSongFromPlaylist(playlistId, songId);
    }

    //correções: permitir que a mesma música seja add em várias playlists,
    // acho que é só mudar de set para list,
    // ou dar algum jeito de cada usuário tenha sua playlist
    //melhorar os dto de resposta
    // alterar para que somente o usuário dono da playlist possa altera-la ou admins
}
