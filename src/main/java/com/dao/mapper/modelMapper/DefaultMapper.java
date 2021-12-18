package com.dao.mapper.modelMapper;

import com.dto.MovieDto;
import com.model.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefaultMapper {
    MovieDto toDto(Movie movie);
    Movie toEntity(MovieDto movieDto);
}
