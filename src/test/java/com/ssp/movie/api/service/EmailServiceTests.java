package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import com.ssp.movie.api.service.ConfigService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class EmailServiceTests {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Autowired
    private ConfigService configService;

    private List<Movie> movies;
    private int minimumVotes;
    private double minimumRating;
    private String testEmail;

    @BeforeEach
    public void setUp() {
        movies = new ArrayList<>();
        movies.add(new Movie("Movie001", "movie", "Test Movie 1", 2018, 100, "Action", minimumRating, minimumVotes));
        movies.add(new Movie("Movie002", "movie", "Test Movie 2", 2018, 100, "Action", minimumRating, minimumVotes));
        movies.add(new Movie("Movie003", "movie", "Test Movie 3", 2018, 100, "Action", minimumRating, minimumVotes));

        testEmail = System.getenv("TEST_EMAIL");

        minimumVotes = configService.getMinimumVotes();
        minimumRating = configService.getMinimumRating();
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
