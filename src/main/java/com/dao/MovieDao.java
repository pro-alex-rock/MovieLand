package com.dao;

import com.dao.mapper.MovieRowMapper;
import com.dto.MovieDto;
import com.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDao {
    private static final Logger logger = LoggerFactory.getLogger(MovieDao.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<Movie>> getAllMovie() {
        List<Movie> movies = jdbcTemplate.query(
                "SELECT movie_id, title_russian, title_native, year" +
                        ", rating, price, poster_link FROM movie"
                ,  new MovieRowMapper());
        logger.info("Selected list of movies: {}", movies);
        return Optional.of(movies);
    }

    public Optional<List<Movie>> getRandom() {
        List<Movie> movies = jdbcTemplate.query(
                "SELECT movie_id, title_russian, title_native, year" +
                        ", rating, price, poster_link FROM movie WHERE movie_id IN" +
                        "(SELECT movie_id FROM movie ORDER BY random() LIMIT 3)"
                ,  new MovieRowMapper());
        logger.info("Selected 3 random movies: {}", movies);
        return Optional.of(movies);
    }

    public void addMovie(Movie movie) {
        jdbcTemplate.update("INSERT INTO movie (title_russian_movie, title_native_movie, year, country" +
                        ", genre, description, rating, price, poster_link) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                movie.getTitleRussianMovie(), movie.getTitleNativeMovie(), movie.getYearOfRelease()
                , movie.getCountry(), movie.getGenre().toString(), movie.getDescription(), movie.getRating()
                , movie.getPrice(), movie.getPosterLink());
        logger.info("The movie {} created.", movie);
    }
}
