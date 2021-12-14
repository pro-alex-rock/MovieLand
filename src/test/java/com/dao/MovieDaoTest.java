package com.dao;

import com.IntegrationTestBase;
import com.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MovieDaoTest extends IntegrationTestBase {

    @Autowired
    private MovieDao movieDao;

    @Test
    public void shouldGetMovieFromDB() {
        Optional<List<Movie>> optionalMovies = movieDao.getAllMovie();
        assertTrue(optionalMovies.isPresent());
        optionalMovies.ifPresent(entity -> {
            assertEquals("Матрица", entity.get(0).getTitleRussian());
            assertEquals("Matrix", entity.get(0).getTitleNative());
            assertEquals(1999, entity.get(0).getYearOfRelease());
            assertEquals(1999, entity.get(0).getYearOfRelease());
            assertEquals("фантастика", entity.get(0).getGenre());
            assertEquals("Мир Матрицы — это иллюзия", contains(entity.get(0).getDescription()));
            assertEquals(10.0, entity.get(0).getRating());
            assertEquals(100.00, entity.get(0).getPrice());
            assertEquals("Matrix_reloaded.jpg", contains(entity.get(0).getPosterLink()));
        });
    }

    @Test
    public void shouldCallMethodOneTime() {
        verify(movieDao, times(0));
        movieDao.getAllMovie();
        verify(movieDao, times(1));
    }

    @Test
    public void shouldGetThreeRandomMoviesFromDB() {
        Optional<List<Movie>> optionalMovies = movieDao.getRandom();
        assertTrue(optionalMovies.isPresent());
        assertEquals(optionalMovies.get().size(), 3);
        verify(movieDao, times(1));
    }

    @Test
    public void shouldGetAllMoviesByGenre() {
        Optional<List<Movie>> optionalMovies = movieDao.getMoviesByGenre("фантастика");
        assertTrue(optionalMovies.isPresent());
        assertTrue(optionalMovies.get().size() > 0);
        assertEquals(optionalMovies.get().size(), 2);
    }
}