package com.luis.VibeTunes.service;

import com.luis.VibeTunes.model.playlist.Playlist;
import com.luis.VibeTunes.model.user.User;
import com.luis.VibeTunes.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;

    public Playlist savePlaylist(Playlist playlist) {
        return this.playlistRepository.save(playlist);
    }

    /*public Playlist findById(Long id) {
        return this.playlistRepository.findById(id).orElseThrow(() -> playlistNotFoundException("Playlist não encontrada"));
    }*/

    public Playlist findPlaylistByName(String playlistName) {
        return this.playlistRepository.findPlaylistByName(playlistName);
    }

    public List<Playlist> findPlaylistByUserUsername (String username) {
        return this.playlistRepository.findPlaylistByUserUsername(username);
    }
}
