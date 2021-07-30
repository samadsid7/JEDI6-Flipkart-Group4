package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.constant.Grade;
import com.flipkart.dao.CatalogDaoOperation;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.CourseNotTaughtException;
import com.flipkart.exceptions.GradesAlreadyGivenException;
import com.flipkart.exceptions.StudentNotFoundException;
import com.flipkart.input.TableGenerator;
import com.mysql.cj.log.Log;
import org.apache.log4j.Logger;
import sun.security.util.math.intpoly.P256OrderField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JEDI-06-group-4
 *Class for Professor Operations
 *
 */

public class ProfessorOperation implements ProfessorOperationInterface {

    Professor professor;
    private Logger logger = Logger.getLogger(ProfessorOperation.class);
    public ProfessorOperation(Professor professor){
        this.professor = professor;
    }

    /**
     * Method to Grade a student 
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @param grade: grade given to student in that course
     * @return: void
     */
    @Override
    public void addGrades(String studentId, String courseCode , int grade) {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        try {
            operation.addGrades(studentId , courseCode , grade);
            logger.info("Grades Added");
        }catch (GradesAlreadyGivenException ex){
            logger.error(ex.getMessage());
        }catch (StudentNotFoundException ex){
            logger.error(ex.getMessage());
        }
    }

    /**
     * Method to choose course which professor wants to teach 
     * @param: courseCode: course code for the corresponding
     * @return: void
     * @throws CourseAlreadyRegisteredException
     */
    @Override
    public void chooseCourse(String courseCode) throws CourseAlreadyRegisteredException {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        operation.chooseCourse(professor.getId() , courseCode);
    }

    /**
     * Method to view list of enrolled Students 
     * @param: courseCode: course code of the professor
     * @return: void
     * @throws CourseNotTaughtException
     */
    @Override
    public void viewEnrolledStudent(String courseCode) {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        try {
            ArrayList<ArrayList<String>> students = operation.getEnrolledStudents(professor.getId() , courseCode);
            List<String> headers = Arrays.asList("Student ID" , "Name" , "Branch");
            List<List<String>> data = new ArrayList<>(students);
            logger.info(new TableGenerator().generateTable(headers , data));
        }
        catch (CourseNotTaughtException ex){
            logger.error(ex.getMessage());
        }
    }

    /**
     * Method to get Courses by Professor Id 
     * @return void
     */
    @Override
    public void viewCourses() {

        ArrayList<Course> enrolledCourses = new ProfessorDaoOperation().getCourseByProf(professor.getId());
        printTable(enrolledCourses);
    }

    /**
     * Method to get all available courses 
     * @return void
     */
    public void showAllCourses(){

        ArrayList<Course> allCourses = new CatalogDaoOperation().getAllCourses();
        printTable(allCourses);
    }

    private void printTable(ArrayList<Course> courses){
        List<String> headers = Arrays.asList("Course Code" , "Course Name" , "Description" , "Seats");
        List<List<String>> data = new ArrayList<>();
        for(Course course : courses){

            List<String> row = Arrays.asList(
                    course.getCourseCode() ,
                    course.getCourseName() ,
                    course.getDescription() ,
                    Integer.toString(course.getSeats())
            );
            data.add(row);
        }
        logger.info(new TableGenerator().generateTable(headers , data));
    }
}
