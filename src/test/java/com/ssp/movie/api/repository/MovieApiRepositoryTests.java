package com.ssp.movie.api.repository;

import com.ssp.movie.api.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieApiRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp()
    {
        Movie movie = new Movie("Movie001", "movie", "Test Movie 1",
                2018, 100, "Action", 8.5, 100);
        movieRepository.save(movie);

        movie = new Movie("Movie002", "movie", "Test Movie 2",
                2018, 110, "Thriller", 6.0, 1000);
        movieRepository.save(movie);

        movie = new Movie("Movie003", "movie", "Test Movie 3",
                2018, 120, "Drama", 9.5, 1000);
        movieRepository.save(movie);

        movie = new Movie("Movie004", "movie", "Test Movie 4",
                2018, 900, "Family", 7.5, 100);
        movieRepository.save(movie);
    }

    @Test
    public void shouldReturnLimitOf3MoviesForSpecifiedYearWithNoZeroValuesForRatingsAndVotes() {
        Iterable<Movie> movies = movieRepository.findByReleaseYear(2018,0,0);
        assertThat(movies).hasSize(3);
    }

    @Test
    public void shouldReturn2MoviesForSpecifiedYearWithValueSetForRatings() {
        Iterable<Movie> movies = movieRepository.findByReleaseYear(2018,8.0,0);
        assertThat(movies).hasSize(2);
    }

    @Test
    public void shouldReturn1MovieForSpecifiedYearWithValueSetForRatingsAndVotes() {
        Iterable<Movie> movies = movieRepository.findByReleaseYear(2018,8.0,1000);
        assertThat(movies).hasSize(1);
    }

    @Test
    public void shouldReturn0MoviesForYearWithNoMovies() {
        Iterable<Movie> movies = movieRepository.findByReleaseYear(3019,0.0,0);
        assertThat(movies).hasSize(0);
    }


}
