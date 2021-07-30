package com.flipkart.bean;

import com.flipkart.constant.Role;

/**
 *
 * @author JEDI-06
 * Admin Class
 *
 */
public class Admin extends User {

    public Admin(){

    }

    /**
     * Constructor
     * @param id
     * @param name
     * @param password
     * @param address
     * @param role
     */
    public Admin(String id, String name,String password,Address address,Role role) {
            setId(id);
            setName(name);
            setPassword(password);
            setAddress(address);
            setRole(role);
    }

    /**
     *Constructor
     * @param id
     * @param name
     * @param password
     * @param role
     */
    public Admin(String id , String name , String password , Role role){
        super(id , name , password , role);
    }
}
