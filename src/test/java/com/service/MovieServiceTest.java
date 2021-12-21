package com.service;

import com.dto.ThinMovieDto;
import com.repository.MovieDao;
import com.entity.Movie;
import com.model.SortingCredentials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class MovieServiceTest {

    private final MovieDao movieDao = mock(MovieDao.class);
    @Autowired
    private MovieService movieService;
    private final SortingCredentials sortingCredentials = new SortingCredentials();

    @Test
    public void shouldTransformMovieToDto() {
        Movie movie = new Movie();
        movie.setNameRussian("Название");
        movie.setNameNative("Title");
        movie.setYearOfRelease(2000);
        movie.setGenre("action");
        movie.setDescription("Description");
        movie.setRating(8.0);
        movie.setPrice(new BigDecimal("10.00"));
        movie.setPicturePath("Poster_link");

        ThinMovieDto expectedThinMovieDto = new ThinMovieDto();
        expectedThinMovieDto.setNameRussian("Название");
        expectedThinMovieDto.setNameNative("Title");
        expectedThinMovieDto.setYearOfRelease(2000);
        expectedThinMovieDto.setRating(8.0);
        expectedThinMovieDto.setPrice(new BigDecimal("10.00"));
        List<ThinMovieDto> expectedMoviesDto = List.of(expectedThinMovieDto);

        when(movieService.getAll(sortingCredentials)).thenReturn(List.of(expectedThinMovieDto));
        when(movieDao.getAllMovie(sortingCredentials)).thenReturn(Optional.of(List.of(movie)));
        List<ThinMovieDto> actualMoviesDto = movieService.getAll(sortingCredentials);
        Assertions.assertEquals(expectedMoviesDto.get(0).getNameRussian(), actualMoviesDto.get(0).getNameRussian());
        Assertions.assertEquals(expectedMoviesDto.get(0).getNameNative(), actualMoviesDto.get(0).getNameNative());
        Assertions.assertEquals(expectedMoviesDto.get(0).getYearOfRelease(), actualMoviesDto.get(0).getYearOfRelease());
        Assertions.assertEquals(expectedMoviesDto.get(0).getRating(), actualMoviesDto.get(0).getRating());
        Assertions.assertEquals(expectedMoviesDto.get(0).getPrice(), actualMoviesDto.get(0).getPrice());
        Assertions.assertEquals(expectedMoviesDto.size(), actualMoviesDto.size());
        verify(movieService, times(1));
    }

}