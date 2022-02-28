package com.ssp.movie.api.repository;


import com.ssp.movie.api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByPrimaryNameIgnoreCase(String name);
}