/**
 * @author JEDI6 Group4
 */

package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.CatalogDaoInterface;
import com.flipkart.dao.CatalogDaoOperation;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.RegistrationNotCompleteException;
import com.flipkart.input.Helper;
import com.flipkart.input.TableGenerator;
import org.apache.log4j.Logger;
import sun.jvm.hotspot.memory.HeapBlock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentOperation implements StudentOperationInterface {
    Student student;
    private static Logger logger = Logger.getLogger(StudentOperation.class);

    /**
     * Parameterized Constructor for StudentOperation
     * @param student
     */

    public StudentOperation(Student student){
        this.student = student;
    }
    /**
     * Method to view enrolled courses
     */
    @Override
    public void viewEnrolledCourses() {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.viewEnrolledCourses(student.getId());
    }
    /**
     * Method to add course corresponding to student
     * @param courseCode : Course Code
     */
    @Override
    public void addCourse(String courseCode) {
        logger.info("Available Courses");
        viewAvailableCourses();
        StudentDaoOperation operation=new StudentDaoOperation();
        try {
            operation.addCourse(student.getId() , courseCode);
        } catch (SQLException ex){
            ex.printStackTrace();
        }catch (RegistrationNotCompleteException ex){
            logger.warn(ex.getMessage());
        }
    }
    /**
     * Method to register courses for students
     */
    @Override
    public void register() {

        viewAvailableCourses();
        final int minCourses = 4;
        CatalogDaoOperation operation = new CatalogDaoOperation();
        ArrayList<String> courses = new ArrayList<>();
        for(int i = 0 ; i < 4 ; ++i){
            String courseId = Helper.scanString("Course " + Integer.toString(i));
            courses.add(courseId);
        }
        StudentDaoOperation ops = new StudentDaoOperation();
        for(String course : courses){
            ops.addSingleCourse(this.student.getId() , course);
        }
        logger.info("Registration for courses successful");
    }
    /**
     * Method to drop courses for students
     */
    @Override
    public void dropCourse(String courseCode) {
        viewEnrolledCourses();
        StudentDaoOperation operation=new StudentDaoOperation();

        try {
            operation.dropCourse(student.getId() , courseCode);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    /**
     * Method to view grade card for students
     */
    @Override
    public void viewGradeCard() {
        StudentDaoOperation operation=new StudentDaoOperation();
        List<StudentGrade> gradeCard = new ArrayList<>();

        try {
            gradeCard = operation.viewGradeCard(student.getId());
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        if(gradeCard.isEmpty())
        {
            return;
        }
        List<String> headers = Arrays.asList("Course Code" , "Grades");
        List<List<String>> data = new ArrayList<>();
        for(StudentGrade obj : gradeCard)
        {
            data.add(Arrays.asList(obj.getCourseCode() , obj.getGrade()));
        }
        logger.info(new TableGenerator().generateTable(headers , data));
    }


    /**
     * Method to pay student fees
     */
    @Override
    public void payFees() {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.payFees(student.getId());
    }

    public void viewAvailableCourses(){

        StudentDaoOperation operation = new StudentDaoOperation();
        ArrayList<Course> courses = operation.getAvailableCourses(student.getId());
        printTable(courses);
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
