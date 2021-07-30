package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;

/**
 * @author JEDI-06
 * Interface for Admin Operation
 *
 */
public interface AdminOperationInterface {

    /**
     * Add Course using Admin Dao Operation
     * @param course
     * @return void
     */
    public void addCourse(Course course);
    /**
     * Remove Course using Admin Dao Operation
     * @param courseCode
     * @return void
     */
    public void removeCourse(String courseCode);
    /**
     * Approve student using Admin Dao Operation
     * @param id
     * @return void
     */
    public void approveStudent(String id);
    /**
     * add Professor using Admin Dao Operation
     * @param professor
     * @return void
     */
    public void addProfessor(Professor professor);
}
