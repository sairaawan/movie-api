package com.ssp.movie.api.repository;


import com.ssp.movie.api.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
   public List<People> findByPrimaryNameIgnoreCase(String name);

   public List<People> findByPrimaryProfessionIgnoreCase(String profession);

   public  List<People>findByPrimaryNameAndPrimaryProfessionIgnoreCase(String name, String profession);
}
