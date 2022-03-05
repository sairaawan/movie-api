package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class EmailServiceTests {

    @InjectMocks
    private EmailServiceImpl emailService;

    private List<Movie> movies;
    private String testEmail;

    @BeforeEach
    public void setUp() {
        movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2", 2018, 100, "Action", 8.5, 1000));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3", 2018, 100, "Action", 8.5, 1000));

        testEmail = System.getenv("TEST_EMAIL");
    }

    @Test
    public void shouldNotThrowExceptionForValidEmail() {
         assertDoesNotThrow(() -> emailService.sendEmail("Test Search", testEmail, movies));
    }

    @Test
    public void shouldThrowExceptionForInvalidEmail()  {
        assertThrows(IllegalArgumentException.class, () -> emailService.sendEmail("Test Search", "x@x", movies));
    }

}
