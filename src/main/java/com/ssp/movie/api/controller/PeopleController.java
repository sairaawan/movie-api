package com.ssp.movie.api.controller;


import com.ssp.movie.api.entity.People;
import com.ssp.movie.api.error.NoRecommendationsFoundException;
import com.ssp.movie.api.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PeopleController {
    @Autowired
    PeopleService peopleService;

    private final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @GetMapping("/people/name/{name}")
    public ResponseEntity fetchPeopleListByName(@PathVariable("name") String name) throws NoRecommendationsFoundException {
        LOGGER.info("Inside fetchPeopleListByName of PeopleController");
        List<People> people = peopleService.fetchPeopleListByName(name);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }
    @GetMapping("/people/profession/{profession}")
    public ResponseEntity fetchPeopleListByPrimaryProfession(@PathVariable("profession") String profession) throws NoRecommendationsFoundException {
        LOGGER.info("Inside fetchPeopleListByProfession of PeopleController");
        List<People> people = peopleService.fetchPeopleListByPrimaryProfession(profession);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/people/name/{name}/profession/{profession}")
    public ResponseEntity fetchPeopleListByNameAndProfession(@PathVariable("name") String name, @PathVariable("profession") String profession) throws NoRecommendationsFoundException {
        LOGGER.info("Inside fetchPeopleListByProfession of PeopleController");
        List<People> people = peopleService.fetchPeopleListByPrimaryNameAndProfession(name,profession);
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

}
