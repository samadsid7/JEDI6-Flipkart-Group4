package com.flipkart.exceptions;

/**
 * Exception to check if course is already present in catalog
 * @author JEDI-03
 */
public class CourseFoundException extends Exception
{
    private String courseCode;

    /***
     * Constructor
     * @param courseCode
     */
    public CourseFoundException(String courseCode) {
        this.courseCode = courseCode;
    }


    /**
     * Getter method
     * @return course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Return message when exception occurs
     */
    @Override
    public String getMessage() {
        String message="Course with courseCode: " + courseCode + " has already been registered in the catalog";
        return message;
    }
}