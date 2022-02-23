package com.ssp.movie.api.repository;

import com.ssp.movie.api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select s from Movie s where s.releaseYear= ?1")
    List<Movie> findByReleaseYear(int year);

    @Query("select s from Movie s where s.averageRating= ?1")
    List<Movie> getMovieByAverageRating(double averageRating);
}
