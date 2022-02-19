package com.ssp.movie.api.controller;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.service.MovieService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(MovieController.class);

    @PostMapping("/movies")
    public Movie saveDepartment(@RequestBody Movie movie) {
        LOGGER.info("Inside saveMovie of MovieController");
        return movieService.saveMovie(movie);
    }

    @GetMapping("/")
    public String Welcome() {
        return "Welcome to movies";
    }

//   Find 3 movies per page, given page number, ordered by rating

    @GetMapping("/movies/page/{page}")
    public List<Movie> fetchMoviesListByRatingSorted(@PathVariable("page") int page) {
        LOGGER.info("Inside fetchMoviesListByRatingSorted of MovieController Per Page");
        return movieService.fetchMoviesListByRatingSorted(page);
    }
// Find all movies given a rating number

    @GetMapping("/movies/rating/{rating}")
    public List<Movie> fetchMoviesListByRatings(@PathVariable("rating") int rating) {
        LOGGER.info("Inside fetchMoviesListByRatings of MovieController");
        return movieService.fetchMoviesListByRatings(rating);
    }

//    Find by year, returning 3 movie records based on rating

    @GetMapping("/movies/year/{year}")
    public List<Movie> fetchMoviesListByYear(@PathVariable("year") int year) {
        LOGGER.info("Inside fetchMovieListByYear of MovieController");
        return movieService.fetchMoviesListByYear(year);
    }

}
