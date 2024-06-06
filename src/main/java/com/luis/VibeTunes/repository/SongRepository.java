package com.luis.VibeTunes.repository;

import com.luis.VibeTunes.model.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
