package com.repository.mapper;

import com.dto.ThinMovieDto;
import com.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieThinMapper {
    ThinMovieDto toDto(Movie movie);
    Movie toEntity(ThinMovieDto thinMovieDto);
}
