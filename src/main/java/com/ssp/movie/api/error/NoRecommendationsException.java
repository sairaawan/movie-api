package com.ssp.movie.api.error;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class NoRecommendationsException extends Exception{

       public NoRecommendationsException(String message) {
        super(message);
    }
}
