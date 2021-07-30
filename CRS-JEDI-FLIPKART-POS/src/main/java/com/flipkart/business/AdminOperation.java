package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminOperation implements AdminOperationInterface {

    public void addCourse(Course course)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.addCourse(course);
        } catch (CourseFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeCourse(String courseCode){
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.removeCourse(courseCode);
        } catch (CourseNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void approveStudent(String id)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.approveStudent(id);
        } catch (StudentNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void addProfessor(Professor professor)
    {
        AdminDaoOperation operation=new AdminDaoOperation();
        try {
            operation.addProfessor(professor);
        } catch (ProfessorNotAddedException e) {
            e.printStackTrace();
        }
    }

    public void viewStudents(){

        AdminDaoOperation operation = new AdminDaoOperation();
        ArrayList<Student> students = operation.getAllStudents();
        List<String> headers = Arrays.asList("Student Id");
    }

    public void viewProfessors(){

    }
}



