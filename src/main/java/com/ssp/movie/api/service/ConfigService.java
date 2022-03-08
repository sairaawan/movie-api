package com.ssp.movie.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class ConfigService {

    @Value("${app.sendgridapikey}")
    private String sendGridAPIKey;

    @Value("${app.mailfrom}")
    private String mailFrom;

    @Value("${app.testemail}")
    private String testEmail;

    @Value("${app.minimumvotes}")
    private int minimumVotes;

    @Value("${app.minimumrating}")
    private double minimumRating;

    public int getMinimumVotes() {
        return minimumVotes;
    }

    public double getMinimumRating() {
        return minimumRating;
    }

    public String getSendGridAPIKey() {
        return sendGridAPIKey;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public String getTestKey() {
        return "TESTKEY";
    }

}
