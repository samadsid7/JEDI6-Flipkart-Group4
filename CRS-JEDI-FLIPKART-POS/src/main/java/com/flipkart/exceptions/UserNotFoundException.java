package com.flipkart.exceptions;

public class UserNotFoundException extends Exception {

    private String userId;
    public UserNotFoundException(String userId){
        this.userId = userId;
    }

    @Override
    public String getMessage(){
        return "No user found with userId " + userId;
    }
}
