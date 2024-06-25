package com.luis.VibeTunes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "artists")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private Set<Song> songs = new HashSet<>();

    public Artist() {
    }

    public Artist(Long id, String name, Set<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artist artist = (Artist) obj;
        return Objects.equals(id, artist.id);
    }
}
