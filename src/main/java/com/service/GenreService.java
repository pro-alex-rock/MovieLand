package com.service;

import com.dao.CacheGenreRepository;
import com.dao.MovieDao;
import com.dto.GenreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);
    private final MovieDao movieDao;
    private final CacheGenreRepository cacheGenreRepository;

    public GenreService(MovieDao movieDao, CacheGenreRepository cacheGenreRepository) {
        this.movieDao = movieDao;
        this.cacheGenreRepository = cacheGenreRepository;
    }

    public List<GenreDto> getAllGenres() {
        List<GenreDto> cachedGenres = cacheGenreRepository.cacheGenre();
        if (cachedGenres.size() == 0) {
            Optional<List<GenreDto>> optionalGenres = movieDao.getAllGenres();
            if (optionalGenres.isPresent()) {
                List<GenreDto> genresDbDto = optionalGenres.get();
                logger.info("Delivered all genres: {} from db", genresDbDto);
                return genresDbDto;
            }
            logger.info("There no any genre.");
            return List.of();
        } else {
            logger.info("Delivered all genres: {} from cache", cachedGenres);
            return cachedGenres;
        }
    }
}
