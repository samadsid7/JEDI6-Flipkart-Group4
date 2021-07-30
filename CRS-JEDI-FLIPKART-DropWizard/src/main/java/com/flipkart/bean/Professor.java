package com.flipkart.bean;

import com.flipkart.constant.Role;

/**
 * @author JEDI-06-group-4
 *
 */

public class Professor extends User {

    private String department;
    private String designation;

    /**
     * Constructor
     */
    public Professor(){
        department = "CS";
        designation = "Assistant Professor";
    }

    /**
     * Constructor to set params
     * @param id: professor Id
     * @param name: professor Name
     * @param password: password for the professor account
     * @param role: professor
     */
    public Professor(String id , String name , String password , Role role){
        super(id , name , password , role);
    }

    /**
     *
     * @return: get the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     *
     * @param department: department for the professor
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *
     * @return: get the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     *
     * @param designation: designation for the professor
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
