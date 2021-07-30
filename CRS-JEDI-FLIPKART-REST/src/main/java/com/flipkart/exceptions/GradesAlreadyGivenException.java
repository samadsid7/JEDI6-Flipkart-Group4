package com.flipkart.exceptions;

public class GradesAlreadyGivenException extends Exception{

    private String studentId;
    public GradesAlreadyGivenException(String studentId){
        this.studentId = studentId;
    }

    @Override
    public String getMessage(){
        return "Grades has been already Given to " + studentId + " Use update Grade to change the grade";
    }
}
