package com.luis.VibeTunes.dto;

import java.util.List;

public record CreatePlaylistDto(String name,
                                Long userId,
                                List<Long> songIds) {
}
