package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest
public class MovieServiceTests {
    @Mock
    private MovieRepository mockMovieRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @Mock
    private ConfigService configService;

    private List<Movie> movies;
    private static int minimumVotes;
    private static double minimumRating;


    @BeforeEach
    public void setUp()
    {
        minimumVotes = configService.getMinimumVotes();
        minimumRating = configService.getMinimumRating();

        movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1", 2018, 100, "Action", minimumRating, minimumVotes));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2", 2018, 100, "Action", minimumRating, minimumVotes));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3", 2018, 100, "Action", minimumRating, minimumVotes));

    }

    @Test
    public void shouldBeAbleToFetchMoviesListByReleaseYear() {

        when(mockMovieRepository.findByReleaseYear(2018, minimumRating, minimumVotes))
                .thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchMoviesListByReleaseYear(2018, minimumRating, minimumVotes);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

    @Test
    public void shouldBeAbleToFetchReleaseYearBetween() {

        when(mockMovieRepository.findByReleaseYearBetween(2018, 2019,minimumRating, minimumVotes))
                .thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchByReleaseYearBetween(2018, 2019,minimumRating, minimumVotes);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }


    @Test
    public void shouldBeAbleToFetchMoviesByGenre() {

        String genre = "Action";
        when(mockMovieRepository.findByGenre(genre, minimumRating, minimumVotes))
                .thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchByGenre(genre, minimumRating, minimumVotes);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

    @Test
    public void shouldBeAbleToFetchMoviesById() {

        List<String> movieIds = Arrays.asList("Movie001","Movie002","Movie003");
        when(mockMovieRepository.findByMovieId(movieIds)).thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchByMovieId(movieIds);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

    @Test
    public void shouldBeAbleToFetchMoviesByName() {
        String movie="Test Movie 1";
        when(mockMovieRepository.findByMovieNameContaining(movie, minimumRating, minimumVotes)).thenReturn(movies);
        List<Movie> actualResult = movieServiceImpl.fetchMovieByName(movie,minimumRating, minimumVotes);

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(movies);
    }

}

