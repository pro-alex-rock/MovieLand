package com.web.controller;


import com.dto.GenreDto;
import com.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = {"/v1/genre"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);
    private final MovieService movieService;

    public GenreController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    @ResponseBody
    public List<GenreDto> getAllGenres() {
        logger.info("Came request to get all genres.");
        return movieService.getAllGenres();
    }
}
