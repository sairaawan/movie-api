package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> fetchByPrimaryName(String name);
}