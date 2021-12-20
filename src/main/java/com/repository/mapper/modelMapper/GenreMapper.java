package com.repository.mapper.modelMapper;

import com.dto.GenreDto;
import com.entity.Genre;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GenreMapper {
    GenreDto toDto(Genre genre);
    Genre toEntity(GenreDto genreDto);
}
