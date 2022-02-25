package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.error.MovieNotFoundException;

import java.util.Date;
import java.util.List;

public interface MovieService {
   public  List<Movie> fetchMoviesListByRatingSorted(int page) throws MovieNotFoundException;

  public Movie saveMovie(Movie movie);

   public  List<Movie> fetchMoviesListByAverageRating(int rating) throws MovieNotFoundException;

   public List<Movie> fetchMoviesListByReleaseYear(int year, double minimumRating, int minimumVotes) throws MovieNotFoundException;

   public List<Movie> findByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes) throws MovieNotFoundException;

}
