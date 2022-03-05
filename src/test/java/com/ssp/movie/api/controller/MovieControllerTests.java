package com.ssp.movie.api.controller;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.entity.Person;
import com.ssp.movie.api.error.NoRecommendationsException;
import com.ssp.movie.api.error.RestResponseEntityExceptionHandler;
import com.ssp.movie.api.service.EmailServiceImpl;
import com.ssp.movie.api.service.MovieServiceImpl;
import com.ssp.movie.api.service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class MovieControllerTests {

    @Mock
    private MovieServiceImpl mockMovieServiceImpl;

    @Mock
    private PersonServiceImpl mockPersonServiceImpl;

    @Mock
    private EmailServiceImpl mockEmailServiceImpl;

    @InjectMocks
    private MovieController movieController;

    @Autowired
    private MockMvc mockMvcController;

    private List<Movie> movies;
    private List<Person> people;
    private List<String> movieIds;
    private List<Movie> emptyMovieList;
    private static final double MINIMUM_RATING = 8.0;
    private static final int MINIMUM_VOTES = 1000;


    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(movieController).setControllerAdvice(new RestResponseEntityExceptionHandler()).build();

        movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3", 2018, 100, "Action", 8.5, 1000));

        people = new ArrayList<>();
        people.add(new Person("Person001", "Tom Hanks", 1956, null, "Actor", "Movie001,Movie002,Movie003"));

        movieIds = Arrays.asList("Movie001", "Movie002", "Movie003");
        emptyMovieList = new ArrayList<>();
    }

    @Test
    public void shouldReturnMovieRecommendationsForTheYear() throws Exception {

        when(mockMovieServiceImpl.fetchMoviesListByReleaseYear(2018, MINIMUM_RATING, MINIMUM_VOTES)).thenReturn(movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/2018"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].movieId").value("Movie001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].movieName").value("Test Movie 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[1].movieId").value("Movie002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[1].movieName").value("Test Movie 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[2].movieId").value("Movie003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[2].movieName").value("Test Movie 3"));
    }

    @Test
    public void shouldReturnMovieRecommendationsForYearBetweenTwoYears() throws Exception {

        when(mockMovieServiceImpl.fetchByReleaseYearBetween(2016, 2018, MINIMUM_RATING, MINIMUM_VOTES)).thenReturn(movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/").param("startYear", "2016")
                        .param("endYear", "2018"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].movieId").value("Movie001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].releaseYear").value(2018))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[1].movieId").value("Movie002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[1].releaseYear").value(2018))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[2].movieId").value("Movie003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[2].releaseYear").value(2018));
    }

    @Test
    public void shouldReturnMovieRecommendationsByGenre() throws Exception {

        String genre = "Action";
        when(mockMovieServiceImpl.fetchByGenre(genre, MINIMUM_RATING, MINIMUM_VOTES)).thenReturn(movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/genre/Action"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].movieId").value("Movie001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].movieGenre").value("Action"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[1].movieId").value("Movie002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[1].movieGenre").value("Action"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[2].movieId").value("Movie003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[2].movieGenre").value("Action"));
    }

    @Test
    public void shouldHandleNoMoviesFoundForAYear() throws Exception {

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/2999"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoRecommendationsException));
    }

    @Test
    public void shouldHandleNoMoviesFoundForAYearRange() throws Exception {
        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/").param("startYear", "2900")
                        .param("endYear", "2901"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoRecommendationsException));
    }

    @Test
    public void shouldHandleNoMoviesFoundForAGenre() throws Exception {
        String genre = "Thriller";
        when(mockMovieServiceImpl.fetchByGenre(genre, MINIMUM_RATING, MINIMUM_VOTES)).thenReturn(emptyMovieList);
        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/genre/Thriller"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoRecommendationsException));
    }

    @Test
    public void shouldHandleInvalidGenres() throws Exception {

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/genre/unknown"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusMessage").value("Invalid Genre"));
    }

    @Test
    public void shouldReturnMovieRecommendationsByPersonName() throws Exception {

        when(mockPersonServiceImpl.fetchByPrimaryName("Tom Hanks")).thenReturn(people);
        when(mockMovieServiceImpl.fetchByMovieId(movieIds)).thenReturn(movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/person/Tom Hanks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].movieId").value("Movie001"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[0].movieGenre").value("Action"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[1].movieId").value("Movie002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[1].movieGenre").value("Action"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[2].movieId").value("Movie003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movies[2].movieGenre").value("Action"));
    }

    @Test
    public void shouldHandleInvalidPerson() throws Exception {

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/person/uknown"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoRecommendationsException));

    }

    @Test
    public void shouldHandleValidPersonButNoMovies() throws Exception {

        when(mockPersonServiceImpl.fetchByPrimaryName("Tom Hanks")).thenReturn(people);
        when(mockMovieServiceImpl.fetchByMovieId(movieIds)).thenReturn(emptyMovieList);
        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/person/Tom Hanks"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoRecommendationsException));

    }

    @Test
    public void shouldHandleInvalidYearFormat() throws Exception {

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/19"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));

    }

    @Test
    public void shouldHandleYearContainingChars() throws Exception {

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/19xx"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));

    }

    @Test
    public void shouldHandleInvalidStartYearFormat() throws Exception {
        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/").param("startYear", "19")
                        .param("endYear", "1901"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));

    }

    @Test
    public void shouldHandleInvalidEndYearFormat() throws Exception {
        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/").param("startYear", "1999")
                        .param("endYear", "19"))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof IllegalArgumentException));

    }


    @Test
    public void shouldTryToSendEmailIfAddressIsPresentForYearSearch() throws Exception {
        String testEmail = "test.email@movi3.me.uk";
        String year = "2016";
        String expectedSearchText = MessageFormat.format("Movies from {0}", year);

        when(mockMovieServiceImpl.fetchMoviesListByReleaseYear(Integer.parseInt(year), MINIMUM_RATING, MINIMUM_VOTES))
                .thenReturn(movies);

        doNothing().when(mockEmailServiceImpl).sendEmail(expectedSearchText, testEmail, movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/" + year)
                        .param("emailAddress", testEmail))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockEmailServiceImpl, times(1)).sendEmail(expectedSearchText, testEmail, movies);
    }

    @Test
    public void shouldTryToSendEmailIfAddressIsPresentForBetweenYearsSearch() throws Exception {
        String testEmail = "test.email@movi3.me.uk";
        String startYear = "2016";
        String endYear = "2018";
        String expectedSearchText = MessageFormat.format("Movies from {0} to {1}", startYear, endYear);

        when(mockMovieServiceImpl.fetchByReleaseYearBetween(Integer.parseInt(startYear), Integer.parseInt(endYear),
                MINIMUM_RATING, MINIMUM_VOTES))
                .thenReturn(movies);

        doNothing().when(mockEmailServiceImpl).sendEmail(expectedSearchText, testEmail, movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/year/")
                        .param("startYear", startYear)
                        .param("endYear", endYear)
                        .param("emailAddress", testEmail))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockEmailServiceImpl, times(1)).sendEmail(expectedSearchText, testEmail, movies);
    }


    @Test
    public void shouldTryToSendEmailIfAddressIsPresentForGenreSearch() throws Exception {
        String testEmail = "test.email@movi3.me.uk";
        String genre = "Action";
        String expectedSearchText = MessageFormat.format("Movies for the {0} genre", genre);

        when(mockMovieServiceImpl.fetchByGenre(genre, MINIMUM_RATING, 1000)).thenReturn(movies);

        doNothing().when(mockEmailServiceImpl).sendEmail(expectedSearchText, testEmail, movies);

        this.mockMvcController.perform(MockMvcRequestBuilders.get("/movies/genre/" + genre)
                        .param("emailAddress", testEmail))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockEmailServiceImpl, times(1)).sendEmail(expectedSearchText, testEmail, movies);
    }


}
