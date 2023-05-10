package com.book.projectps.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String Message){
        super(Message);
    }
}
