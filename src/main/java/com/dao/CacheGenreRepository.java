package com.dao;

import com.dto.GenreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CacheGenreRepository {
    private static final Logger logger = LoggerFactory.getLogger(CacheGenreRepository.class);
    private final GenreDao genreDao;
    private volatile List<GenreDto> genreDtoList;

    public CacheGenreRepository(GenreDao genreDao) {
        this.genreDao = genreDao;
        genreDtoList = getAllGenres();
    }

    public List<GenreDto> getCacheGenre() {
        List<GenreDto> newListGenreDto = new ArrayList<>();
        newListGenreDto.addAll(genreDtoList);
        logger.info("Sent all genres: {} from cache to GenreService", newListGenreDto);
        return newListGenreDto;
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    private void fillCache() {
        if (!genreDtoList.isEmpty()) {
            genreDtoList.clear();
        }
        genreDtoList = getAllGenres();
    }

    private List<GenreDto> getAllGenres() {
        Optional<List<GenreDto>> optionalGenres = genreDao.getAllGenres();
        if (optionalGenres.isPresent()) {
            List<GenreDto> genresDbDto = optionalGenres.get();
            logger.info("Selected all genres: {} from db to Cache", genresDbDto);
            return genresDbDto;
        }
        logger.info("There no any genre for saving in Cache.");
        return List.of();
    }
}
