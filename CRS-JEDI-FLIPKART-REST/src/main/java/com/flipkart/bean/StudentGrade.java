/**
 * @author JEDI6 Group4
 */

package com.flipkart.bean;

public class StudentGrade {

    private String courseCode;
    private String grade;

    public StudentGrade(){

    }
    /**
     * Constructor to initialize students and grade
     * @param courseCode : CourseCode
     * @param grade : Grade of the student
     */
    public StudentGrade(String courseCode, String grade) {
        this.courseCode = courseCode;
        this.grade = grade;
    }
    /**
     * Method to get CourseCode of Student
     * @return courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Method to set CourseCode of Student
     * @param courseCode : CourseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    /**
     * Method to get grade of Student
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Method to set grade of Student
     * @param grade
     */

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
