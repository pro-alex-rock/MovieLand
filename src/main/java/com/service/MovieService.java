package com.service;

import com.dao.MovieDao;
import com.dao.mapper.MovieMapper;
import com.dto.MovieDto;
import com.model.Movie;
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
    private MovieMapper movieMapper;

    public MovieService(MovieDao movieDao, MovieMapper movieMapper) {
        this.movieDao = movieDao;
        this.movieMapper = movieMapper;
    }

    public List<MovieDto> getAll() {
        Optional<List<Movie>> optionalMovies = movieDao.getAll();
        List<MovieDto> movieDtos = new ArrayList<>();
        if (optionalMovies.isPresent()) {
            List<Movie> movies = optionalMovies.get();
            for (Movie movie : movies) {
                movieDtos.add(movieMapper.toDto(movie));
            }
        }

        return movieDtos;
    }

    public void addMovie(Movie movie) {
        movieDao.addMovie(movie);
    }
}
