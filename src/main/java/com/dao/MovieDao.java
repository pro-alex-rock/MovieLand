package com.dao;

import com.dao.mapper.MovieRowMapper;
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

    public Optional<List<Movie>> getAll() {
        List<Movie> movies = jdbcTemplate.query(
                "SELECT movie_id, title_russian_movie, title_native_movie, year, country" +
                        ", genre_id, description, rating, price, poster_link FROM movie" +
                        "JOIN poster ON movie.movie_id = poster.poster_id"
                ,  new MovieRowMapper());
        logger.info("Selected list of movies: {}", movies);
        return Optional.of(movies);
    }

    public void addMovie(Movie movie) {
        jdbcTemplate.update("INSERT INTO movie (title_russian_movie, title_native_movie, year, country" +
                        ", genre_id, description, rating, price) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                movie.getTitleRussianMovie(), movie.getTitleNativeMovie(), movie.getYearOfRelease()
                , movie.getCountry(), movie.getGenre(), movie.getDescription(), movie.getRating()
                , movie.getPrice());
        jdbcTemplate.update("INSERT INTO poster (movie_id, poster_link) VALUES (?, ?)", movie.getId()
                , movie.getPosterLink());
        logger.info("The movie {} created.", movie);
    }
}
