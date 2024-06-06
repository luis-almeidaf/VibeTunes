package com.luis.VibeTunes.repository;

import com.luis.VibeTunes.model.playlist.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
