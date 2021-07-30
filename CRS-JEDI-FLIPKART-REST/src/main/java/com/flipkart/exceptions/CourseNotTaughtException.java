package com.flipkart.exceptions;

public class CourseNotTaughtException extends Exception{

    String courseCode;
    public CourseNotTaughtException(String courseCode){
        this.courseCode = courseCode;
    }

    @Override
    public String getMessage(){
        return "You don't teach course " + this.courseCode;
    }
}
