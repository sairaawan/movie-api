package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Person;
import com.ssp.movie.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> fetchByPrimaryName(String name) {
        return personRepository.findByPrimaryNameIgnoreCase(name);
    }


}