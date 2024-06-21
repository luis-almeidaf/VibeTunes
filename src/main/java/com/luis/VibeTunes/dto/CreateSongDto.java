package com.luis.VibeTunes.dto;

import com.luis.VibeTunes.model.Artist;

public record CreateSongDto(String tittle, Artist artist, String album, String genre ) {
}
