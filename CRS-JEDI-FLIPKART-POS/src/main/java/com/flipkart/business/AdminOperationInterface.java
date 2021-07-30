package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

public interface AdminOperationInterface {

    public void addCourse(Course course);
    public void removeCourse(String courseCode);
    public void approveStudent(String id);
    public void addProfessor(Professor professor);
}
