package com.dao.mapper;

import com.dto.MovieDto;
import com.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    private final ModelMapper mapper;

    public MovieMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public MovieDto toDto(Movie movie) {
        if (movie == null) {
            throw new RuntimeException("The field 'movie' cannot be null.");
        }
        return mapper.map(movie, MovieDto.class);
    }

    public Movie toEntity(MovieDto movieDto) {
        if (movieDto == null) {
            throw new RuntimeException("The field 'movieDto' cannot be null.");
        }
        return mapper.map(movieDto, Movie.class);
    }
}
