package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;

import java.io.IOException;
import java.util.List;

public interface EmailService {

    void sendEmail(String searchType, String emailAddress, List<Movie> movies) throws IllegalArgumentException, IOException;
}
