package com.flipkart.exceptions;

public class AuthException extends Exception{

    public AuthException(){
        super("UserId and password does not match");
    }
    public AuthException(String message){
        super(message);
    }
}
