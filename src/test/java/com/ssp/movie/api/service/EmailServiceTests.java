package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@SpringBootTest
public class EmailServiceTests {

    @Mock
    private ConfigService mockConfigService;

    @InjectMocks
    private EmailServiceImpl emailService;

    private List<Movie> movies;

    @BeforeEach
    public void setUp() {

        movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1", 2018, 100, "Action", 8.0, 1000));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2", 2018, 100, "Action", 8.0, 1000));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3", 2018, 100, "Action", 8.0, 1000));
    }

    @Test
    public void shouldNotThrowExceptionForValidEmail() {

        String testEmail = System.getenv("TEST_EMAIL");
        when(mockConfigService.getSendGridAPIKey()).thenReturn(System.getenv("SENDGRID_API_KEY"));
        when(mockConfigService.getMailFrom()).thenReturn(System.getenv("MAIL_FROM"));

        assertDoesNotThrow(() -> emailService.sendEmail(testEmail, "Test Search", movies));
    }

    @Test
    public void shouldThrowExceptionForInvalidEmail() {
        when(mockConfigService.getSendGridAPIKey()).thenReturn("TESTKEY");
        assertThrows(IllegalArgumentException.class, () -> emailService.sendEmail("invalid email", "Test Search", movies));
    }

    @Test
    public void shouldThrowExceptionIfApiKeyIsNotSet() {
        when(mockConfigService.getSendGridAPIKey()).thenReturn(null);
        assertThrows(IllegalStateException.class, () -> emailService.sendEmail("no api key", "Test Search", movies));
    }


}
