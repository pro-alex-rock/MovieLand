package com.web.controller;

import com.dto.ThickMovieDto;
import com.dto.ThinMovieDto;
import com.entity.Movie;
import com.model.SortDirection;
import com.model.SortingCredentials;
import com.model.SortingField;
import com.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/api/v1/movie"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER, ROLE_GUEST')")
    public List<ThinMovieDto> getAll(@RequestParam(value = "direction", required = false) SortDirection sortDirection
            , @RequestParam(value = "field ", required = false) SortingField sortingField) {
        logger.info("Came request to get all movies. Column for sorting - {},  sorting direction - {}", sortingField, sortDirection);
        return movieService.getAll(new SortingCredentials(sortDirection, sortingField));
    }

    @GetMapping(path = "/random")
    public List<ThinMovieDto> getRandom() {
        List<ThinMovieDto> moviesDto = movieService.getRandom();
        logger.info("Came request to get random movies, count - {}.", moviesDto.size());
        return moviesDto;
    }

    @GetMapping(path = "/genre/{genreId}/")
    public List<ThinMovieDto> getMoviesByGenre(@PathVariable("genreId") int genreId
            , @RequestParam(value = "rating", required = false) SortDirection sortDirection
            , @RequestParam(value = "price ", required = false) SortingField sortingField) {
        logger.info("Came request to get movies by genre '{}'. Column for sorting - {},  sorting direction - {}", sortingField, sortDirection, genreId);
        return movieService.getMoviesByGenre(genreId, new SortingCredentials(sortDirection, sortingField));
    }

    @GetMapping(path = "/{movieId}")
    public Movie getMovieById(@PathVariable("movieId") int movieId) {
        Movie movie = movieService.getMovieById(movieId);
        logger.info("Came request to get movie by id '{}'.", movieId);
        return movie;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:manage')")
    public void addMovie(@RequestBody ThickMovieDto movieDto) {
        logger.info("Came request to add new movie: {}.", movieDto);
        movieService.addMovie(movieDto);
    }

    @PatchMapping(path = "/{movieId}")
    @PreAuthorize("hasAuthority('user:manage')")
    public void editMovie(@PathVariable("movieId") int movieId, @RequestBody ThickMovieDto movieDto) {
        logger.info("Came request to edit movie with id: {}. Movie: {}", movieId, movieDto);
        movieService.editMovie(movieId, movieDto);
    }

    @DeleteMapping(path = "/{movieId}")
    @PreAuthorize("hasAuthority('user:manage')")
    public void deleteMovie(@PathVariable("movieId") int movieId) {
        logger.info("Came request to delete movie with id: {}.", movieId);
        movieService.deleteMovie(movieId);
    }
}
