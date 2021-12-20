package com.repository.mapper.modelMapper;

import com.dto.MovieDto;
import com.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieMapper {
    MovieDto toDto(Movie movie);
    Movie toEntity(MovieDto movieDto);
}
