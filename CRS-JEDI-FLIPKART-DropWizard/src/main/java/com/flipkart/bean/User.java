package com.flipkart.bean;

import com.flipkart.constant.Role;

public class User {

    private String id;
    private String name;
    private String password;
    private Address address;
    private Role role;

    public User(){

    }
    /**
     * constructor
     * @param  id
     * @param name
     * @param password
     * @param role
     */
    public User(String id, String name, String password, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    /**
     * method to get Id
     * @return id
     */
    public String getId() {
        return id;
    }
    /**
     * method to get name
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * method to get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * method to get address
     * @return address
     */

    public Address getAddress() {
        return address;
    }

    public Role getRole() { return role; }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                '}';
    }
}
