package com.luis.VibeTunes.model.artist;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

@Data
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String genre;
}
