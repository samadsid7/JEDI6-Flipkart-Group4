package com.flipkart.dao;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exceptions.CourseFoundException;
import com.flipkart.exceptions.CourseNotFoundException;
import com.flipkart.exceptions.ProfessorNotAddedException;
import com.flipkart.exceptions.StudentNotFoundException;

public interface AdminDaoInterface {
    public void addCourse(Course course) throws CourseFoundException;
    public void removeCourse(String courseCode) throws CourseNotFoundException;
    public void addProfessor(Professor professor) throws ProfessorNotAddedException;
    public void approveStudent(String id) throws StudentNotFoundException;
}
