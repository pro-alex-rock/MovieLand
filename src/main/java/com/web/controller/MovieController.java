package com.web.controller;

import com.dto.MovieDto;
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
@RequestMapping(path = {"/", "/api/v1/movie"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @ResponseBody
    public List<MovieDto> getAll() {
        return movieService.getAll();
    }

    @GetMapping(path = "/random")
    @ResponseBody
    public List<MovieDto> getRandom() { //TODO
        return movieService.getAll();
    }
}
