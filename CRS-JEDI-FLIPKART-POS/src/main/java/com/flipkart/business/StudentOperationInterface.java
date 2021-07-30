package com.flipkart.business;

import com.flipkart.bean.Student;

import java.sql.SQLException;

public interface StudentOperationInterface {
    /**
     * Method to view enrolled courses
     */
    void viewEnrolledCourses();

    /**
     * Method to add course corresponding to student
     * @param courseCode : Course Code
     */
    void addCourse(String courseCode) throws SQLException;
    /**
     * Method to registr courses for students
     */
    void register();

    /**
     * Method to drop courses for students
     * @throws SQLException
     */

    void dropCourse(String courseCode) throws SQLException;
    /**
     * Method to view grade card for students
     * @throws SQLException
     */
    void viewGradeCard() throws SQLException;
    /**
     * Method to pay fees for students
     */
    void payFees();
}
