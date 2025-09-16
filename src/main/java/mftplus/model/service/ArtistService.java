package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Artist;
import mftplus.model.repository.ArtistRepository;


import java.util.List;

public class ArtistService implements Service<Artist, Integer> {
    @Getter
    private static ArtistService service = new ArtistService();

    private ArtistService() {

    }

    @Override
    public void save(Artist artist) throws Exception {
        try (ArtistRepository artistRepository = new ArtistRepository()) {
            artistRepository.save(artist);
        }

    }

    @Override
    public void edit(Artist artist) throws Exception {
        try (ArtistRepository artistRepository = new ArtistRepository()) {
            artistRepository.edit(artist);
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        try (ArtistRepository artistRepository = new ArtistRepository()) {
            artistRepository.delete(id);
        }

    }

    @Override
    public List<Artist> findAll() throws Exception {
        try (ArtistRepository artistRepository = new ArtistRepository()) {
            return artistRepository.findAll();
        }
    }

    @Override
    public Artist findById(Integer id) throws Exception {
        try (ArtistRepository artistRepository = new ArtistRepository()) {
            return artistRepository.findById(id);
        }
    }
}
