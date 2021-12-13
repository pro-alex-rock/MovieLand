package com.web.controller;

import com.configuration.RootConfig;
import com.configuration.SpringConfig;
import com.model.Movie;
import com.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class, RootConfig.class })
@WebAppConfiguration
class MovieControllerITest {

    @Autowired
    private MovieService movieService;

    /*@Test
    public void shouldGetMovieFromDB() {
        Movie movie = new Movie();
        movie.setTitleRussianMovie("Название");
        movie.setTitleNativeMovie("Title");
        movie.setYearOfRelease(2000);
        movie.setCountry("Country");
        movie.setGenre(Genre.setGenre("action"));
        movie.setDescription("Description");
        movie.setRating(8.0);
        movie.setPrice(new BigDecimal(10.00));
        movie.setPosterLink("Poster_link");

        MovieDto expectedMovieDto = new MovieDto();
        expectedMovieDto.setTitleRussianMovie("Название");
        expectedMovieDto.setTitleNativeMovie("Title");
        expectedMovieDto.setYearOfRelease(2000);
        expectedMovieDto.setRating(8.0);
        expectedMovieDto.setPrice(new BigDecimal(10.00));

        movieService.addMovie(movie);
        List<MovieDto> movieDtos = movieService.getAll();
        MovieDto actualMovieDto = movieDtos.get(0);

        assertTrue(movieDtos.size() > 0);
        assertEquals(expectedMovieDto.getTitleRussianMovie(), actualMovieDto.getTitleRussianMovie());
        assertEquals(expectedMovieDto.getTitleNativeMovie(), actualMovieDto.getTitleNativeMovie());
        assertEquals(expectedMovieDto.getYearOfRelease(), actualMovieDto.getYearOfRelease());
        assertEquals(expectedMovieDto.getPicturePath(), actualMovieDto.getPicturePath());
        assertEquals(expectedMovieDto.getRating(), actualMovieDto.getRating());
    }*/

    @Test
    public void shouldCallMethodOneTime() {
        MovieService movieService = mock(MovieService.class);
        Movie movie2 = new Movie();
        movieService.addMovie(movie2);
        verify(movieService, times(1));
    }

}