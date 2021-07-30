package com.flipkart.application;

import com.flipkart.business.AdminOperation;
import com.flipkart.input.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.*;
import com.flipkart.input.Helper;
import org.apache.log4j.Logger;

import java.util.*;

public class AdminCRSMenu {

    private static final Logger logger = Logger.getLogger(AdminCRSMenu.class);
    public static void menu(){
        ArrayList<String> list = new ArrayList<>(
                Arrays.asList(
                "Add Course" ,
                "Remove Course" ,
                "Add Professor" ,
                "Approve Student" ,
                "View Students",
                "View Professors",
                "Log out"
                )
        );

        int count = 1;
        for(String value : list){
            System.out.println(count + ". " + value);
            count++;
        }

        Integer value = Helper.scanInt();

        if(value==null || value > 7){
            logger.warn("Invalid Option");
            menu();
        }
        else if(value == 1){
            Course course=new Course();

            String course_code=Helper.scanString("the course code:->");
            course.setCourseCode(course_code);
            String course_name=Helper.scanString("the course name:->");
            course.setCourseName(course_name);
            String description=Helper.scanString("the course description:->");
            course.setDescription(description);
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the seats:->");
            int seats=sc.nextInt();
            course.setSeats(seats);

            AdminOperation operation=new AdminOperation();
            operation.addCourse(course);
        }
        else if(value==2){

            String course_code=Helper.scanString("the course code");
            AdminOperation operation=new AdminOperation();
            operation.removeCourse(course_code);
        }
        else if(value==3)
        {

            String professor_id=Helper.scanString("the Professor_id:->");
            String professor_name=Helper.scanString("the professor Name:->");
            String professor_password=Helper.scanString("the password:->");
            Professor professor=new Professor(professor_id, professor_name, professor_password, Role.PROF);
            String professor_designation=Helper.scanString("the professor designation:->");
            professor.setDesignation(professor_designation);
            String professor_department=Helper.scanString("professor department:->");
            professor.setDepartment(professor_department);
            AdminOperation operation=new AdminOperation();
            operation.addProfessor(professor);
        }
        else if(value==4){

            String student_id=Helper.scanString("the student Id");
            AdminOperation operation=new AdminOperation();
            operation.approveStudent(student_id);
        }
        else if(value==5){

            // View Students should be called .
        }
        else if(value==6){
            // View Professor should be called .
        }
        else if(value==7){
            return;
        }

        menu();
    }

    public static void login(){

        String id = Helper.scanString("id");
        String password = Helper.scanString("Password");
        System.out.println("Login Success");
        menu();
    }

}

/*
Notes :
Make CRsMenu to Menu .
Create a separate User Interface for Admin .
*/
