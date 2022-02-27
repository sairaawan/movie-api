package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.People;

import java.util.List;

public interface PeopleService {
    public List<People> fetchPeopleListByName(String name);

    List<People> fetchPeopleListByPrimaryProfession(String profession);

    List<People> fetchPeopleListByPrimaryNameAndProfession(String name, String profession);
}
