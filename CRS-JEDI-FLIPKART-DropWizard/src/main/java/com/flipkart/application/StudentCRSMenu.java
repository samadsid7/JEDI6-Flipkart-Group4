package com.flipkart.application;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.StudentOperation;
import com.flipkart.constant.color;
import com.flipkart.input.Helper;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author JEDI-06 Group 4
 *  The class displays the menu for student client
 *
 */


public class StudentCRSMenu {

    private StudentOperation studentOperation;
    private Student student;

    /**
     * Parameterized Constructor for StudentCRSMenu
     * @param student
     */
    public StudentCRSMenu(Student student){
        this.student = student;
        studentOperation = new StudentOperation(student);
    }

    /**
     * Method to generate Student Menu for course registration, addition, removal and fee payment
     */


    public void menu() {

        Logger logger = Logger.getLogger(StudentCRSMenu.class);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("View Enrolled Course" , "Add Course" , "Drop Course" , "Register" ,"View Grade Card","Pay Fees","Log Out"));
        int count = 1;
        System.out.println(color.TEXT_BLUE+"**********************************************");
        System.out.println(color.TEXT_BLUE+"**********************************************"+color.TEXT_YELLOW);

        for(String value : list){
            System.out.println("*************  " + count + ". " + value + "  ****************");
            ++count;
        }
        System.out.println(color.TEXT_BLUE+"**********************************************");
        System.out.println(color.TEXT_BLUE+"**********************************************"+color.TEXT_GREEN);


        Integer value = Helper.scanInt();

        if(value==null || value > 7){
            logger.info("Invalid Option");
            menu();
        }
        else if(value == 1){
            studentOperation.viewEnrolledCourses();
        }
        else if(value==2){
            String  courseCode = Helper.scanString("courseCode");
            studentOperation.addCourse(courseCode);
        }
        else if(value==3){
            String  courseCode = Helper.scanString("courseCode");
            studentOperation.dropCourse(courseCode);
        }
        else if(value==4){
            studentOperation.register();
        }

        else if(value==5){

            studentOperation.viewGradeCard();
        }
        else if(value==6){
            studentOperation.payFees();
        }
        else if(value==7){
            logger.info("Logged Out Successfully");
            return;
        }
        menu();
    }
    /**
     * Method to signUp
     * @param list
     * throw SQLException
     *
     */

    public static void signUpMenu(ArrayList<User> list) throws SQLException {
        Student student = new Student();
        student.setName(Helper.scanString("Name"));
        student.setId(Helper.scanString("id"));
        student.setPassword(Helper.scanString("Password"));
        student.setBranch(Helper.scanString("Branch"));
        System.out.println("Student Registered successfully");
        list.add(student);
        StudentCRSMenu student1=new StudentCRSMenu(student);
        student1.menu();
    }
}
