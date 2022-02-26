package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.error.NoRecommendationsFoundException;
import com.ssp.movie.api.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@DataJpaTest
public class MovieApiServiceTests {
    @Mock
    private MovieRepository mockMovieRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImplServiceImpl;

    @Test
    public void testFetchMoviesListByReleaseYear() throws NoRecommendationsFoundException {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3", 2018, 100, "Action", 8.5, 1000));

        when(mockMovieRepository.findByReleaseYear(2018, 8, 1000))
                .thenReturn(movies);
        List<Movie> actualResult = movieServiceImplServiceImpl.fetchMoviesListByReleaseYear(2018, 8, 1000);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

    @Test
    public void testFetchReleaseYearBetween() throws NoRecommendationsFoundException {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3", 2018, 100, "Action", 8.5, 1000));

        when(mockMovieRepository.findByReleaseYearBetween(2018, 2019,8, 1000))
                .thenReturn(movies);
        List<Movie> actualResult = movieServiceImplServiceImpl.fetchByReleaseYearBetween(2018, 2019,8, 1000);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }


    @Test
    public void shouldThrowExceptionWhenNoRecommendationsAreAvailableByYear() throws NoRecommendationsFoundException {
        List<Movie> movies = new ArrayList<>();

        when(mockMovieRepository.findByReleaseYear(2018, 8, 1000))
                .thenReturn(movies);
        assertThrows(NoRecommendationsFoundException.class,
                () -> movieServiceImplServiceImpl.fetchMoviesListByReleaseYear(2018, 8, 1000));
    }

    @Test
    public void shouldThrowExceptionWhenNoRecommendationsAreAvailableByYearRange() throws NoRecommendationsFoundException {
        List<Movie> movies = new ArrayList<>();

        when(mockMovieRepository.findByReleaseYearBetween(2018, 2019,8, 1000))
                .thenReturn(movies);
        assertThrows(NoRecommendationsFoundException.class,
                () -> movieServiceImplServiceImpl.fetchByReleaseYearBetween(2018, 2019,8, 1000));
    }


}

