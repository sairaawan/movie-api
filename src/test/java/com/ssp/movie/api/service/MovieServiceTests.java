package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.error.NoRecommendationsException;
import com.ssp.movie.api.repository.MovieRepository;
import com.ssp.movie.api.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@DataJpaTest
public class MovieServiceTests {
    @Mock
    private MovieRepository mockMovieRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;


    private List<Movie> movies;

    @BeforeEach
    public void setUp()
    {
        movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3", 2018, 100, "Action", 8.5, 1000));
    }

    @Test
    public void shouldBeAbleToFetchMoviesListByReleaseYear() throws NoRecommendationsException {

        when(mockMovieRepository.findByReleaseYear(2018, 8, 1000))
                .thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchMoviesListByReleaseYear(2018, 8, 1000);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

    @Test
    public void shouldBeAbleToFetchReleaseYearBetween() throws NoRecommendationsException {

        when(mockMovieRepository.findByReleaseYearBetween(2018, 2019,8, 1000))
                .thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchByReleaseYearBetween(2018, 2019,8, 1000);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }


    @Test
    public void shouldBeAbleToFetchMoviesByGenre() throws NoRecommendationsException {

        String genre = "Action";
        when(mockMovieRepository.findByGenre(genre, 8, 1000))
                .thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchByGenre(genre, 8, 1000);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

    @Test
    public void shouldBeAbleToFetchMoviesById() throws NoRecommendationsException {

        List<String> movieIds = Arrays.asList("Movie001","Movie002","Movie003");
        when(mockMovieRepository.findByMovieId(movieIds)).thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchByMovieId(movieIds);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

    @Test
    public void shouldBeAbleToFetchMoviesByName() throws NoRecommendationsException {
        String movie="Test Movie 1";
        when(mockMovieRepository.findByMovieNameContaining(movie, 8,1000)).thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchMovieByName(movie,8,1000);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

}

