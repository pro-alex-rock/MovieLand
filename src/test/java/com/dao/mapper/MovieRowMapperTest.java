package com.dao.mapper;

import com.model.Movie;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class MovieRowMapperTest {

    @Test
    public void testMapRowWithProperMovie() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        MovieRowMapper movieMapper = new MovieRowMapper();
        when(resultSet.getInt("movie_id")).thenReturn(0);
        when(resultSet.getString("title_russian")).thenReturn("Название");
        when(resultSet.getString("title_native")).thenReturn("Title");
        when(resultSet.getInt("year")).thenReturn(2000);
        Movie actualMovie = movieMapper.mapRow(resultSet, 0);

        assertEquals(actualMovie.getId(), 0);
        assertEquals(actualMovie.getTitleRussian(), "Название");
        assertEquals(actualMovie.getTitleNative(), "Title");
        assertEquals(actualMovie.getYearOfRelease(), 2000);
    }

    @Test
    public void shouldCallMethodMapRowOneTime() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        MovieRowMapper movieMapper = mock(MovieRowMapper.class);
        movieMapper.mapRow(resultSet, 0);
        verify(movieMapper, times(1));
    }
}