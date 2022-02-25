package com.ssp.movie.api.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    private String movieId;
    private String movieType;
    private String movieName;
    private int releaseYear;
    private int runningTime;
    private String movieGenre;
    private double averageRating;
    private int numberOfVotes;
}
