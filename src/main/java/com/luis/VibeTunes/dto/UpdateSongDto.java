package com.luis.VibeTunes.dto;

import com.luis.VibeTunes.model.Artist;

public record UpdateSongDto(String title, Artist artist, String album, String genre) {
}
