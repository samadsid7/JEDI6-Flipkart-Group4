package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.business.AdminOperation;
import com.flipkart.constant.*;
import com.flipkart.exceptions.CourseFoundException;
import com.flipkart.input.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author JEDI-06
 * Application Class for Admin
 */
public class AdminCRSMenu {

    /**
     *
     * Menu for choosing the various admin functionalities
     * @return void
     * @throws CourseFoundException
     */
    public static void menu(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Add Course" , "Remove Course" , "Add Professor" , "Approve Student"));

        int count = 1;
        System.out.println(color.TEXT_BLUE+"**********************************************");
        System.out.println(color.TEXT_BLUE+"**********************************************"+color.TEXT_YELLOW);

        for(String value : list){
            System.out.println(count + ". " + value);
            count++;
        }
        System.out.println(color.TEXT_BLUE+"**********************************************");
        System.out.println(color.TEXT_BLUE+"**********************************************"+color.TEXT_GREEN);


        Integer value = Helper.scanInt();

        if(value==null || value > 4){
            System.out.println("Invalid Option");
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

        menu();
    }
    /**
     *
     * Login for Admin
     * @return void
     */
    public static void login(){

        String id = Helper.scanString("id");
        String password = Helper.scanString("Password");
        System.out.println("Login Success");
        menu();
    }

}

