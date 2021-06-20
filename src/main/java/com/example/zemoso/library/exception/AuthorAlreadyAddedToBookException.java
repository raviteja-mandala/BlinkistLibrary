package com.example.zemoso.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuthorAlreadyAddedToBookException extends Exception{
    public AuthorAlreadyAddedToBookException(String message){
        super(message);
    }
}
