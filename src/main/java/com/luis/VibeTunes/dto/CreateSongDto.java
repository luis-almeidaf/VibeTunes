package com.luis.VibeTunes.dto;

public record CreateSongDto(String title,
                            String genre,
                            Long artistId) {
}
