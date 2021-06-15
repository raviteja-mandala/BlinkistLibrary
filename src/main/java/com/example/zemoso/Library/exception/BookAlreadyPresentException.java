package com.example.zemoso.Library.exception;

public class BookAlreadyPresentException extends Exception{
    public BookAlreadyPresentException(String message){
        super(message);
    }
}
