package com.service;

import com.dto.ThickMovieDto;
import com.dto.ThinMovieDto;
import com.entity.Genre;
import com.entity.Movie;
import com.model.SortingCredentials;
import com.repository.MovieRepository;
import com.repository.mapper.MovieThickMapper;
import com.repository.mapper.MovieThinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;
    private final GenreService genreService;
    private final MovieThinMapper movieThinMapper;
    private final MovieThickMapper movieThickMapper;

    public MovieService(MovieRepository movieRepository, GenreService genreService, MovieThinMapper movieThinMapper, MovieThickMapper movieThickMapper) {
        this.movieRepository = movieRepository;
        this.genreService = genreService;
        this.movieThinMapper = movieThinMapper;
        this.movieThickMapper = movieThickMapper;
    }

    public List<ThinMovieDto> getAll(SortingCredentials sortingCredentials) {
        String sortingDirection = sortingCredentials.getSortDirection().getValue();
        String sortField = sortingCredentials.getSortingField().getValue();

        List<Movie> movies = movieRepository.findAll(Sort.by(sortingDirection, sortField));
        List<ThinMovieDto> moviesDto = movies.stream().map(movieThinMapper::toDto).collect(Collectors.toList());
            logger.info("Delivered all movies: {}. Column for sorting - {},  sorting type - {}",
                    moviesDto, sortingCredentials.getSortingField(), sortingCredentials.getSortDirection());
        return moviesDto;
    }

    public List<ThinMovieDto> getRandom() {
        List<Movie> movies = movieRepository.findRandomMovie();
        List<ThinMovieDto> moviesDto = movies.stream().map(movieThinMapper::toDto).collect(Collectors.toList());
        logger.info("Delivered random movies: {}, count - {}", moviesDto, moviesDto.size());
        return moviesDto;
    }

    public List<ThinMovieDto> getMoviesByGenre(int genreId, SortingCredentials sortingCredentials) {
        Genre genre = genreService.getGenreById(genreId);
        List<Movie> movies = movieRepository.findAllMoviesByGenreId(genre);
        List<ThinMovieDto> moviesDto = movies.stream().map(movieThinMapper::toDto).collect(Collectors.toList());
        logger.info("Delivered movies {} by genreId: {}. Column for sorting - {},  sorting type - {}",
                moviesDto,  genreId, sortingCredentials.getSortingField(), sortingCredentials.getSortDirection());
        return moviesDto;
    }

    public Movie getMovieById(int id) {
        Movie movie = movieRepository.findById(id);
        logger.info("Delivered movie {} by Id: {}.", movie,  id);
        return movie;
    }

    public void addMovie(ThickMovieDto movieDto) {
        Movie movie = movieThickMapper.toEntity(movieDto);
        movieRepository.save(movie);
        logger.info("Added new movie: {}.", movie);
    }

    public void deleteMovie(int movieId) {
        movieRepository.deleteById(movieId);
        logger.info("Deleted movie with id: {}.", movieId);
    }

    public void editMovie(int movieId, ThickMovieDto movieDto) {

    }
}
