package com.flipkart.exceptions;

import com.flipkart.bean.Course;

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
