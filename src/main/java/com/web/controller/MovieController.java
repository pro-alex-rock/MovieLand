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
    public List<MovieDto> getAll() {
        logger.info("Came request to get all movies.");
        return movieService.getAll();
    }

    @GetMapping(path = "/random")
    public List<MovieDto> getRandom() {
        logger.info("Came request to get random three movies.");
        return movieService.getRandom();
    }

    @GetMapping(path = "/genre/{genreId}")
    public List<MovieDto> getMoviesByGenre(@PathVariable("genreId") String genreId) {
        logger.info("Came request to get movies by genre '{}'", genreId);
        return movieService.getMoviesByGenre(genreId);
    }
}
