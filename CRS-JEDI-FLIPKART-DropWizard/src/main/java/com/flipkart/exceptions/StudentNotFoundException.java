package com.flipkart.exceptions;

/**
 * This exception occurs when student is not found in student table during approval
 * @author JEDI-06
 */
public class StudentNotFoundException extends Exception
{
    private String studentId;
    /***
     * Constructor
     * @param studentId
     */
    public StudentNotFoundException(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Getter function for studentId
     * @return String
     */
    public String getStudentId() {
        return this.studentId;
    }


    /**
     * Return message on exception
     * @return String
     */
    @Override
    public String getMessage() {
        String message= "Student with studentId: " +studentId +"not found!";
        return message;
    }
}