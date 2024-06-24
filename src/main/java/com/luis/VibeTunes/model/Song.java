package com.luis.VibeTunes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "songs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    @JsonIgnoreProperties("songs")
    private Artist artist;

    public Song() {
    }

    public Song(Long id, String title, String genre, Artist artist) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Song song = (Song) obj;
        return Objects.equals(id, song.id);
    }
}
