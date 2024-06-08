package com.luis.VibeTunes.repository;

import com.luis.VibeTunes.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByTitle(String title);
    Song findByGenre(String genre);
}
