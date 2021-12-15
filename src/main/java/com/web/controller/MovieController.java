package com.web.controller;

import com.dto.MovieDto;
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
    public List<MovieDto> getAll(@RequestParam(value = "rating", required = false) String rating
            , @RequestParam(value = "price ", required = false) String price) {
        String columnName = (rating != null ? "rating" : (price != null ? "price" : "nameRussian"));
        String sortingType = (rating != null ? rating : (price != null ? price : "asc"));
        logger.info("Came request to get all movies. Column for sorting - {},  sorting type - {}", columnName, sortingType);
        return movieService.getAll(columnName, sortingType);
    }

    @GetMapping(path = "/random")
    public List<MovieDto> getRandom() {
        logger.info("Came request to get random three movies.");
        return movieService.getRandom();
    }

    @GetMapping(path = "/genre/{genreId}/")
    public List<MovieDto> getMoviesByGenre(@PathVariable("genreId") String genreId
            , @RequestParam(value = "rating", required = false) String rating
            , @RequestParam(value = "price ", required = false) String price) {
        String columnName = (rating != null ? "rating" : (price != null ? "price" : "nameRussian"));
        String sortingType = (rating != null ? rating : (price != null ? price : "asc"));
        logger.info("Came request to get movies by genre '{}'. Column for sorting - {},  sorting type - {}", columnName, sortingType, genreId);
        return movieService.getMoviesByGenre(genreId, columnName, sortingType);
    }
}
