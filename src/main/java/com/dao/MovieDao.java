package com.dao;

import com.dao.mapper.GeneralMovieRowMapper;
import com.dao.mapper.ShortInfoMovieMapper;
import com.model.Movie;
import com.model.SortingCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDao {
    private static final Logger logger = LoggerFactory.getLogger(MovieDao.class);

    private final JdbcTemplate jdbcTemplate;
    private final GeneralMovieRowMapper generalMovieRowMapper = new GeneralMovieRowMapper();
    private final ShortInfoMovieMapper shortInfoMovieMapper = new ShortInfoMovieMapper();

    public MovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<Movie>> getAllMovie(SortingCredentials sortingCredentials) {
        String sortingDir = sortingCredentials.getSortDirection().getValue();
        String sortField = sortingCredentials.getSortingField().getValue();
        List<Movie> movies = jdbcTemplate.query(
                "SELECT movie_id, title_russian, title_native, year" +
                        ", rating, price, poster_link FROM movie ORDER BY ?, ?",
                shortInfoMovieMapper, sortField, sortingDir);
        logger.info("Selected list of movies: {}. Column for sorting - {},  sorting type - {}", movies, sortField, sortingDir);
        return Optional.of(movies);
    }

    public Optional<List<Movie>> getRandom() {
        List<Movie> movies = jdbcTemplate.query(
                "SELECT movie_id, title_russian, title_native, year" +
                        ", rating, price, poster_link FROM movie WHERE movie_id IN" +
                        "(SELECT movie_id FROM movie ORDER BY random() LIMIT 3)"
                ,  generalMovieRowMapper);
        logger.info("Selected random movies: {}, count - {}", movies, movies.size());
        return Optional.of(movies);
    }


    public Optional<List<Movie>> getMoviesByGenre(String genre, SortingCredentials sortingCredentials) {
        String sortingDir = sortingCredentials.getSortDirection().getValue();
        String sortField = sortingCredentials.getSortingField().getValue();
        List<Movie> movies = jdbcTemplate.query(
                "SELECT movie_id, title_russian, title_native, year" +
                        ", rating, price, poster_link FROM movie WHERE genre = ? ORDER BY ? ? ",
                shortInfoMovieMapper, genre, sortField, sortingDir);
        logger.info("Selected list of movies {} by genre: {}. Column for sorting - {},  sorting type - {}", movies, genre, sortField, sortingDir);
        return Optional.of(movies);
    }
}
