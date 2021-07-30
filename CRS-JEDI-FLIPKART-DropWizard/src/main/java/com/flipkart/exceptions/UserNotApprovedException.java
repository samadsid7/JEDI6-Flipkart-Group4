package com.flipkart.exceptions;

public class UserNotApprovedException extends Exception{

    public UserNotApprovedException(){

    }
    /**
     * Message returned when exception is thrown
     * @return String
     */
    @Override
    public String getMessage(){
        return "You are not approved by Admin yet";
    }
}
