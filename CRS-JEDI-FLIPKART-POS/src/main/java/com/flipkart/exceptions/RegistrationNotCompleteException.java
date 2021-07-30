package com.flipkart.exceptions;

public class RegistrationNotCompleteException extends Exception{

    public RegistrationNotCompleteException(){
        super("Please complete the registration first");
    }
}
