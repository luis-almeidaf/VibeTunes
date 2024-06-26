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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
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

    public List<Playlist> findPlaylistByName(String playlistName) {
        return this.playlistRepository.findPlaylistByName(playlistName);
    }

    public List<Playlist> findPlaylistByUsername(String username) {
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName());
        if (!playlist.getUser().equals(currentUser)) {
            throw new AccessDeniedException("Você não tem permissão para alterar essa playlist");
        }

        playlistRepository.delete(playlist);
    }

    @Transactional
    public void updatePlaylist(Long playlistId, UpdatePlaylistDto updatePlaylistDto) throws Exception {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new Exception("Playlist não encontrada"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName());
        if (!playlist.getUser().equals(currentUser)) {
            throw new AccessDeniedException("Você não tem permissão para alterar essa playlist");
        }
        playlist.setName(updatePlaylistDto.name());

        playlistRepository.save(playlist);
    }

    @Transactional
    public void addSongToPlaylist(Long playlistId, Long songId) throws Exception {

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new Exception("Playlist não encontrada"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName());
        if (!playlist.getUser().equals(currentUser)) {
            throw new AccessDeniedException("Você não tem permissão para alterar essa playlist");
        }

        boolean songAlreadyInPlaylist = playlist.getSongs().stream()
                .anyMatch(song -> song.getId().equals(songId));
        if (songAlreadyInPlaylist) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Música já foi adicionada a playlist");
        }

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new Exception("Música não encontrada"));

        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

    @Transactional
    public void removeSongFromPlaylist(Long playlistId, Long songId) throws Exception {

        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new Exception("Playlist não encontrada"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName());
        if (!playlist.getUser().equals(currentUser)) {
            throw new AccessDeniedException("Você não tem permissão para alterar essa playlist");
        }

        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new Exception("Música não encontrada"));

        playlist.getSongs().remove(song);
        playlistRepository.save(playlist);
    }
}
