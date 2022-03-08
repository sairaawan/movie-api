package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;

import java.util.List;

public interface MovieService {

    List<Movie> fetchMovies(double minimumRating, int minimumVotes);

    List<Movie> fetchMoviesListByReleaseYear(int year, double minimumRating, int minimumVotes);

    List<Movie> fetchByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes);

    List<Movie> fetchByGenre(String genre, double minimumRating, int minimumVotes);

    List<Movie> fetchByMovieId(List<String> movieIds);

    List<Movie> fetchMovieByName(String movieName, double minimumRating, int minimumVotes);

}
