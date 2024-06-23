package com.luis.VibeTunes.repository;

import com.luis.VibeTunes.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByTitle(String title);
    List<Song> findByGenre(String genre);


}
