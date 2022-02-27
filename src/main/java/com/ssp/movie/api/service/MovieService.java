package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.error.NoRecommendationsFoundException;

import java.util.List;

public interface MovieService {
   List<Movie> fetchMoviesListByRatingSorted(int page) throws NoRecommendationsFoundException;

   List<Movie> fetchMoviesListByAverageRating(int rating) throws NoRecommendationsFoundException;

   List<Movie> fetchMoviesListByReleaseYear(int year, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException;

   List<Movie> fetchByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException;

   List<Movie> fetchByGenre(String genre, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException;

    List<Movie> fetchByName(String name, double minimum_rating, int minimum_votes);
}
