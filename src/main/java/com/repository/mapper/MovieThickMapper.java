package com.repository.mapper;

import com.dto.ThickMovieDto;
import com.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieThickMapper {
    ThickMovieDto toDto(Movie movie);
    Movie toEntity(ThickMovieDto thickMovieDto);
}
