package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie>fetchMoviesListByRatingSorted(int page) {
        Pageable sortByRating= PageRequest.of(page,3, Sort.by("rating"));
        List<Movie> movies
                = movieRepository.findAll(sortByRating).getContent();
       return movies;

    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> fetchMoviesListByRatings(int rating) {
        return movieRepository.getMovieByRating(rating);
    }

    @Override
    public List<Movie> fetchMoviesListByYear(int year) {
        return movieRepository.findByYear(year);
    }

    @Override
    public List<Movie> findByYearBetween(int start, int end) {
        return movieRepository.findByYearBetween(start, end);
    }
}
