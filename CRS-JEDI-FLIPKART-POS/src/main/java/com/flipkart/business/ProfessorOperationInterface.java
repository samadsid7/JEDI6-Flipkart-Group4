package com.flipkart.business;

import com.flipkart.constant.Grade;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.CourseNotTaughtException;
import com.flipkart.exceptions.GradesAlreadyGivenException;

/**
 * @author JEDI-06-group-4
 *Interface to implement Professor Operations
 *
 */

public interface ProfessorOperationInterface {

    /**
     * Method to Grade a student
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @param grade: grade given to student in that course
     * @return: void
     */
    public void addGrades(String studentId , String courseCode , int grade);

    /**
     * Method to choose course which professor wants to teach
     * @param: courseCode: course code for the corresponding
     * @return: void
     * @throws CourseAlreadyRegisteredException
     */
    public void chooseCourse(String courseCode) throws CourseAlreadyRegisteredException;

    /**
     * Method to view list of enrolled Students 
     * @param: courseCode: course code of the professor
     * @return: void
     * @throws CourseNotTaughtException
     */
    public void viewEnrolledStudent(String courseCode);

    /**
     * Method to get Courses by Professor Id
     * @return void
     */
    public void viewCourses();
}
