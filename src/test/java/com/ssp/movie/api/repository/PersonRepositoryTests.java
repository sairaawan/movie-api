package com.ssp.movie.api.repository;


import com.ssp.movie.api.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository peopleRepository;

    @BeforeEach
    public void setUp() {
        Person person = new Person("Person001", "Tom Hanks", 1956, null, "Actor", "Movie001");
        peopleRepository.save(person);
    }

    @Test
    public void shouldBeAbleToFindByPrimaryName() {
        List<Person> people = peopleRepository.findByPrimaryNameIgnoreCase("Tom Hanks");
        assertThat(people).hasSize(1);
    }


}
