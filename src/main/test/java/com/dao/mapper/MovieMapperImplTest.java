package com.dao.mapper;

import com.dao.MovieDao;
import com.dto.MovieDto;
import com.model.Movie;
import com.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieMapperImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private MovieDao movieDao;
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;

    @Test
    public void shouldGetMovieFromDB() {
        Movie movie = new Movie();
        movie.setTitleRussianMovie("Название");
        movie.setTitleNativeMovie("Title");
        movie.setYearOfRelease(2000);
        movie.setCountry("Country");
        movie.setGenre("action");
        movie.setDescription("Description");
        movie.setRating(8.0);
        movie.setPrice(new BigDecimal(10));
        movie.setPosterLink("Poster_link");

        MovieDto movieDto = movieMapper.toDto(movie);
        MovieDto actualMovie = movieService.getAll().get(0);
        assertEquals(movieDto, actualMovie);
    }

}