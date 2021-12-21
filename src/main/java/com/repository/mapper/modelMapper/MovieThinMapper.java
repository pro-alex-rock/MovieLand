package com.repository.mapper.modelMapper;

import com.dto.ThinMovieDto;
import com.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieThinMapper {
    ThinMovieDto toDto(Movie movie);
    Movie toEntity(ThinMovieDto thinMovieDto);
}
