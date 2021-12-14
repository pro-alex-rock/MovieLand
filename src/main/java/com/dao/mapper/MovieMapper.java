package com.dao.mapper;

import com.dto.MovieDto;
import com.model.Movie;

public interface MovieMapper {
    MovieDto toDto(Movie movie);

    Movie toEntity(MovieDto movieDto);
}
