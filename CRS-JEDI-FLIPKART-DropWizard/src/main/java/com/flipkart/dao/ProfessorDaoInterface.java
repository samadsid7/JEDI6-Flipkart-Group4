package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.CourseNotTaughtException;
import com.flipkart.exceptions.GradesAlreadyGivenException;

import java.util.ArrayList;

/**
 *
 * @author JEDI-06-group-4
 * Interface for Admin Dao Operations
 *
 */

public interface ProfessorDaoInterface {

    /**
     * Method to get Courses by Professor Id using SQL Commands
     * @param profId: prof id of the professor
     * @return get the courses offered by the professor.
     */
    ArrayList<Course> getCourseByProf(String profId);

    /**
     * Method to view list of enrolled Students using SQL Commands
     * @param: profId: professor id
     * @param: courseCode: course code of the professor
     * @return: return the enrolled students for the corresponding professor and course code.
     * @throws CourseNotTaughtException
     */
    ArrayList<ArrayList<String>> getEnrolledStudents(String profId , String courseCode) throws CourseNotTaughtException;

    /**
     * Method to Grade a student using SQL Commands
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @param grade: grade given to student in that course
     * @return: void
     * @throws GradesAlreadyGivenException
     */
    void addGrades(String studentId , String courseCode , int grade) throws GradesAlreadyGivenException;

    /**
     * Method to update student grades using SQL Commands
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @param grade: updated grade given to student in that course
     * @return: void
     */
    void updateGrades(String studentId , String courseCode , String grade);

    /**
     * Method to choose course which professor wants to teach using SQL Commands
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @return: void
     * @throws CourseAlreadyRegisteredException
     */
    void chooseCourse(String profId , String courseCode) throws CourseAlreadyRegisteredException;
}
