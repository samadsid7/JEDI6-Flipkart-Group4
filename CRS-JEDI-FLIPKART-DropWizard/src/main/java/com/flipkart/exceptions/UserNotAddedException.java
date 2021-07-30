package com.flipkart.exceptions;

/**
 * Exception to check if the user is not added successfully by admin
 * @author JEDI-06
 *
 */
public class UserNotAddedException extends Throwable {
    private String userId;

    /***
     * Constructor
     * @param userId: ID of user
     */
    public UserNotAddedException(String userId) {
        this.userId = userId;
    }

    /**
     * Getter function for userId
     * @return String
     */
    public String getUserId()
    {
        return this.userId;
    }

    /**
     * Message returned when exception is thrown
     * @return String
     */
    public String returnMessage()
    {
        String message= "userId "+ userId + " not added successsfully.";
        return message;
    }
}

