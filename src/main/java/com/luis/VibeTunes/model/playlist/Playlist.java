package com.luis.VibeTunes.model.playlist;

import com.luis.VibeTunes.model.song.Song;
import com.luis.VibeTunes.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @Column(nullable = false)
    private User user;
    private Set<Song> songs = new HashSet<>();
}
