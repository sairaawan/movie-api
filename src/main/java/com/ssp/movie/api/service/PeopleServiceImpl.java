package com.ssp.movie.api.service;

import com.ssp.movie.api.entity.People;
import com.ssp.movie.api.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    PeopleRepository peopleRepository;

    @Override
    public List<People> fetchPeopleListByName(String name) {
        return peopleRepository.findByPrimaryNameIgnoreCase(name);
    }

    @Override
    public List<People> fetchPeopleListByPrimaryProfession(String profession) {
        return peopleRepository.findByPrimaryProfessionIgnoreCase(profession);
    }

    @Override
    public List<People> fetchPeopleListByPrimaryNameAndProfession(String name, String profession) {
        return peopleRepository.findByPrimaryNameAndPrimaryProfessionIgnoreCase(name,profession);
    }


}
