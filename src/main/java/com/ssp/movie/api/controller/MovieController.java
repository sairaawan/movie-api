package com.ssp.movie.api.controller;

import com.ssp.movie.api.entity.ApiResponse;
import com.ssp.movie.api.entity.GenreEnum;
import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.entity.Person;
import com.ssp.movie.api.error.NoRecommendationsException;
import com.ssp.movie.api.service.MovieService;
import com.ssp.movie.api.service.PersonService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    PersonService personService;

    private final double MINIMUM_RATING = 8.0;
    private final int MINIMUM_VOTES = 1000;

    private final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/")
    public String Welcome() {
        return "Welcome to movies";
    }

    //  Get recommendations for a specified year, /movies/year/2019
    @GetMapping("/movies/year/{year}")
    public ResponseEntity<ApiResponse> fetchMoviesListByYear(@PathVariable("year") int year) throws NoRecommendationsException {
        LOGGER.info("Inside fetchMovieListByYear of MovieController");
        List<Movie> movies = movieService.fetchMoviesListByReleaseYear(year, MINIMUM_RATING, MINIMUM_VOTES);

        if (movies.isEmpty()) {
            throw new NoRecommendationsException("No recommendations found");
        }
        ApiResponse apiResponse = new ApiResponse("Movies recommended", true, movies);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    //  Get recommendations for a specified year range, /movies/year?startYear=2018&endYear=2020
    @GetMapping("/movies/year")
    public ResponseEntity getMoviesByCreatedDate(@RequestParam int startYear, @RequestParam int endYear) throws NoRecommendationsException {
        LOGGER.info("Inside getMoviesByCreatedDate of MovieController");
        List<Movie> movies = movieService.fetchByReleaseYearBetween(startYear, endYear, MINIMUM_RATING, MINIMUM_VOTES);

        if (movies.isEmpty()) {
            throw new NoRecommendationsException("No recommendations found");
        }

        ApiResponse apiResponse = new ApiResponse("Movies recommended", true, movies);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    //  Get recommendations for a specified genre
    @GetMapping("/movies/genre/{genre}")
    public ResponseEntity getMoviesByGenre(@PathVariable("genre") String genre) throws NoRecommendationsException {
        LOGGER.info("Inside getMoviesByGenre of MovieController");

        if (!GenreEnum.isValidGenre(genre)) {
            throw new IllegalArgumentException("Invalid Genre");
        }

        List<Movie> movies =  movieService.fetchByGenre(GenreEnum.valueOf(genre.toUpperCase()).getName(), MINIMUM_RATING, MINIMUM_VOTES);

        if (movies.isEmpty()) {
            throw new NoRecommendationsException("No recommendations found");
        }

        ApiResponse apiResponse = new ApiResponse("Movies recommended", true, movies);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    //  Get recommendations for a specified person
    @GetMapping("/movies/person/{name}")
    public ResponseEntity getMoviesByPerson(@PathVariable("name") String name) throws NoRecommendationsException {
        LOGGER.info("Inside getMoviesByGenre of MovieController");

        List<Person> person = personService.fetchByPrimaryName(name);

        if (person.isEmpty()) {
            throw new NoRecommendationsException("Person not found");
        }

        List<String> movieIds = Arrays.asList(person.get(0).getKnownForMovies().split(",", -1));

        List<Movie> movies = movieService.fetchByMovieId(movieIds);

        if (movies.isEmpty()) {
            throw new NoRecommendationsException("No recommendations found");
        }

        ApiResponse apiResponse = new ApiResponse("Movies recommended", true, movies);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/movies/name/contains/{name}")
    public ResponseEntity<ApiResponse> fetchMovieByName(@PathVariable("name") String movieName) throws NoRecommendationsException {
        List<Movie> movies = movieService.fetchMovieByName(movieName, MINIMUM_RATING, MINIMUM_VOTES);
        if (movies.isEmpty()) {
            throw new NoRecommendationsException("No recommendations found");
        }

        ApiResponse apiResponse = new ApiResponse("Movies recommended", true, movies);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("/movies/name/{name}")
    public ResponseEntity<ApiResponse> fetchMovieByNameExact(@PathVariable("name") String movieName) throws NoRecommendationsException {
        List<Movie> movies = movieService.fetchMovieByNameExact(movieName);
        if (movies.isEmpty()) {
            throw new NoRecommendationsException("No recommendations found");
        }

        ApiResponse apiResponse = new ApiResponse("Movies recommended", true, movies);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

}
