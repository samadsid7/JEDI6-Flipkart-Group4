
/**
 * @author JEDI-06
 * Application Class for Admin
 */

package com.flipkart.client;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.Role;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exceptions.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/admin")
public class AdminRESTAPIController  {
    /**
     * Approve Student using SQL commands
     * @param studentId
     * @throws StudentNotFoundException
     */
    @PUT
    @Path("/approveStudent/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(@PathParam("studentId") String studentId) throws StudentNotFoundException {
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.approveStudent(studentId);
        } catch (StudentNotFoundException ex){
            ex.printStackTrace();
            return Response.status(500).entity("Error: Student not approved").build();
        }
        return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();
    }


    /**
     * Remove Course using SQL commands
     * @param courseCode
     * @throws CourseNotFoundException
     */
    @DELETE
    @Path("/removeCourse/{courseCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeCourse(@PathParam("courseCode") String courseCode) throws CourseNotFoundException {
        AdminDaoOperation operation=new AdminDaoOperation();
        try
        {
            operation.removeCourse(courseCode);
            return Response.status(201).entity("Course with courseCode: " + courseCode + " deleted from catalog").build();
        }
        catch (CourseNotFoundException e)
        {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * Add Course
     * @param courseCode
     * @param courseName
     * @param description
     * @param seats
     * @throws CourseFoundException
     */
    @POST
    @Path("/addCourse/{courseCode}/{courseName}/{description}/{seats}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@PathParam("courseCode") String courseCode, @PathParam("courseName") String courseName,
                              @PathParam("description") String description, @PathParam("seats") int seats) throws CourseFoundException{
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            Course course=new Course(courseCode,  courseName, seats, description);
            operation.addCourse(course);
            return Response.status(201).entity("Course with courseCode: " + course.getCourseCode() + " added to catalog").build();
        } catch (CourseFoundException e) {
            e.printStackTrace();
            return Response.status(500).entity("Error: Course could not be added").build();
        }
    }


    /**
     * Add professor
     * @param profId
     * @param department
     * @param designation
     * @param Name
     * @param Password
     * @throws ProfessorNotAddedException
     */
    @POST
    @Path("/addProfessor/{profId}/{Name}/{Password}/{designation}/{department}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(@PathParam("profId") String profId, @PathParam("Name") String Name,@PathParam("Password") String Password,
                                 @PathParam("designation") String designation,@PathParam("department") String department) throws ProfessorNotAddedException, UserNotAddedException{
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            Professor professor=new Professor(profId, Name, Password, Role.PROF);
            professor.setDesignation(designation);
            professor.setDepartment(department);
            operation.addProfessor(professor);
        } catch (ProfessorNotAddedException e) {
            e.printStackTrace();
            return Response.status(500).entity("Error: Professor cannot be added in Professor table").build();
        } catch (UserNotAddedException e) {
            e.printStackTrace();
            return Response.status(500).entity("Error: User cannot be added in User table").build();
        }
        return Response.status(201).entity(" Professor successfully added ").build();
    }

    @GET
    @Path("/viewProfessors")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Professor> viewProfessor() throws SQLException {
        AdminDaoOperation operation = new AdminDaoOperation();
        try {
            return operation.viewProfessor();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * View student
     *
     * @throws SQLException
     */
    @GET
    @Path("/viewStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Student> viewStudent() throws SQLException {
        AdminDaoOperation operation = new AdminDaoOperation();
        try {
            return operation.viewStudent();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}