package com.example.zemoso.Library.exception;

import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class BlinkistExceptionHandler {

    @ExceptionHandler({BookAlreadyPresentException.class})
    public ResponseEntity<BookErrorResponse> handleBookAlreadyPresentException(BookAlreadyPresentException exception){
        return new ResponseEntity<>(new BookErrorResponse(404,"This book is already present",new Date()), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<BookErrorResponse> handleGeneralException(Exception exception){
        return new ResponseEntity<>(new BookErrorResponse(404,"This is a bad request",new Date()), HttpStatus.BAD_REQUEST);
    }

}
