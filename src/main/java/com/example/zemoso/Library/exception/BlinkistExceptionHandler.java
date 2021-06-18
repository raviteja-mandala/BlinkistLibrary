package com.example.zemoso.Library.exception;

import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class BlinkistExceptionHandler {

    @ExceptionHandler({BookAlreadyPresentException.class,BookAlreadyInUserLibraryException.class})
    public ResponseEntity<BookErrorResponse> handleBookAlreadyPresentException(Exception exception){
        return new ResponseEntity<>(new BookErrorResponse(404,exception.getMessage(),new Date()), HttpStatus.ALREADY_REPORTED);
    }

   /* @ExceptionHandler({BookAlreadyInUserLibraryException.class})
    public ResponseEntity<BookErrorResponse> handleBookAlreadyPresentException(BookAlreadyInUserLibraryException exception){
        return new ResponseEntity<>(new BookErrorResponse(404, exception.getMessage(), new Date()), HttpStatus.ALREADY_REPORTED);
    }*/

    @ExceptionHandler({Exception.class})
    public ResponseEntity<BookErrorResponse> handleGeneralException(Exception exception){
        return new ResponseEntity<>(new BookErrorResponse(404,"This is a bad request",new Date()), HttpStatus.BAD_REQUEST);
    }

}
