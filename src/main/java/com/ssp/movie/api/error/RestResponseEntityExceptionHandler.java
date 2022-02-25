package com.ssp.movie.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoRecommendationsFoundException.class)
    public ResponseEntity<ErrorMessage> MovieNotFoundException(NoRecommendationsFoundException exception,
                                                               WebRequest request) {

        ErrorMessage message = new ErrorMessage(HttpStatus.OK, exception.getMessage());

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
