package com.flipkart.exceptions;

public class CourseAlreadyRegisteredException extends Exception{

    String courseCode;
    public CourseAlreadyRegisteredException(String courseCode){
        this.courseCode = courseCode;
    }

    @Override
    public String getMessage(){
        return "Course " + this.courseCode + " already registered";
    }
}
