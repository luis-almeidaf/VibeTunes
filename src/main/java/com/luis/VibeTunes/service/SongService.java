package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreateSongDto;
import com.luis.VibeTunes.dto.UpdateSongDto;
import com.luis.VibeTunes.model.Artist;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.repository.ArtistRepository;
import com.luis.VibeTunes.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    public SongService(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    public List<Song> findByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    public List<Song> findByGenre(String genre) {
        return songRepository.findByGenre(genre);
    }

    @Transactional
    public void newSong(Long artistId, CreateSongDto songDto) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid artist id: " + artistId));
        Song song = new Song();
        song.setTitle(songDto.tittle());
        song.setArtist(artist);
        song.setAlbum(songDto.album());
        song.setGenre(songDto.genre());
        songRepository.save(song);
    }



    @Transactional
    public void deleteSong(Long id) throws Exception {
        if (!songRepository.existsById(id)) {
            throw new Exception("Não foi encontrada uma música com esse id: " + id); // criar exceção depois
        }
        songRepository.deleteById(id);
    }

    @Transactional
    public void updateSong(Long id, UpdateSongDto updatedSong) throws Exception {
        songRepository.findById(id)
                .map(song -> {
                    song.setTitle(updatedSong.title());
                    song.setArtist((updatedSong.artist()));
                    song.setAlbum(updatedSong.album());
                    song.setGenre(updatedSong.genre());
                    return songRepository.save(song);
                }).orElseThrow(() -> new Exception("Nenhuma música encontrada"));
    }


}

