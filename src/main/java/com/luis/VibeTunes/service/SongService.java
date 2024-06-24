package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreateSongDto;
import com.luis.VibeTunes.dto.UpdateSongDto;
import com.luis.VibeTunes.model.Artist;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.repository.ArtistRepository;
import com.luis.VibeTunes.repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public List<Song> findAll() {
        return this.songRepository.findAll();
    }

    @Transactional
    public Song newSong(CreateSongDto songDto) {
        Optional<Artist> optionalArtist = artistRepository.findById(songDto.artistId());
        if (optionalArtist.isPresent()) {
            Artist artist = optionalArtist.get();
            Song song = new Song();
            song.setTitle(songDto.title());
            song.setGenre(songDto.genre());
            song.setArtist(artist);

            artist.getSongs().add(song);
            songRepository.save(song);

            return song;
        } else {
            throw new EntityNotFoundException("Artist not found");
        }
    }

    @Transactional
    public void deleteSong(Long id) throws Exception {
        Song song = songRepository.findById(id).orElseThrow(() -> new Exception("Música não encontrada"));
        songRepository.delete(song);
    }

    @Transactional
    public void updateSong(Long id, UpdateSongDto updatedSong) throws Exception {
        songRepository.findById(id)
                .map(song -> {
                    song.setTitle(updatedSong.title());
                    song.setGenre(updatedSong.genre());
                    return songRepository.save(song);
                }).orElseThrow(() -> new Exception("Música não encontrada"));
    }

}

