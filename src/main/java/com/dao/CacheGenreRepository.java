package com.dao;

import com.dto.GenreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableScheduling
public class CacheGenreRepository {
    private static final Logger logger = LoggerFactory.getLogger(CacheGenreRepository.class);
    private final MovieDao movieDao;
    List<GenreDto> genreDtoList;

    public CacheGenreRepository(MovieDao movieDao) {
        this.movieDao = movieDao;
        genreDtoList = getAllGenres();
    }

    public List<GenreDto> cacheGenre() {
        return genreDtoList;
    }

    @Scheduled(fixedRate = 4 * 60 * 60 * 1000)
    private void fillCache() {
        if (genreDtoList.isEmpty()) {
            getAllGenres();
        } else {
            genreDtoList.clear();
            getAllGenres();
        }
    }

    private List<GenreDto> getAllGenres() {
        Optional<List<GenreDto>> optionalGenres = movieDao.getAllGenres();
        if (optionalGenres.isPresent()) {
            List<GenreDto> genresDbDto = optionalGenres.get();
            logger.info("Selected all genres: {} from db to Cache", genresDbDto);
            return genresDbDto;
        }
        logger.info("There no any genre for saving in Cache.");
        return List.of();
    }
}
