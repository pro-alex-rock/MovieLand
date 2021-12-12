package com.web.controller;

import com.dao.MovieDao;
import com.dto.MovieDto;
import com.model.Genre;
import com.model.Movie;
import com.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private MovieService movieService;

    @Test
    public void shouldGetMovieFromDB() {
        Movie movie = new Movie();
        movie.setTitleRussianMovie("Название");
        movie.setTitleNativeMovie("Title");
        movie.setYearOfRelease(2000);
        movie.setCountry("Country");
        movie.setGenre(Genre.setGenre("action"));
        movie.setDescription("Description");
        movie.setRating(8.0);
        movie.setPrice(new BigDecimal(10));
        movie.setPosterLink("Poster_link");

        MovieDto expectedMovieDto = new MovieDto();
        expectedMovieDto.setTitleRussianMovie("Название");
        expectedMovieDto.setTitleNativeMovie("Title");
        expectedMovieDto.setYearOfRelease(2000);
        expectedMovieDto.setRating(8.0);
        expectedMovieDto.setPrice(new BigDecimal(10));

        movieService.addMovie(movie);
        List<MovieDto> movieDtos = movieService.getAll();
        MovieDto actualMovieDto = movieDtos.get(0);

        assertTrue(movieDtos.size() > 0);
        assertEquals(expectedMovieDto, actualMovieDto);
    }

}