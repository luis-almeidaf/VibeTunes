package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreatePlaylistDto;
import com.luis.VibeTunes.dto.UpdatePlaylistDto;
import com.luis.VibeTunes.dto.UpdateSongDto;
import com.luis.VibeTunes.model.Playlist;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.repository.PlaylistRepository;
import com.luis.VibeTunes.repository.SongRepository;
import com.luis.VibeTunes.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    final PlaylistRepository playlistRepository;
    final UserRepository userRepository;
    final SongRepository songRepository;

    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    public Playlist findPlaylistByName(String playlistName) {
        return this.playlistRepository.findPlaylistByName(playlistName);
    }

    public List<Playlist> findPlaylistByUserUsername (String username) {
        return this.playlistRepository.findPlaylistByUserUsername(username);
    }

    public List<Playlist> findAll () {
        return this.playlistRepository.findAll();
    }

    @Transactional
    public Playlist newPlaylist(CreatePlaylistDto playlistDto) {
        Optional<User> optionalUser = userRepository.findById(playlistDto.userId());
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        User user = optionalUser.get();
        List<Song> songs = songRepository.findAllById(playlistDto.songIds());

        Playlist playlist = new Playlist();
        playlist.setName(playlist.getName());
        playlist.setUser(user);
        playlist.setSongs(songs);

        return playlistRepository.save(playlist);
    }

    @Transactional
    public void deletePlaylist(Long id) throws Exception {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> new Exception("Playlist não encontrada"));
        playlistRepository.delete(playlist);
    }

    @Transactional
    public void updatePlaylist(Long id, UpdatePlaylistDto updatePlaylistDto) throws Exception {
        Playlist playlist = playlistRepository.findById(id)
                .orElseThrow(() -> new Exception("Playlist não encontrada"));
        playlist.setName(updatePlaylistDto.name());

        List<Song> songs = songRepository.findAllById(updatePlaylistDto.songIds());
        playlist.setSongs(songs);
        playlistRepository.save(playlist);
    }
}
