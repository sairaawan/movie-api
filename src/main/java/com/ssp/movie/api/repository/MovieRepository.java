package com.ssp.movie.api.repository;

import com.ssp.movie.api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select s from Movie s where s.averageRating= ?1")
    List<Movie> getMovieByAverageRating(double averageRating);

    @Query(nativeQuery = true, value = "SELECT * FROM Movie s WHERE s.releaseYear = :year ORDER BY rating DESC LIMIT 3")
    List<Movie> findByReleaseYear(@Param("year") int year);

//  List<Movie> findByCreatedAtBetween(Date startDate, Date endDate);

     List<Movie> findByYearBetween(int start, int end);

}
