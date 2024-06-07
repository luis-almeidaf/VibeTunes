package com.luis.VibeTunes.repository;

import com.luis.VibeTunes.model.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Artist findArtistByName(String name);
    Artist findArtistByGenre(String genre);
}
