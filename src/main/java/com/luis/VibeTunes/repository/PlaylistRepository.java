package com.luis.VibeTunes.repository;

import com.luis.VibeTunes.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findPlaylistByName(String playlistName);
    List<Playlist> findPlaylistByUserUsername(String username);
}
