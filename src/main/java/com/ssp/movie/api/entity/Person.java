package com.ssp.movie.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    private String personId;
    private String primaryName;
    private Integer birthYear;
    private Integer deathYear;
    private String primaryProfession;
    private String knownForMovies;
}