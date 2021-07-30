package com.flipkart.bean;

public class Course {

    private String courseCode="NA";
    private String courseName="NA";
    private String description = "NA";
    private int seats = 10;

    public Course(){

    }
    public Course(String courseCode , String courseName , int seats){

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.seats = seats;
    }

    public Course(String courseCode , String courseName , int seats , String description){

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.seats = seats;
        this.description = description;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getSeats() {
        return seats;
    }

    public String getDescription(){
        return description;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                ", seats=" + seats +
                '}';
    }
}
