package com.ssp.movie.api.repository;

import com.ssp.movie.api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM Movie s WHERE s.releaseYear = :year " +
                    "AND averageRating >= :minimumRating " +
                    "AND numberOfVotes >= :minimumVotes ORDER BY RAND() DESC LIMIT 3")
    List<Movie> findByReleaseYear(@Param("year") int year, double minimumRating, int minimumVotes);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Movie s WHERE s.releaseYear >= :startYear AND s.releaseYear <= :endYear" +
                    " AND averageRating >= :minimumRating" +
                    " AND numberOfVotes >= :minimumVotes ORDER BY RAND() DESC LIMIT 3")
    List<Movie> findByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes);

    @Query(nativeQuery = true,
            value = "SELECT * FROM Movie s WHERE s.movieGenre LIKE CONCAT('%', :movieGenre, '%')" +
                    " AND averageRating >= :minimumRating" +
                    " AND numberOfVotes >= :minimumVotes ORDER BY RAND() DESC LIMIT 3")
    List<Movie> findByGenre(String movieGenre, double minimumRating, int minimumVotes);


    @Query(nativeQuery = true, value = "SELECT * FROM Movie s WHERE s.movieId IN :movieIds" +
            " ORDER BY RAND() DESC LIMIT 3")
    List<Movie> findByMovieId(List<String> movieIds);

    @Query(nativeQuery = true, value = "SELECT * FROM Movie s WHERE s.movieName LIKE CONCAT('%', :movieName, '%')" +
                    " AND averageRating >= :minimumRating" +
                    " AND numberOfVotes >= :minimumVotes ORDER BY RAND() DESC LIMIT 3")
    List<Movie> findByMovieNameContaining(String movieName, double minimumRating, int minimumVotes);



}
