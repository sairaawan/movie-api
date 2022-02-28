package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.error.NoRecommendationsException;

import java.util.List;

public interface MovieService {

   List<Movie> fetchMoviesListByReleaseYear(int year, double minimumRating, int minimumVotes) throws NoRecommendationsException;

   List<Movie> fetchByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes) throws NoRecommendationsException;

   List<Movie> fetchByGenre(String genre, double minimumRating, int minimumVotes) throws NoRecommendationsException;

   List<Movie> fetchByMovieId(List<String> movieIds) throws NoRecommendationsException;
}
