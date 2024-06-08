package com.luis.VibeTunes.service;

import com.luis.VibeTunes.model.Artist;
import com.luis.VibeTunes.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    @Autowired
    ArtistRepository artistRepository;

    public Artist saveArtist(Artist artist){
        return this.artistRepository.save(artist);
    }

    public Artist findArtistByName (String name) {
        return this.artistRepository.findArtistByName(name);
    }

    public Artist findArtistByGenre (String genre) {
        return this.artistRepository.findArtistByGenre(genre);
    }


}
