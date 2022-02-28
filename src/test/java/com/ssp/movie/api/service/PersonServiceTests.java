package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Person;
import com.ssp.movie.api.error.NoRecommendationsException;
import com.ssp.movie.api.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class PersonServiceTests {

    @Mock
    private PersonRepository mockPeopleRepository;

    @InjectMocks
    private PersonServiceImpl peopleServiceImpl;

    private List<Person> people;

    @BeforeEach
    public void setUp()
    {
        people = new ArrayList<>();
        people.add(new Person("Person001", "Tom Hanks", 1956, null, "Actor", "Movie001"));
    }

    @Test
    public void shouldBeAbleToFetchPeopleByName() throws NoRecommendationsException {

        when(mockPeopleRepository.findByPrimaryNameIgnoreCase("Tom Hanks")).thenReturn(people);
        List<Person> actualResult = peopleServiceImpl.fetchByPrimaryName("Tom Hanks");

        assertThat(actualResult).hasSize(1);
        assertThat(actualResult).isEqualTo(people);
    }


}
