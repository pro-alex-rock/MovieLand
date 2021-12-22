package com.repository;

import com.IntegrationTestBase;
import com.entity.Movie;
import com.model.SortingCredentials;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MovieDaoTest extends IntegrationTestBase {

    @Autowired
    private MovieDao movieDao;
    private final SortingCredentials sortingCredentials = new SortingCredentials();

    @Test
    public void shouldGetMovieFromDB() {
        Optional<List<Movie>> optionalMovies = movieDao.getAllMovie(sortingCredentials);
        assertTrue(optionalMovies.isPresent());
        optionalMovies.ifPresent(entity -> {
            assertEquals("Матрица", entity.get(0).getNameRussian());
            assertEquals("Matrix", entity.get(0).getNameNative());
            assertEquals(1999, entity.get(0).getYearOfRelease());
            assertEquals(1999, entity.get(0).getYearOfRelease());
            assertEquals("фантастика", entity.get(0).getGenres());
            assertEquals("Мир Матрицы — это иллюзия", contains(entity.get(0).getDescription()));
            assertEquals(10.0, entity.get(0).getRating());
            assertEquals(new BigDecimal("100.00"), entity.get(0).getPrice());
            assertEquals("Matrix_reloaded.jpg", contains(entity.get(0).getPicturePath()));
        });
    }

    @Test
    public void shouldCallGetAllMovieTime() {
        verify(movieDao, times(0));
        movieDao.getAllMovie(sortingCredentials);
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
        Optional<List<Movie>> optionalMovies = movieDao.getMoviesByGenre("фантастика", sortingCredentials);
        assertTrue(optionalMovies.isPresent());
        assertTrue(optionalMovies.get().size() > 0);
        assertEquals(optionalMovies.get().size(), 2);
    }
}