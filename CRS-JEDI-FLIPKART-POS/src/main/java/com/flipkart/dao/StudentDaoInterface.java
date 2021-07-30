package com.flipkart.dao;

import com.flipkart.bean.StudentGrade;
import com.flipkart.exceptions.RegistrationNotCompleteException;

import java.sql.SQLException;
import java.util.List;

public interface StudentDaoInterface {
    /**
     * Method to view all the enrolled courses
     * @param studentId
     * @throws SQLException
     */
    void viewEnrolledCourses(String studentId);
    /**
     * Method to add course in database
     * @param courseCode : code for selected course
     * @param studentId : ID of student
     * @return void
     * @throws SQLException
     */

    void addCourse(String studentId, String courseCode) throws SQLException , RegistrationNotCompleteException;
    /**
     * Method to view grade card of the student
     * @param studentId
     * @throws SQLException
     * @return Student's grade card
     */
    List<StudentGrade> viewGradeCard(String studentId) throws SQLException;
    /**
     * Drop Course selected by student
     * @param courseCode : code for selected course
     * @param studentId : ID of student
     * @return void
     * * @throws SQLException
     */

    void dropCourse(String studentId, String courseCode) throws SQLException;

    /**
     * Method to pay fee of the student
     * @param studentId
     * @throws SQLException
     */
    void payFees(String studentId);

}
