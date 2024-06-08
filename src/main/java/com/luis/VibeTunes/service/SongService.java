package com.luis.VibeTunes.service;

import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    @Autowired
    SongRepository songRepository;

    public Song findByTitle(String title) {
        return this.songRepository.findByTitle(title);
    }

    public Song findByGenre(String genre) {
        return this.songRepository.findByGenre(genre);
    }

    public Song saveSong (Song song) {
        return this.songRepository.save(song);
    }
}
