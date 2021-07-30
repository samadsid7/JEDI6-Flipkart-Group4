package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exceptions.*;

/**
 * @author JEDI-06
 * Interface for Admin Dao Operations
 *
 */
public interface AdminDaoInterface
{
    /**
     * Add Course using SQL commands
     * @param course
     * @throws CourseFoundException
     */
    public void addCourse(Course course) throws CourseFoundException;
    /**
     * Remove Course using SQL commands
     * @param courseCode
     * @throws CourseNotFoundException
     */
    public void removeCourse(String courseCode) throws CourseNotFoundException;
    /**
     * Add professor using SQL commands
     * @param professor
     * @throws ProfessorNotAddedException
     */
    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserNotAddedException;
    /**
     * Approve Student using SQL commands
     * @param id
     * @throws StudentNotFoundException
     */
    public void approveStudent(String id) throws StudentNotFoundException;
}
