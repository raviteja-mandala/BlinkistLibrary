package com.example.zemoso.library.exception;

import com.example.zemoso.library.entity.User;

public class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(String message){
        super(message);
    }
}
