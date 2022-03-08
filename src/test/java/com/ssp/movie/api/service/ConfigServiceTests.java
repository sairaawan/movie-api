package com.ssp.movie.api.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ConfigServiceTests {

    @Autowired
    ConfigService configService;

    @Autowired
    Environment env;

    @Test
    public void shouldReturnCorrectValueForAPIKeyFromConfigService() {
        String expectedValue = env.getProperty("app.sendgridapikey");
        assertEquals(expectedValue,configService.getSendGridAPIKey());
    }

    @Test
    public void shouldReturnCorrectValueForMailFromConfigService() {
        String expectedValue = env.getProperty("app.mailfrom");
        assertEquals(expectedValue,configService.getMailFrom());
    }

    @Test
    public void shouldReturnCorrectValueForTestKeyFromConfigService() {
        String expectedValue = env.getProperty("app.testkey");
        assertEquals(expectedValue,configService.getTestKey());
    }

    @Test
    public void shouldReturnCorrectValueForMinRatingFromConfigService() {
        String expectedValue = env.getProperty("app.minimumrating");
        assertEquals(Double.parseDouble(expectedValue),configService.getMinimumRating());
    }

    @Test
    public void shouldReturnCorrectValueForMinVotesFromConfigService() {
        String expectedValue = env.getProperty("app.minimumvotes");
        assertEquals(Double.parseDouble(expectedValue),configService.getMinimumVotes());
    }


}
