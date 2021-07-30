package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.constant.Role;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;

import javax.validation.ValidationException;
import javax.validation.constraints.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/professors")
public class ProfessorRESTAPIController {

    @GET
    @Path("/professorDetails")
    @Produces(MediaType.APPLICATION_JSON)
    public Professor getProfessorDetails(
            @NotNull
            @QueryParam("profId") String profId){
        //  client --- service ---- dao ----> SQL
//        Professor professor = new Professor();
//        professor.setDepartment("lol");
//        professor.setRole(Role.PROF);
//        professor.setName("Amit");
//        System.out.println(profId);
        Professor info = ProfessorOperation.getProfessorDetails(profId);
        return info;
//        return ProfessorOperation.getProfessorDetails(profId);
    }

    @GET
    @Path("/getEnrolledStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ArrayList<String>> viewEnrolledStudents(
            @NotNull
            @QueryParam("profId") String profId,
            @QueryParam("courseCode") String courseCode) throws ValidationException {

        try
        {
            ArrayList<ArrayList<String>> data = new ProfessorOperation().viewEnrolledStudent(profId , courseCode);
            System.out.println(data);
            return data;
        }
        catch(Exception ex)
        {
            System.out.println("something went wrong");
            ex.printStackTrace();
            return null;
        }
    }

    @POST
    @Path("/addGrade")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGrade(
            @NotNull
            @Min(value = 1, message = "Student ID should not be less than 1")
            @Max(value = 9999, message = "Student ID should be less than 10000")
            @QueryParam("studentId") String studentId,

            @NotNull
            @Size(min = 4 , max = 10 , message = "Course Code length should be between 4 and 10 character")
            @QueryParam("courseCode") String courseCode,

            @QueryParam("grade") int grade) throws ValidationException {

        new ProfessorOperation().addGrades(studentId, courseCode, grade);
        return Response.status(200).entity("Added grades for : " + studentId).build();

    }



    @POST
    @Path("/chooseCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response chooseCourse(

            @NotNull
            @Size(min = 4 , max = 10 , message = "Course Code length should be between 4 and 10 character")
            @QueryParam("courseCode") String courseCode,


            @QueryParam("profId") String profId) throws ValidationException {

        try {
            new ProfessorOperation().chooseCourse(profId, courseCode);
            return Response.status(200).entity("Course Added").build();
        }catch (CourseAlreadyRegisteredException ex){

            return Response.status(200).entity("Course already chosen").build();
        }

    }

    @GET
    @Path("/viewCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Course> viewCourses(
            @NotNull
            @QueryParam("profId") String profId
    ) throws ValidationException{

        return new ProfessorOperation().viewCourses(profId);
    }

    @GET
    @Path("/getAllCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Course> getAllCourses(){

        return new ProfessorOperation().showAllCourses(null);
    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Professor professor) {
        System.out.println("hit post service");

        System.out.println("value of title from UI " + professor.getId());
        System.out.println("value of singer form UI" + professor.getName());

        String result = "Track saved : " + professor;

        return Response.status(201).entity(result).build();

    }
}