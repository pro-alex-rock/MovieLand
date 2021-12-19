package com.dao.mapper;

import com.dto.GenreDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper  implements RowMapper<GenreDto> {
    @Override
    public GenreDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenreDto genreDto = new GenreDto();
        genreDto.setGenreId(rs.getInt("genre_id"));
        genreDto.setName(rs.getString("genre"));
        return null;
    }
}
