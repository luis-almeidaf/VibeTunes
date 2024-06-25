package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreatePlaylistDto;
import com.luis.VibeTunes.dto.UpdatePlaylistDto;
import com.luis.VibeTunes.model.Playlist;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.model.User;
import com.luis.VibeTunes.repository.PlaylistRepository;
import com.luis.VibeTunes.repository.SongRepository;
import com.luis.VibeTunes.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Playlist> findPlaylistByUserUsername(String username) {
        return this.playlistRepository.findPlaylistByUserUsername(username);
    }

    public List<Playlist> findAll() {
        return this.playlistRepository.findAll();
    }

    @Transactional
    public void newPlaylist(CreatePlaylistDto playlistDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());

        Playlist playlist = new Playlist();
        playlist.setName(playlistDto.name());
        playlist.setUser(user);
        playlistRepository.save(playlist);
    }


    @Transactional
    public void deletePlaylist(Long playlistId) throws Exception {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new Exception("Playlist não encontrada"));
        playlistRepository.delete(playlist);
    }

    @Transactional
    public void updatePlaylist(Long playlistId, UpdatePlaylistDto updatePlaylistDto) throws Exception {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new Exception("Playlist não encontrada"));
        playlist.setName(updatePlaylistDto.name());

        playlistRepository.save(playlist);
    }

    @Transactional
    public Playlist addSongToPlaylist(Long playlistId, Long songId) throws Exception {

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new Exception("Playlist não encontrada"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new Exception("Música não encontrada"));

        playlist.getSongs().add(song);
        return playlistRepository.save(playlist);
    }

    @Transactional
    public Playlist removeSongFromPlaylist(Long playlistId, Long songId) throws Exception {

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new Exception("Playlist não encontrada"));

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new Exception("Música não encontrada"));

        playlist.getSongs().remove(song);
        return playlistRepository.save(playlist);
    }
}
