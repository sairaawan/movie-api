package com.ssp.movie.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponse {

    private String statusMessage;
    private boolean requestSuccessful;
    private List<Movie> movies;
}
