package com.flipkart.dao;
import java.sql.*;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exceptions.*;

/**
 * @author JEDI-06
 * Dao Class Operation for Admin
 */
public class AdminDaoOperation implements AdminDaoInterface {
    private final Connection conn;
    public AdminDaoOperation()
    {
        conn = DBConnector.getInstance();
    }
    /**
     *
     * @param course
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
            System.out.println(row +" Course Added");
            if(row==0)
            {
                System.out.println("Course with Course Code: " +course.getCourseCode()+ "not added to catalog");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new CourseFoundException(course.getCourseCode());
        }
    }
    /**
     *
     * @param courseCode
     * @throws CourseNotFoundException
     */
    @Override
    public void removeCourse(String courseCode) throws  CourseNotFoundException
    {
        try
        {
            String sql="delete from courseCatalog where courseCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, courseCode);
            int row = stmt.executeUpdate();
            if(row==0)
            {
                System.out.println("The course having provided courseCode doesn't exist");
                throw new CourseNotFoundException(courseCode);
            }
            System.out.println(row +" Course Removed");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    /**
     *
     * @param professor
     * @throws ProfessorNotAddedException
     */
    public void addProfessor(Professor professor) throws ProfessorNotAddedException
    {
        try
        {
            /**
             * insertion in professor table
             */
            String sql_prof="insert into professor(profId, department,designation) values (?, ?, ?)";
            PreparedStatement stmt_prof = conn.prepareStatement(sql_prof);
            stmt_prof.setString(1, professor.getId());
            stmt_prof.setString(2, professor.getDesignation());
            stmt_prof.setString(3, professor.getDepartment());
            int row_prof= stmt_prof.executeUpdate();
            System.out.println(row_prof+" Professor Added in Professor");
            if(row_prof==0)
            {
                System.out.println("Professor with professor ID:+: "+professor.getId()+ " not added");
                throw new ProfessorNotAddedException(professor.getId());
            }
            /**
             * insertion in user table
             */
            String sql_user="insert into users(userId, name,password, role) values (?, ?, ?, ?)";
            PreparedStatement stmt_user = conn.prepareStatement(sql_user);
            stmt_user.setString(1, professor.getId());
            stmt_user.setString(2, professor.getName());
            stmt_user.setString(3, professor.getPassword());
            stmt_user.setString(4, "PROFESSOR");
            int row_user = stmt_user.executeUpdate();
            System.out.println(row_user +" Professor Added in User");
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    /**
     *
     * @param id
     * @throws StudentNotFoundException
     */
    public void approveStudent(String id) throws StudentNotFoundException
    {
        try
        {
            String sql="update student set isApproved = true where studentID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            int row = stmt.executeUpdate();
            if(row==0)
            {
                System.out.println("Student with provided student id doesn't exist");
                throw new StudentNotFoundException(id);
            }
            else
            {
                System.out.println("Student with student id: " + id + " is approved");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public ArrayList<Student> getAllStudents(){

        Connection conn = DBConnector.getInstance();
        ArrayList<Student> students = new ArrayList<>();
        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.GET_ALL_STUDENTS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Student student = new Student();
                student.setName(rs.getString("name"));
                student.setId(rs.getString("userId"));
                student.setRole(Role.STUDENT);
                student.setBranch("branch");
                students.add(student);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return students;
    }

    public ArrayList<Professor> getAllProfessors(){

        Connection conn = DBConnector.getInstance();
        ArrayList<Professor> professors = new ArrayList<>();

        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.GET_ALL_PROFESSORS);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Professor professor = new Professor();
                professor.setName(rs.getString("name"));
                professor.setDepartment(rs.getString("department"));
                professor.setDesignation(rs.getString("designation"));
                professor.setRole(Role.PROF);
                professor.setId(rs.getString("userId"));
                professors.add(professor);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return professors;
    }
}