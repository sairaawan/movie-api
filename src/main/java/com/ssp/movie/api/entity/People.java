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
public class People {
    @Id
    private String peopleId;
    private String primaryName;
    private int birthYear;
    private int deathYear;
    private String primaryProfession;
}
