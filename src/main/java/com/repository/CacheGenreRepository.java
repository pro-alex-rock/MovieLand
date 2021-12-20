package com.repository;

import com.dto.GenreDto;
import com.entity.Genre;
import com.repository.mapper.modelMapper.GenreMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CacheGenreRepository {
    private static final Logger logger = LoggerFactory.getLogger(CacheGenreRepository.class);
    private final GenreRepository genreRepository;
    private volatile List<Genre> genreList;
    private final GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

    public CacheGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        genreList = getAllGenres();
    }


    public List<GenreDto> getCacheGenre() {
        List<GenreDto> newListGenreDto = genreList.stream().map(genreMapper::toDto).collect(Collectors.toList());
        logger.info("Sent all genres: {} from cache to GenreService", newListGenreDto);
        return List.copyOf(newListGenreDto);
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    private void fillCache() {
        if (!genreList.isEmpty()) {
            genreList.clear();
        }
        genreList = getAllGenres();
    }

    private List<Genre> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        logger.info("Selected all genres: {} from db to Cache", genres);
        return genres;
    }
}
