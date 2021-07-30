package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.DBConnector;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.RegistrationNotCompleteException;
import org.apache.log4j.Logger;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;



@Path("/student")
public class StudentRESTAPIController {
    private static Logger logger = Logger.getLogger(StudentRESTAPIController.class);



    /**
     * Method to add course in database
     * @param courseCode : code for selected course
     * @param studentId : ID of student
     * @return Response
     * @throws SQLException
     */
    @POST
    @Path("/addCourse/{courseCode}/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@PathParam("courseCode") String courseCode, @PathParam("studentId") String studentId) {
        StudentDaoOperation operation=new StudentDaoOperation();
        try {
            operation.addCourse(studentId , courseCode);
        } catch (SQLException ex){
            ex.printStackTrace();
            return Response.status(500).entity("Error: Course cannot be added").build();
        }catch (RegistrationNotCompleteException ex){
            logger.warn(ex.getMessage());
            return Response.status(500).entity("Error: Course cannot be added").build();
        }
        return Response.status(201).entity("Course successfully added \t"+ courseCode).build();
    }



    /**
     * Method to add remove course in database
     * @param courseCode : code for selected course
     * @param studentId : ID of student
     * @return Response
     * @throws SQLException
     */
    @DELETE
    @Path("/dropCourse/{studentId}/{courseCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(@PathParam("studentId") String studentId, @PathParam("courseCode") String courseCode) throws ValidationException{
        StudentDaoOperation operation=new StudentDaoOperation();
        try{
            operation.dropCourse(studentId, courseCode);
            return Response.status(201).entity( "You have successfully dropped Course : " + courseCode).build();
        }
        catch(SQLException e)
        {
            logger.info(e.getMessage());
            return Response.status(501).entity("You cannot drop the course").build();
        }
    }

    /**
     * Method to view Grade Card
     * @param studentId : ID of student
     * @return List of Students Grade
     */
    @GET
    @Path("/viewGradeCard/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentGrade> viewGradeCard(@PathParam("studentId") String studentId) throws ValidationException, SQLException {
        StudentDaoOperation operation=new StudentDaoOperation();
        List<StudentGrade> gradeCard = operation.viewGradeCard(studentId);
        return gradeCard;
    }

    /**
     * Method to view all the enrolled courses
     * @param studentId
     * @throws SQLException
     */

    @GET
    @Path("/viewEnrolledCourses/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewEnrolledCourses(@PathParam("studentId") String studentId) throws ValidationException, SQLException {
        StudentDaoOperation operation=new StudentDaoOperation();
        List<Course> enrolledCourses = operation.viewEnrolledCourses(studentId);
        return enrolledCourses;
    }

    /**
     * Method to check if the fees has been paid
     * @param studentId
     */


    @GET
    @Path("/checkFeeAlreadyPaid/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkFeeAlreadyPaid(@PathParam("studentId") String studentId) throws ValidationException, SQLException {
        StudentDaoOperation operation=new StudentDaoOperation();
        boolean status= operation.checkFeeAlreadyPaid(studentId);
        if(status==true)
            return Response.status(201).entity( "You have already paid the fees" ).build();
        else
            return Response.status(500).entity("You need to pay the fees").build();

    }


    /**
     * Method to pay fees of the student
     * @param studentId
     * @param id
     * @param mode
     * @param password
     * @return Response
     */

    @POST
    @Path("/payFees/{studentId}/{mode}/{id}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response payFees(@PathParam("studentId") String studentId,
                            @PathParam("mode") String mode,
                           @PathParam("id") String id,
                           @PathParam("password") String password
                           ) throws ValidationException, SQLException {
        NotificationDaoOperation operation=new NotificationDaoOperation();
        String transactionID= operation.sendNotification(studentId,mode);
        return Response.status(201).entity( "Your transaction is successful . Your transaction Id is " + transactionID).build();
    }
}
