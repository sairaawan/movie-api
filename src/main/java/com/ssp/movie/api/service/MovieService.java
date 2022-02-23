package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;

import java.util.Date;
import java.util.List;

public interface MovieService {
   public  List<Movie> fetchMoviesListByRatingSorted(int page);

  public Movie saveMovie(Movie movie);

   public  List<Movie> fetchMoviesListByAverageRating(int rating);

   public List<Movie> fetchMoviesListByReleaseYear(int year, double minimumRating, int minimumVotes);

   public List<Movie> findByReleaseYearBetween(int start, int end);

}
