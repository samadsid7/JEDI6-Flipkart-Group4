package com.flipkart.exceptions;

public class RegistrationNotCompleteException extends Exception{
    /**
     * Message returned when the registration is not completed
     * @return String
     */
    public RegistrationNotCompleteException(){
        super("Please complete the registration first");
    }
}
