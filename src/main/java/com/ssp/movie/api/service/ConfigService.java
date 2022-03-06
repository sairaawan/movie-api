package com.ssp.movie.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class ConfigService {

    @Value("${app.minimumVotes}")
    private int minimumVotes;

    @Value("${app.minimumRating}")
    private double minimumRating;

    public int getMinimumVotes() {
        return minimumVotes;
    }

    public double getMinimumRating() {
        return minimumRating;
    }


}
