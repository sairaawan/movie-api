package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.error.NoRecommendationsFoundException;

import java.util.List;

public interface MovieService {
   public  List<Movie> fetchMoviesListByRatingSorted(int page) throws NoRecommendationsFoundException;

   public  List<Movie> fetchMoviesListByAverageRating(int rating) throws NoRecommendationsFoundException;

   public List<Movie> fetchMoviesListByReleaseYear(int year, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException;

   public List<Movie> fetchByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException;

}
