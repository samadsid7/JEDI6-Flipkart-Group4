package com.flipkart.exceptions;

/**
 * Exception to check if course is available in courseCatalog
 * @author JEDI-06
 */
public class CourseNotFoundException extends Exception {
    private String courseCode;

    /***
     * Constructor
     * @param courseCode
     */
    public CourseNotFoundException(String courseCode)
    {
        this.courseCode = courseCode;
    }

    /**
     * Getter function for course code
     * @return String
     */
    public String getCourseCode() {
        return courseCode;
    }


    /**
     * Return message on exception throw
     * @return String
     */
    @Override
    public String getMessage() {
        String message="Course with courseCode "+ courseCode + " does not exist";
        return message;
    }
}