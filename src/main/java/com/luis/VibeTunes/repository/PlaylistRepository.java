package com.luis.VibeTunes.repository;

import com.luis.VibeTunes.model.playlist.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Playlist findPlaylistByName(String playlistName);
    List<Playlist> findPlaylistByUserUsername(String username);
}