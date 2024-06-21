package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreateSongDto;
import com.luis.VibeTunes.dto.UpdateSongDto;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findByTitle(String title) {
        return songRepository.findByTitle(title);
    }

    public List<Song> findByGenre(String genre) {
        return songRepository.findByGenre(genre);
    }

    @Transactional
    public void newSong(CreateSongDto songDto) {
        Song song = new Song();
        song.setTitle(songDto.tittle());
        song.setArtist(songDto.artist());
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
    public void updateSong (Long id, UpdateSongDto updatedSong) throws Exception {
        songRepository.findById(id)
                .map(song -> {
                    song.setTitle(updatedSong.title());
                    song.setArtist((updatedSong.artist()));
                    song.setAlbum(updatedSong.album());
                    return songRepository.save(song);
                }).orElseThrow(() -> new Exception("Nenhuma música encontrada"));
    }
}

