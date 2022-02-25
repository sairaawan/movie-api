package com.ssp.movie.api.error;

public class NoRecommendationsFoundException extends Exception{

    public NoRecommendationsFoundException() {
        super();
    }

    public NoRecommendationsFoundException(String message) {
        super(message);
    }

    public NoRecommendationsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRecommendationsFoundException(Throwable cause) {
        super(cause);
    }

    protected NoRecommendationsFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
