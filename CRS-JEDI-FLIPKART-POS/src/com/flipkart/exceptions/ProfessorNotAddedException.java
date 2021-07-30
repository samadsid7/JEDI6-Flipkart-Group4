package com.flipkart.exceptions;

public class ProfessorNotAddedException extends Exception{
    private String professorId;

    public ProfessorNotAddedException(String professorId) {
        this.professorId = professorId;
    }

    public String getProfessorId()
    {
        return this.professorId;
    }

    public String returnMessage()
    {
        String message= "professorId "+ professorId + "not added successsfully.";
    }


}
