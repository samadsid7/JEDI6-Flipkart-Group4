package com.flipkart.exceptions;

public class InvalidCredentialsException extends Exception{

    public InvalidCredentialsException(){

    }
    @Override
    public String getMessage(){
        return "Wrong UserId or Password";
    }
}
