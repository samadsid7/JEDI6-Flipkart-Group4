package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.constant.Color;
import com.flipkart.constant.Grade;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.input.Helper;
import com.flipkart.validator.Authentication;
import org.apache.log4j.Logger;
import sun.security.util.math.intpoly.P256OrderField;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author JEDI-06-group-4
 * Class that display Professor Client Menu
 *
 */

public class ProfessorCRSMenu {

    private final ProfessorOperation professorOperation;
    private Professor professor;
    public ProfessorCRSMenu(Professor professor){
        this.professor = professor;
        professorOperation = new ProfessorOperation(professor);

    }

    /**
     * Method to create Professor menu
     * displays all the options for the professor, and provides navigation
     */
    public void menu(){

        Logger logger = Logger.getLogger(ProfessorCRSMenu.class);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Add Grades" , "Choose Course" , "View Enrolled Student" , "View Courses"  , "List All courses" , "Logout"));

        int count = 1;
//        System.out.println("============= Select from the below ===============");
//        for(String value : list){
//            System.out.println("              " + count + ". " + value);
//            ++count;
//        }
        System.out.println(Color.TEXT_BLUE+"**********************************************");        
        System.out.println(Color.TEXT_BLUE+"**********************************************"+Color.TEXT_YELLOW);
        for(String value : list){            
            System.out.println("*************  " + count + ". " + value + "  ****************");
            ++count;        
        }
        System.out.println(Color.TEXT_BLUE+"**********************************************");        
        System.out.println(Color.TEXT_BLUE+"**********************************************"+Color.TEXT_GREEN);
        Integer value = Helper.scanInt();

        if(value==null || value > 6){
            logger.info("Invalid Option");
            menu();
        }
        else if(value == 1){

            String studentId = Helper.scanString("studentId");
            String courseCode = Helper.scanString("course code");
            Integer grade = Helper.scanInt("grades [5 to 10]");
            professorOperation.addGrades(studentId , courseCode , grade);

        }
        else if(value==2){
            String  courseCode = Helper.scanString("courseCode");
            try {
                professorOperation.chooseCourse(courseCode);
            } catch (CourseAlreadyRegisteredException ex){
                logger.error(ex.getMessage());
            }
        }
        else if(value==3){
            String courseCode = Helper.scanString("course code");
            professorOperation.viewEnrolledStudent(courseCode);
        }
        else if(value==4){
            professorOperation.viewCourses();
        }

        else if(value==5){
            professorOperation.showAllCourses();
        }
        else if(value==6){
            logger.info("Logged out successfully");
            return;
        }
        menu();
    }
}
