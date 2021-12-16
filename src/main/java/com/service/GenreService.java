package com.service;

import com.dao.CacheGenreRepository;
import com.dto.GenreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);
    private final CacheGenreRepository cacheGenreRepository;

    public GenreService(CacheGenreRepository cacheGenreRepository) {
        this.cacheGenreRepository = cacheGenreRepository;
    }

    public List<GenreDto> getAllGenres() {
        List<GenreDto> cachedGenres = cacheGenreRepository.getCacheGenre();
        logger.info("Delivered all genres: {} from cache", cachedGenres);
        return cachedGenres;
    }
}
