package com.service;

import com.dao.MovieDao;
import com.dao.mapper.modelMapper.DefaultMapper;
import com.dto.MovieDto;
import com.model.Movie;
import com.model.SortingCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final MovieDao movieDao;
    private final DefaultMapper movieMapper;

    public MovieService(MovieDao movieDao, DefaultMapper movieMapper) {
        this.movieDao = movieDao;
        this.movieMapper = movieMapper;
    }

    public List<MovieDto> getAll(SortingCredentials sortingCredentials) {
        Optional<List<Movie>> optionalMovies = movieDao.getAllMovie(sortingCredentials);
        List<MovieDto> moviesDto = new ArrayList<>();
        if (optionalMovies.isPresent()) {
            List<Movie> movies = optionalMovies.get();
            for (Movie movie : movies) {
                moviesDto.add(movieMapper.toDto(movie));
            }
        }
        logger.info("Delivered all movies: {}. Column for sorting - {},  sorting type - {}",
                moviesDto, sortingCredentials.getSortingField(), sortingCredentials.getSortDirection());
        return moviesDto;
    }

    public List<MovieDto> getRandom() {
        Optional<List<Movie>> optionalMovies = movieDao.getRandom();
        List<MovieDto> moviesDto = new ArrayList<>();
        if (optionalMovies.isPresent()) {
            List<Movie> movies = optionalMovies.get();
            for (Movie movie : movies) {
                moviesDto.add(movieMapper.toDto(movie));
            }
        }
        logger.info("Delivered random movies: {}, count - {}", moviesDto, moviesDto.size());
        return moviesDto;
    }

    public List<MovieDto> getMoviesByGenre(String genre, SortingCredentials sortingCredentials) {
        Optional<List<Movie>> optionalMovies = movieDao.getMoviesByGenre(genre, sortingCredentials);
        List<MovieDto> moviesDto = new ArrayList<>();
        if (optionalMovies.isPresent()) {
            List<Movie> movies = optionalMovies.get();
            for (Movie movie : movies) {
                moviesDto.add(movieMapper.toDto(movie));
            }
        }
        logger.info("Delivered movies {} by genre: {}. Column for sorting - {},  sorting type - {}",
                moviesDto,  genre, sortingCredentials.getSortingField(), sortingCredentials.getSortDirection());
        return moviesDto;
    }
}
