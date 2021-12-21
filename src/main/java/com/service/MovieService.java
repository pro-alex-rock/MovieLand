package com.service;

import com.dto.ThinMovieDto;
import com.repository.MovieDao;
import com.repository.mapper.modelMapper.MovieThickMapper;
import com.repository.mapper.modelMapper.MovieThinMapper;
import com.entity.Movie;
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
    //private final MovieRepository movieRepository;
    private final MovieThinMapper movieThinMapper;

    public MovieService(MovieDao movieDao, MovieThinMapper movieThinMapper) {
        this.movieDao = movieDao;
        this.movieThinMapper = movieThinMapper;
    }

    public List<ThinMovieDto> getAll(SortingCredentials sortingCredentials) {
        Optional<List<Movie>> optionalMovies = movieDao.getAllMovie(sortingCredentials);
        List<ThinMovieDto> moviesDto = new ArrayList<>();
        if (optionalMovies.isPresent()) {
            List<Movie> movies = optionalMovies.get();
            for (Movie movie : movies) {
                moviesDto.add(movieThinMapper.toDto(movie));
            }
        }
        logger.info("Delivered all movies: {}. Column for sorting - {},  sorting type - {}",
                moviesDto, sortingCredentials.getSortingField(), sortingCredentials.getSortDirection());
        return moviesDto;
    }

    public List<ThinMovieDto> getRandom() {
        Optional<List<Movie>> optionalMovies = movieDao.getRandom();
        List<ThinMovieDto> moviesDto = new ArrayList<>();
        if (optionalMovies.isPresent()) {
            List<Movie> movies = optionalMovies.get();
            for (Movie movie : movies) {
                moviesDto.add(movieThinMapper.toDto(movie));
            }
        }
        logger.info("Delivered random movies: {}, count - {}", moviesDto, moviesDto.size());
        return moviesDto;
    }

    public List<ThinMovieDto> getMoviesByGenre(String genre, SortingCredentials sortingCredentials) {
        Optional<List<Movie>> optionalMovies = movieDao.getMoviesByGenre(genre, sortingCredentials);
        List<ThinMovieDto> moviesDto = new ArrayList<>();
        if (optionalMovies.isPresent()) {
            List<Movie> movies = optionalMovies.get();
            for (Movie movie : movies) {
                moviesDto.add(movieThinMapper.toDto(movie));
            }
        }
        logger.info("Delivered movies {} by genre: {}. Column for sorting - {},  sorting type - {}",
                moviesDto,  genre, sortingCredentials.getSortingField(), sortingCredentials.getSortDirection());
        return moviesDto;
    }

    public MovieThickMapper getMovieById() {
        return null;
    }
}
