package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.CatalogDaoOperation;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.CourseNotTaughtException;
import com.flipkart.exceptions.GradesAlreadyGivenException;
import com.flipkart.exceptions.UserNotFoundException;
import org.apache.log4j.Logger;

import java.util.ArrayList;

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
    public ProfessorOperation(){

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
        }catch (GradesAlreadyGivenException ex){
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

        try {
            ProfessorDaoOperation operation = new ProfessorDaoOperation();
            operation.chooseCourse(professor.getId() , courseCode);
        }catch (UserNotFoundException ex){
            ex.printStackTrace();
        }

    }
    public void selectCourse(String profId , String courseCode) throws CourseAlreadyRegisteredException , UserNotFoundException{

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        operation.chooseCourse(profId , courseCode);
    }

    /**
     * Method to view list of enrolled Students
     * @param: courseCode: course code of the professor
     * @return: void
     * @throws CourseNotTaughtException
     */
    @Override
    public ArrayList<ArrayList<String>> viewEnrolledStudent(String courseCode) {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        try {
            ArrayList<ArrayList<String>> data = operation.getEnrolledStudents(professor.getId() , courseCode);
            logger.trace("============================================================");
            data.forEach(info -> logger.info(info));
            logger.trace("============================================================");
            return data;
        }
        catch (CourseNotTaughtException ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    public ArrayList<ArrayList<String>> viewEnrolledStudent(String profId , String courseCode) {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        try {
            ArrayList<ArrayList<String>> data = operation.getEnrolledStudents(profId , courseCode);
            logger.trace("============================================================");
            data.forEach(info -> logger.info(info));
            logger.trace("============================================================");
            return data;
        }
        catch (CourseNotTaughtException ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    /**
     * Method to get Courses by Professor Id
     * @return void
     */
    @Override
    public void viewCourses() {

        ArrayList<Course> enrolledCourses = new ProfessorDaoOperation().getCourseByProf(professor.getId());
        logger.trace("============================================================");
        for(Course course : enrolledCourses){
            logger.info(course.toString());
        }
        logger.trace("============================================================");

    }
    public ArrayList<Course> viewCourses(String profId){

        ArrayList<Course> enrolledCourses = new ProfessorDaoOperation().getCourseByProf(profId);
        logger.trace("============================================================");
        for(Course course : enrolledCourses){
            logger.info(course.toString());
        }
        logger.trace("============================================================");
        return enrolledCourses;
    }

    /**
     * Method to get all available courses
     * @return void
     */
    public void showAllCourses(){

        ArrayList<Course> allCourses = new CatalogDaoOperation().getAllCourses();
        logger.trace("============================================================");
        allCourses.forEach(course -> logger.info(course.toString()));

        logger.trace("============================================================");
    }

    public ArrayList<Course> showAllCourses(String profId){

        ArrayList<Course> allCourses = new CatalogDaoOperation().getAllCourses();
        logger.trace("============================================================");
        allCourses.forEach(course -> logger.info(course.toString()));

        logger.trace("============================================================");

        return allCourses;
    }
    public static Professor getProfessorDetails(String profId){

        return ProfessorDaoOperation.getProfessorDetails(profId);
    }
}
