package com.repository.mapper.modelMapper;

import com.dto.ThickMovieDto;
import com.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieThickMapper {
    ThickMovieDto toDto(Movie movie);
    Movie toEntity(ThickMovieDto thickMovieDto);
}
