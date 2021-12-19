package com.dao;

import com.dao.mapper.GenreRowMapper;
import com.dto.GenreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreDao {
    private static final Logger logger = LoggerFactory.getLogger(GenreDao.class);

    private final JdbcTemplate jdbcTemplate;
    private final GenreRowMapper genreRowMapper = new GenreRowMapper();

    public GenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<List<GenreDto>> getAllGenres() {
        List<GenreDto> genresDto = jdbcTemplate.query(
                "SELECT genre_id, genre FROM genre"
                ,  genreRowMapper);
        logger.info("Selected list of genres: {}", genresDto);
        return Optional.of(genresDto);
    }
}
