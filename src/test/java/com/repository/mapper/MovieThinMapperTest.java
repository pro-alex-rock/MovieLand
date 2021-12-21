package com.repository.mapper;

import com.dto.ThinMovieDto;
import com.repository.mapper.modelMapper.MovieThinMapper;
import com.entity.Movie;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class MovieThinMapperTest {

    private MovieThinMapper movieThinMapper = Mappers.getMapper(MovieThinMapper.class);

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

        ThinMovieDto thinMovieDto = movieThinMapper.toDto(movie);
        assertEquals(movie.getNameRussian(), thinMovieDto.getNameRussian());
        assertEquals(movie.getNameNative(), thinMovieDto.getNameNative());
        assertEquals(movie.getYearOfRelease(), thinMovieDto.getYearOfRelease());
        assertEquals(movie.getRating(), thinMovieDto.getRating());
        assertEquals(movie.getPrice(), thinMovieDto.getPrice());
        assertEquals(movie.getPicturePath(), thinMovieDto.getPicturePath());
    }

    @Test
    public void shouldConvertMovieToDto() {
        Movie movie = new Movie();
        movie.setNameRussian("Имя");
        movie.setNameNative("Name");
        movie.setYearOfRelease(1000);
        ThinMovieDto thinMovieDto = movieThinMapper.toDto(movie);
        assertEquals(movie.getNameRussian(), thinMovieDto.getNameRussian());
        assertEquals(movie.getNameNative(), thinMovieDto.getNameNative());
        assertEquals(movie.getYearOfRelease(), thinMovieDto.getYearOfRelease());
    }

    @Test
    public void shouldCallMethodOneTime() {
        Movie movie = new Movie();
        movieThinMapper = mock(MovieThinMapper.class);
        movieThinMapper.toDto(movie);
        verify(movieThinMapper, times(1));
    }
}