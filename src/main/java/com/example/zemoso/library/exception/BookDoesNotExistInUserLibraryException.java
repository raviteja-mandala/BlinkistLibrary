package com.example.zemoso.library.exception;

public class BookDoesNotExistInUserLibraryException extends Exception{
    public BookDoesNotExistInUserLibraryException(String message){
        super(message);
    }
}
