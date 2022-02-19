package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;

import java.util.Date;
import java.util.List;

public interface MovieService {
   public  List<Movie> fetchMoviesListByRatingSorted(int page);

  public Movie saveMovie(Movie movie);

   public  List<Movie> fetchMoviesListByRatings(int rating);

   public List<Movie> fetchMoviesListByYear(int year);

   public List<Movie> findByCreatedAtBetween(Date startDate, Date endDate);
}
