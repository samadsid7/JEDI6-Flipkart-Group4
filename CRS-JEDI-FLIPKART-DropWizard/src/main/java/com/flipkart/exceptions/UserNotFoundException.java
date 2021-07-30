package com.flipkart.exceptions;

public class UserNotFoundException extends Exception {

    private String userId;
    public UserNotFoundException(String userId){
        this.userId = userId;
    }

    @Override
    /**
     * Message returned when user with the given mail id is not found
     * @return String
     */
    public String getMessage(){
        return "No user found with userId " + userId;
    }
}
