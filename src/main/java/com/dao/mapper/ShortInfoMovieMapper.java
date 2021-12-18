package com.dao.mapper;

import com.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShortInfoMovieMapper implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt("movie_id"));
        movie.setNameRussian(rs.getString("title_russian"));
        movie.setNameNative(rs.getString("title_native"));
        movie.setYearOfRelease(rs.getInt("year"));
        movie.setRating(rs.getDouble("rating"));
        movie.setPrice(rs.getBigDecimal("price"));
        movie.setPicturePath(rs.getString("poster_link"));
        return movie;
    }
}
