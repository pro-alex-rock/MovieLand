package com.web.controller;


import com.dto.GenreDto;
import com.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/v1/genre"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping()
    public List<GenreDto> getAllGenres() {
        logger.info("Came request to get all genres.");
        return genreService.getAllGenres();
    }


}
