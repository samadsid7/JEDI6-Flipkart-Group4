package com.flipkart.exceptions;

/**
 * Exception to check if the professor is not added successfully by admin
 * @author JEDI-06
 *
 */
public class ProfessorNotAddedException extends Throwable {
    private String professorId;

    /***
     * Constructor
     * @param professorId: ID of professor
     */
    public ProfessorNotAddedException(String professorId) {
        this.professorId = professorId;
    }

    /**
     * Getter function for professorId
     * @return String
     */
    public String getProfessorId()
    {
        return this.professorId;
    }

    /**
     * Message returned when exception is thrown
     * @return String
     */
    public String returnMessage()
    {
        String message= "professorId "+ professorId + "not added successsfully.";
        return message;
    }
}
