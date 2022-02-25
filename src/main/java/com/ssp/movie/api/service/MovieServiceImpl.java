package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import com.ssp.movie.api.error.NoRecommendationsFoundException;
import com.ssp.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Service
public class MovieServiceImpl implements MovieService{

    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie>fetchMoviesListByRatingSorted(int page) throws NoRecommendationsFoundException {
        Pageable sortByRating= PageRequest.of(page,3, Sort.by("averageRating"));
        List<Movie> movie =
                movieRepository.findAll(sortByRating).getContent();
        if(movie.isEmpty()) {
            throw new NoRecommendationsFoundException("Movie not available.Enter correct page no");
        }
        return  movie;
    }

    @Override
    public List<Movie> fetchMoviesListByAverageRating(int rating) throws NoRecommendationsFoundException {
       List<Movie> movie =movieRepository.getMovieByAverageRating(rating);
        if(movie.isEmpty()) {
            throw new NoRecommendationsFoundException("Movie not available. Enter correct rating");
        }
        return movie;
    }

    @Override
    public List<Movie> fetchMoviesListByReleaseYear(int year, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException {
        List<Movie> movie = movieRepository.findByReleaseYear(year, minimumRating, minimumVotes);
        if(movie.isEmpty()) {
            throw new NoRecommendationsFoundException("Movie not available. Enter correct year");
        }
        return movie;
    }

    @Override
    public List<Movie> findByReleaseYearBetween(int startYear, int endYear, double minimumRating, int minimumVotes) throws NoRecommendationsFoundException {
        List<Movie> movie=movieRepository.findByReleaseYearBetween(startYear, endYear, minimumRating, minimumVotes);
        if(movie.isEmpty()) {
            throw new NoRecommendationsFoundException("Movie not available.");
        }
        return movie;
    }
}
