package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreateSongDto;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;


    public List<Song> findByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    public List<Song> findByGenre(String genre) {
        return songRepository.findByGenre(genre);
    }

    public Song newSong (CreateSongDto songDto) {
        var song = new Song();
        song.setTitle(songDto.tittle());
        song.setArtist(songDto.artist());
        song.setAlbum(songDto.album());
        song.setGenre(songDto.genre());
        return this.songRepository.save(song);
    }
}
