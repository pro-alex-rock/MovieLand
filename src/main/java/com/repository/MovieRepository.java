package com.repository;

import com.entity.Movie;
import com.model.SortingCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<List<Movie>> getAllMovie(SortingCredentials sortingCredentials);
    Optional<List<Movie>> getRandom();
    Optional<List<Movie>> getMoviesByGenre(String genre, SortingCredentials sortingCredentials);
}
