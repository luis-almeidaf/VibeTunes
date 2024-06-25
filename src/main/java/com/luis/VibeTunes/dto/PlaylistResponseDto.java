package com.luis.VibeTunes.dto;

import com.luis.VibeTunes.model.Song;

import java.util.Set;

public record PlaylistResponseDto(String name,
                                  Set<Song> songs) {
}
