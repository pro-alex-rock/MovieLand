package com.dao.mapper;

import com.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {

        Movie movie = new Movie();
        movie.setId(resultSet.getInt("movie_id"));
        movie.setTitleRussian(resultSet.getString("title_russian"));
        movie.setTitleNative(resultSet.getString("title_native"));
        movie.setYearOfRelease(resultSet.getInt("year"));
        movie.setCountry(resultSet.getString("country"));
        movie.setGenre(resultSet.getString("genre"));
        movie.setDescription(resultSet.getString("description"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setPrice(resultSet.getBigDecimal("price"));
        movie.setPosterLink(resultSet.getString("poster_link"));
        return movie;
    }
}
