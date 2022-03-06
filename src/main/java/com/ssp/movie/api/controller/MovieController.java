package com.ssp.movie.api.controller;

import com.ssp.movie.api.entity.ApiResponse;
import com.ssp.movie.api.entity.GenreEnum;
import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.entity.Person;
import com.ssp.movie.api.error.NoRecommendationsException;
import com.ssp.movie.api.service.ConfigService;
import com.ssp.movie.api.service.EmailService;
import com.ssp.movie.api.service.MovieService;
import com.ssp.movie.api.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@RestController
@Component
public class MovieController {
    @Autowired
    MovieService movieService;

    @Autowired
    PersonService personService;

    @Autowired
    EmailService emailService;

    @Autowired
    private ConfigService configService;

    private static int minimumVotes;
    private static double minimumRating;

    @PostConstruct
    public void initialise() {
        minimumVotes = configService.getMinimumVotes();
        minimumRating = configService.getMinimumRating();
    }

    private boolean isValidYear(String year) {
        return year.matches("[12][0-9][0-9][0-9]");
    }

    private ApiResponse buildResponse(List<Movie> movies, String emailAddress, String emailSearchText) throws IOException, NoRecommendationsException {
        if (movies.isEmpty()) {
            throw new NoRecommendationsException("No recommendations found");
        }

        if (emailAddress.length() > 0) {
            emailService.sendEmail(emailSearchText, emailAddress, movies);
        }
        return new ApiResponse("Movies recommended", true, movies);
    }

    private final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/")
    public String Welcome() {
        return "Welcome to the Movi3 API - Please visit /swagger-ui/index.html for details";
    }

    //  Get recommendations for a specified year, /movies/year/2019
    @GetMapping("/movies/year/{year}")
    public ResponseEntity<ApiResponse> fetchMoviesListByYear(@PathVariable("year") String year,
                                                             @RequestParam(value = "emailAddress", defaultValue = "") String emailAddress)
            throws NoRecommendationsException, IOException {

        LOGGER.info(MessageFormat.format("Inside fetchMovieListByYear of MovieController {0} {1}", year, emailAddress));

        if (!isValidYear(year)) {
            throw new IllegalArgumentException("Invalid year specified");
        }

        int movieYear = Integer.parseInt(year);
        List<Movie> movies = movieService.fetchMoviesListByReleaseYear(movieYear, minimumRating, minimumVotes);
        ApiResponse apiResponse = buildResponse(movies,emailAddress,MessageFormat.format("Movies from {0}", year));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //  Get recommendations for a specified year range, /movies/year?startYear=2018&endYear=2020
    @GetMapping("/movies/year")
    public ResponseEntity<ApiResponse> getMoviesByCreatedDate(@RequestParam String startYear, @RequestParam String endYear,
                                                 @RequestParam(value = "emailAddress", defaultValue = "") String emailAddress)
            throws NoRecommendationsException, IOException {

        LOGGER.info(MessageFormat.format("Inside getMoviesByCreatedDate of MovieController {0} {1} {2}", startYear, endYear, emailAddress));

        if (!isValidYear(startYear) || !isValidYear(endYear)) {
            throw new IllegalArgumentException("Invalid year specified");
        }

        int movieStartYear = Integer.parseInt(startYear);
        int movieEndYear = Integer.parseInt(endYear);

        List<Movie> movies = movieService.fetchByReleaseYearBetween(movieStartYear, movieEndYear, minimumRating, minimumVotes);

        ApiResponse apiResponse = buildResponse(movies,emailAddress,MessageFormat.format("Movies from {0} to {1}", startYear, endYear));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //  Get recommendations for a specified genre
    @GetMapping("/movies/genre/{genre}")
    public ResponseEntity<ApiResponse> getMoviesByGenre(@PathVariable("genre") String genre,
                                           @RequestParam(value = "emailAddress", defaultValue = "") String emailAddress)
            throws NoRecommendationsException, IOException {

        LOGGER.info(MessageFormat.format("Inside getMoviesByGenre of MovieController {0} {1}", genre, emailAddress));

        if (!GenreEnum.isValidGenre(genre)) {
            throw new IllegalArgumentException("Invalid Genre");
        }

        List<Movie> movies = movieService.fetchByGenre(GenreEnum.valueOf(genre.toUpperCase()).getName(), minimumRating, minimumVotes);
        ApiResponse apiResponse = buildResponse(movies,emailAddress,MessageFormat.format("Movies for the {0} genre", genre));

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    //  Get recommendations for a specified person
    @GetMapping("/movies/person/{name}")
    public ResponseEntity<ApiResponse> getMoviesByPerson(@PathVariable("name") String name) throws NoRecommendationsException {
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
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/name/{name}")
    public ResponseEntity<ApiResponse> fetchMovieByName(@PathVariable("name") String movieName) throws NoRecommendationsException {
        List<Movie> movies = movieService.fetchMovieByName(movieName, minimumRating, minimumVotes);
        if (movies.isEmpty()) {
            throw new NoRecommendationsException("No recommendations found");
        }
        ApiResponse apiResponse = new ApiResponse("Movies recommended", true, movies);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
