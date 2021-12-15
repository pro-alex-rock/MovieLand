package com.dao;

import com.dao.mapper.GenreRowMapper;
import com.dao.mapper.MovieRowMapper;
import com.dto.GenreDto;
import com.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Optional<List<Movie>> getAllMovie(String columnName, String sortingType) {
        List<Movie> movies = jdbcTemplate.query(
                "SELECT movie_id, title_russian, title_native, year" +
                        ", rating, price, poster_link FROM movie ORDER BY " + columnName + " " + sortingType
                ,  new CustomMovieMapper());
        logger.info("Selected list of movies: {}. Column for sorting - {},  sorting type - {}", movies, columnName, sortingType);
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

    public Optional<List<GenreDto>> getAllGenres() {
        List<GenreDto> genresDto = jdbcTemplate.query(
                "SELECT movie_id, genre FROM movie"
                ,  new GenreRowMapper());
        logger.info("Selected list of genres: {}", genresDto);
        return Optional.of(genresDto);
    }


    public Optional<List<Movie>> getMoviesByGenre(String genre, String columnName, String sortingType) {
        List<Movie> movies = jdbcTemplate.query(
                "SELECT movie_id, title_russian, title_native, year" +
                        ", rating, price, poster_link FROM movie WHERE genre = " + genre + " ORDER BY " + columnName + " " + sortingType,
                new CustomMovieMapper());
        logger.info("Selected list of movies {} by genre: {}. Column for sorting - {},  sorting type - {}", movies, genre, columnName, sortingType);
        return Optional.of(movies);
    }

    private static class CustomMovieMapper implements RowMapper<Movie> {

        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie movie = new Movie();
            movie.setId(rs.getInt("id"));
            movie.setNameRussian(rs.getString("title_russian"));
            movie.setNameNative(rs.getString("title_native"));
            movie.setYearOfRelease(rs.getInt("year"));
            movie.setRating(rs.getDouble("rating"));
            movie.setPrice(rs.getBigDecimal("price"));
            movie.setPicturePath(rs.getString("poster_link"));
            return movie;
        }
    }
}
