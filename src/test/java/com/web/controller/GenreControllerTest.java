package com.web.controller;

import com.IntegrationTestBase;
import com.dao.MovieDao;
import com.dto.GenreDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GenreControllerTest extends IntegrationTestBase {

    @Autowired
    private MovieDao movieDao;

    @Test
    public void shouldGetAllGenresFromDB() {
        Optional<List<GenreDto>> optionalGenres = movieDao.getAllGenres();
        assertTrue(optionalGenres.isPresent());
        optionalGenres.ifPresent(entity -> {
            assertEquals("фантастика", entity.get(0).getName());
            assertEquals("драма", entity.get(2).getName());
        });
        assertTrue(optionalGenres.get().size() > 0);
        assertEquals(optionalGenres.get().size(), 4);
    }

    @Test
    public void shouldCallMethodOneTime() {
        verify(movieDao, times(0));
        movieDao.getAllGenres();
        verify(movieDao, times(1));
    }

}