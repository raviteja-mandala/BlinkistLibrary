package com.example.zemoso.library.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@EnableWebMvc
@ControllerAdvice
public class BlinkistExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AuthorNotFoundException.class,BookNotFoundException.class,AuthorNotAssignedForBookException.class,BookDoesNotExistInUserLibraryException.class})
    public ResponseEntity<ErrorResponse> handleCustomNotFoundException(Exception exception){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, Arrays.asList(exception.getMessage()),new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BookAlreadyPresentException.class,BookAlreadyInUserLibraryException.class,
            AuthorAlreadyExistsException.class, AuthorAlreadyAddedToBookException.class, UserAlreadyPresentException.class})
    public ResponseEntity<ErrorResponse> handleCustomAlreadyExistsException(Exception exception){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.CONFLICT, Arrays.asList(exception.getMessage()),new Date()), HttpStatus.CONFLICT);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
        var details = new ArrayList<String>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST,details,new Date()), HttpStatus.BAD_REQUEST);
    }

   /* @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
         return new ResponseEntity<>(new ErrorResponse(status,Arrays.asList("Rest URL is invalid"),new Date()), status);
    }*/

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception exception){
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST,Arrays.asList(exception.getMessage()),new Date()), HttpStatus.BAD_REQUEST);
    }
}
