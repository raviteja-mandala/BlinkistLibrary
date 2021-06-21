package com.example.zemoso.library.exception;

public class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(String message){
        super(message);
    }
}
