package com.web.controller;

import com.configuration.RootConfig;
import com.configuration.SpringConfig;
import com.dao.MovieDao;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.model.Movie;
import com.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class, RootConfig.class })
@WebAppConfiguration
@DBRider
class MovieControllerITest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieDao movieDao;

    @Test
    @DataSet(value = {"resources/movies.json"})
    public void shouldGetMovieFromDB() {
        Optional<List<Movie>> optionalMovies = movieDao.getAllMovie();
        assertFalse(optionalMovies.isEmpty());
        assertEquals(2, optionalMovies.get().size());
    }

    @Test
    public void shouldCallMethodOneTime() {
        MovieService movieService = mock(MovieService.class);
        Movie movie2 = new Movie();
        movieService.addMovie(movie2);
        verify(movieService, times(1));
    }

}