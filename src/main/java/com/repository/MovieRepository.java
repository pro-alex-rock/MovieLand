package com.repository;

import com.entity.Genre;
import com.entity.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findAll(final Sort sort);

    @Query(nativeQuery=true, value = "SELECT movie_id, title_russian, title_native, year, rating, price, poster_link FROM movie " +
            "WHERE movie_id IN (SELECT movie_id FROM movie ORDER BY random() LIMIT 3)")
    List<Movie> findRandomMovie();

    List<Movie> findAllMoviesByGenreId(Genre genre);

    Movie findById(int id);
}
