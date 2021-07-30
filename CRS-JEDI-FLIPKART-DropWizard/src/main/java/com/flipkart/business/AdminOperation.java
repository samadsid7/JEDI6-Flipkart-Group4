package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exceptions.*;
import org.apache.log4j.Logger;

/**
 * @author JEDI-06
 * Business Class Operation for Admin
 */
public class AdminOperation implements AdminOperationInterface {
    private Logger logger = Logger.getLogger(AdminOperation.class);


    /**
     *
     * @param course
     * @return void
     */
    public void addCourse(Course course)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.addCourse(course);
        } catch (CourseFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param courseCode
     * @return void
     */
    public void removeCourse(String courseCode){
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.removeCourse(courseCode);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param id
     * @return void
     */
    public void approveStudent(String id)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.approveStudent(id);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @param professor
     * @return void
     */
    public void addProfessor(Professor professor)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.addProfessor(professor);
        } catch (ProfessorNotAddedException | UserNotAddedException e) {

            e.printStackTrace();
        }
    }
}



