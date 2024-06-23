package com.luis.VibeTunes.service;

import com.luis.VibeTunes.dto.CreateArtistDto;
import com.luis.VibeTunes.dto.UpdateArtistDto;
import com.luis.VibeTunes.model.Artist;
import com.luis.VibeTunes.model.Song;
import com.luis.VibeTunes.repository.ArtistRepository;
import com.luis.VibeTunes.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;


    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;

    }

    public Artist findArtistByName(String name) {
        return artistRepository.findArtistByName(name);
    }

    @Transactional
    public void newArtist(CreateArtistDto artistDto) {
        Artist artistFromDB = artistRepository.findArtistByName(artistDto.name());
        if (artistFromDB != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Artista já cadastrado");
        }
        Artist artist = new Artist();
        artist.setName(artistDto.name());
        artistRepository.save(artist);
    }

    @Transactional
    public void deleteArtist(Long id) throws Exception {
        if (!artistRepository.existsById(id)) {
            throw new Exception("Não foi encontrado um artista com esse id: " + id);
        }
        artistRepository.deleteById(id);
    }

    @Transactional
    public void updateArtist(Long id, UpdateArtistDto updateArtistDto) throws Exception {
        artistRepository.findById(id)
                .map(artist -> {
                    artist.setName(updateArtistDto.name());
                    return artistRepository.save(artist);
                }).orElseThrow(() -> new Exception("Nenhum artista encontrado"));
    }

}

