package com.example.zemoso.library.exception;

public class BookAlreadyPresentException extends Exception{
    public BookAlreadyPresentException(String message){
        super(message);
    }
}
