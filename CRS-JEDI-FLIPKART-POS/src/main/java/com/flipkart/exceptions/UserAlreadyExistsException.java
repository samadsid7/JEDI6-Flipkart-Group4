package com.flipkart.exceptions;

public class UserAlreadyExistsException extends Exception{

    private String userId;
    public UserAlreadyExistsException(String userId){
        this.userId = userId;
    }

    @Override
    public String getMessage(){
        return "User with " + this.userId + " already Exists .";
    }
}
