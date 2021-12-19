package com.dao.mapper;

import com.dao.mapper.modelMapper.DefaultMapper;
import com.dto.MovieDto;
import com.model.Movie;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = { SpringConfig.class, RootConfig.class })
class DefaultMapperTest {

    private DefaultMapper movieMapper = Mappers.getMapper(DefaultMapper.class);

    @Test
    public void shouldGetMovieFromDB() {
        Movie movie = new Movie();
        movie.setNameRussian("Название");
        movie.setNameNative("Title");
        movie.setYearOfRelease(2000);
        movie.setCountry("Country");
        movie.setGenre("action");
        movie.setDescription("Description");
        movie.setRating(8.0);
        movie.setPrice(new BigDecimal(10));
        movie.setPicturePath("Poster_link");

        MovieDto movieDto = movieMapper.toDto(movie);
        assertEquals(movie.getNameRussian(), movieDto.getNameRussian());
        assertEquals(movie.getNameNative(), movieDto.getNameNative());
        assertEquals(movie.getYearOfRelease(), movieDto.getYearOfRelease());
        assertEquals(movie.getRating(), movieDto.getRating());
        assertEquals(movie.getPrice(), movieDto.getPrice());
        assertEquals(movie.getPicturePath(), movieDto.getPicturePath());
    }

    @Test
    public void shouldConvertMovieToDto() {
        Movie movie = new Movie();
        movie.setNameRussian("Имя");
        movie.setNameNative("Name");
        movie.setYearOfRelease(1000);
        MovieDto movieDto = movieMapper.toDto(movie);
        assertEquals(movie.getNameRussian(), movieDto.getNameRussian());
        assertEquals(movie.getNameNative(), movieDto.getNameNative());
        assertEquals(movie.getYearOfRelease(), movieDto.getYearOfRelease());
    }

    @Test
    public void shouldCallMethodOneTime() {
        Movie movie = new Movie();
        movieMapper = mock(DefaultMapper.class);
        movieMapper.toDto(movie);
        verify(movieMapper, times(1));
    }
}