package com.flipkart.exceptions;

public class UserNotApprovedException extends Exception{

    public UserNotApprovedException(){

    }

    @Override
    public String getMessage(){
        return "You are not approved by Admin yet";
    }
}
