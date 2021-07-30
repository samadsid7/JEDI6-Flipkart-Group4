package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exceptions.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author JEDI-06
 * Dao Class Operation for Admin
 */
public class AdminDaoOperation implements AdminDaoInterface
{
    private static Logger logger = Logger.getLogger(AdminDaoOperation.class);

    private final Connection conn;
    /***
     * Constructor
     */
    public AdminDaoOperation()
    {
        conn = DBConnector.getInstance();
    }
    /**
     *
     * @param course
     * @return void
     * @throws CourseFoundException
     */
    @Override
    public void addCourse(Course course) throws CourseFoundException
    {
        try
        {
            String sql= SQLQueriesConstant.INSERT_COURSE;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, course.getCourseCode());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getDescription());
            stmt.setInt(4, course.getSeats());
            int row = stmt.executeUpdate();

            if(row==0)
            {
                logger.error("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
                throw new CourseFoundException(course.getCourseCode());
            }
            logger.info("Course having course Code: "+ course.getCourseName()+ " is added.");
        }
        catch(SQLException ex)
        {
            logger.error(ex.getMessage());
            throw new CourseFoundException(course.getCourseCode());
        }
    }
    /**
     *
     * @param courseCode
     * @return void
     * @throws CourseNotFoundException
     */
    @Override
    public void removeCourse(String courseCode) throws  CourseNotFoundException
    {
        try
        {
            String sql= SQLQueriesConstant.DELETE_COURSE;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseCode);
            int row = stmt.executeUpdate();
            if(row==0)
            {
                logger.error("The course having provided courseCode doesn't exist");
                throw new CourseNotFoundException(courseCode);
            }
            logger.info("Course with courseCode: " + courseCode + " removed.");
        }
        catch(SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }
    /**
     *
     * @param professor
     * @return void
     * @throws ProfessorNotAddedException
     */
    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserNotAddedException
    {
        try
        {

            String sql_prof=SQLQueriesConstant.INSERT_PROFESSOR;
            PreparedStatement stmt_prof = conn.prepareStatement(sql_prof);
            stmt_prof.setString(1, professor.getId());
            stmt_prof.setString(2, professor.getDesignation());
            stmt_prof.setString(3, professor.getDepartment());
            int row_prof= stmt_prof.executeUpdate();

            if(row_prof==0)
            {
                logger.error("Professor with professor ID:+: "+professor.getId()+ " not added in Professor table");
                throw new ProfessorNotAddedException(professor.getId());
            }

            logger.info("Professor having professorId: " + professor.getId() + " is added in Professor table.");

            String sql_user=SQLQueriesConstant.INSERT_USER;
            PreparedStatement stmt_user = conn.prepareStatement(sql_user);
            stmt_user.setString(1, professor.getId());
            stmt_user.setString(2, professor.getName());
            stmt_user.setString(3, professor.getPassword());
            stmt_user.setString(4, "PROFESSOR");
            int row_user = stmt_user.executeUpdate();

            if(row_user==0)
            {
                logger.error("Professor with professor ID:+: "+professor.getId()+ " not added in User table");
                throw new UserNotAddedException(professor.getId());
            }

            logger.info("Professor having professorId: " + professor.getId() + " is added in User table.");
        }
        catch(SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }
    /**
     *
     * @param id
     * @return void
     * @throws StudentNotFoundException
     */
    public void approveStudent(String id) throws StudentNotFoundException
    {
        try
        {
            String sql=SQLQueriesConstant.UPDATE_STUDENT;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            int row = stmt.executeUpdate();
            if(row==0)
            {
                logger.error("Student with studentId: " + id+ " does not exist");
                throw new StudentNotFoundException(id);
            }

            logger.info("Student with studentId: " + id + " has been approved");
        }
        catch(SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }
}