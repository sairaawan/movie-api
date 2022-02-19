package com.ssp.movie.api.repository;

import com.ssp.movie.api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByFirstYear(int year);

    @Query("select s from Movies s where s.ratings= ?1")
    List<Movie> getMovieByRating(int rating);
}
