package com.luis.VibeTunes.repository;

import com.luis.VibeTunes.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findArtistByName(String name);
    Artist findArtistByGenre(String genre);
}
