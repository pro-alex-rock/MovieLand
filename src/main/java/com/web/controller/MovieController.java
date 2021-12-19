package com.web.controller;

import com.dto.MovieDto;
import com.model.SortDirection;
import com.model.SortingCredentials;
import com.model.SortingField;
import com.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/", "/api/v1/movie"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getAll(@RequestParam(value = "direction", required = false) SortDirection sortDirection
            , @RequestParam(value = "field ", required = false) SortingField sortingField) {
        logger.info("Came request to get all movies. Column for sorting - {},  sorting direction - {}", sortingField, sortDirection);
        return movieService.getAll(new SortingCredentials(sortDirection, sortingField));
    }

    @GetMapping(path = "/random")
    public List<MovieDto> getRandom() {
        List<MovieDto> moviesDto = movieService.getRandom();
        logger.info("Came request to get random movies, count - {}.", moviesDto.size());
        return moviesDto;
    }

    @GetMapping(path = "/genre/{genreId}/")
    public List<MovieDto> getMoviesByGenre(@PathVariable("genreId") String genreId
            , @RequestParam(value = "rating", required = false) SortDirection sortDirection
            , @RequestParam(value = "price ", required = false) SortingField sortingField) {
        logger.info("Came request to get movies by genre '{}'. Column for sorting - {},  sorting direction - {}", sortingField, sortDirection, genreId);
        return movieService.getMoviesByGenre(genreId, new SortingCredentials(sortDirection, sortingField));
    }
}
