package com.flipkart.bean;

public class Course {

    private String courseCode="NA";
    private String courseName="NA";
    private String description = "NA";
    private int seats = 10;

    public Course(){

    }
    /**
     *Constructor
     * @param courseCode
     * @param courseName
     * @param seats
     */
    public Course(String courseCode , String courseName , int seats){

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.seats = seats;
    }
    /**
     *Constructor
     * @param courseCode
     * @param courseName
     * @param seats
     * @param description
     */

    public Course(String courseCode , String courseName , int seats , String description){

        this.courseCode = courseCode;
        this.courseName = courseName;
        this.seats = seats;
        this.description = description;
    }
    /**
     * method to get course code
     * @return courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * method to get course name
     * @return courseName
     */

    public String getCourseName() {
        return courseName;
    }
    /**
     * method to get seats
     * @return seats
     */

    public int getSeats() {
        return seats;
    }

    /**
     * method to get course description
     * @return description
     */

    public String getDescription(){
        return description;
    }

    /**
     * method to set course code
     * @param  courseCode

     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }


    /**
     * method to set course name
     * @param  courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * method to set number of seats
     * @param  seats
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * method to set description
     * @param  description
     */

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
