package com.ssp.movie.api.controller;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.error.NoRecommendationsFoundException;
import com.ssp.movie.api.service.MovieService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    private final double MINIMUM_RATING = 8.0;
    private final int MINIMUM_VOTES = 1000;

    private final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/")
    public String Welcome() {
        return "Welcome to movies";
    }


    //  Get recommendations for a specified year, /movies/year/2019
    @GetMapping("/movies/year/{year}")
    public ResponseEntity fetchMoviesListByYear(@PathVariable("year") int year) throws NoRecommendationsFoundException {
        LOGGER.info("Inside fetchMovieListByYear of MovieController");
        List<Movie> movies = movieService.fetchMoviesListByReleaseYear(year, MINIMUM_RATING, MINIMUM_VOTES);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    //  Get recommendations for a specified year range, /movies/year?startYear=2018&endYear=2020
    @GetMapping("/movies/year")
    public List<Movie> getMoviesByCreatedDate(@RequestParam int startYear, @RequestParam int endYear) throws NoRecommendationsFoundException {
        LOGGER.info("Inside getMoviesByCreatedDate of MovieController");
        return movieService.findByReleaseYearBetween(startYear, endYear, MINIMUM_RATING, MINIMUM_VOTES);
    }

}
