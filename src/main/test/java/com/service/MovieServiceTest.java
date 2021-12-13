package com.service;

import com.configuration.RootConfig;
import com.configuration.SpringConfig;
import com.dao.MovieDao;
import com.dto.MovieDto;
import com.model.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class, RootConfig.class })
@WebAppConfiguration
class MovieServiceTest {

    private MovieDao movieDao = mock(MovieDao.class);
    private DataSource dataSource = mock(DataSource.class);
    @Autowired
    private MovieService movieService;

    @Test
    public void shouldTransformMovieToDto() {
        Movie movie = new Movie();
        movie.setTitleRussianMovie("Название");
        movie.setTitleNativeMovie("Title");
        movie.setYearOfRelease(2000);
        movie.setCountry("Country");
        movie.setGenre("action");
        movie.setDescription("Description");
        movie.setRating(8.0);
        movie.setPrice(new BigDecimal("10.00"));
        movie.setPosterLink("Poster_link");

        MovieDto expectedMovieDto = new MovieDto();
        expectedMovieDto.setTitleRussian("Название");
        expectedMovieDto.setTitleNative("Title");
        expectedMovieDto.setYearOfRelease(2000);
        expectedMovieDto.setRating(8.0);
        expectedMovieDto.setPrice(new BigDecimal("10.00"));
        List<MovieDto> expectedMoviesDto = List.of(expectedMovieDto);

        when(movieDao.getAllMovie()).thenReturn(Optional.of(List.of(movie)));
        List<MovieDto> actualMoviesDto = movieService.getAll();
        Assertions.assertEquals(expectedMoviesDto, actualMoviesDto);
        Assertions.assertEquals(expectedMoviesDto.size(), actualMoviesDto.size());
        verify(movieService, times(1));
    }

}