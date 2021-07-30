package com.flipkart.validator;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.dao.DBConnector;
import com.flipkart.exceptions.*;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {

    private Logger logger = Logger.getLogger(Authentication.class);
    public User login(String username , String password) throws UserNotApprovedException , InvalidCredentialsException {

        Connection conn = DBConnector.getInstance();
        final String sql = "SELECT * from users left join professor on profId = users.userId left join student on studentID = userId where userId = ? and password = ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , username);
            stmt.setString(2 , password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){

                String role = rs.getString("role");
                switch (role) {
                    case "Professor":
                        Professor professor = new Professor();
                        professor.setId(rs.getString("userId"));
                        professor.setDepartment(rs.getString("department"));
                        professor.setName(rs.getString("name"));
                        professor.setRole(Role.PROF);
                        professor.setDesignation(rs.getString("designation"));
                        return professor;
                    case "Admin":
                        Admin admin = new Admin();
                        admin.setId(rs.getString("userId"));
                        admin.setName(rs.getString("name"));
                        admin.setRole(Role.ADMIN);
                        return admin;

                    case "Student":
                        if(!rs.getBoolean("isApproved")){
                            throw new UserNotApprovedException();
                        }
                        Student student = new Student();
                        student.setName(rs.getString("name"));
                        student.setBranch(rs.getString("branch"));
                        student.setId(rs.getString("userId"));
                        student.setRole(Role.STUDENT);
                        return student;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new InvalidCredentialsException();
    }

    public void register(String id , String name , String password , String branch) throws UserAlreadyExistsException {

        Connection conn = DBConnector.getInstance();
        if(checkUserExists(id)){
            throw new UserAlreadyExistsException(id);
        }
        try{
            final String sql = "INSERT INTO users values (? , ? , ? , ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , id);
            stmt.setString(2 , name);
            stmt.setString(3 , password);
            stmt.setString(4 , "Student");
            stmt.executeUpdate();
            stmt = conn.prepareStatement(SQLQueriesConstant.addStudent);
            stmt.setString(1 , id);
            stmt.setString(2 , branch);
            stmt.setBoolean(3 , false);
            stmt.executeUpdate();
            logger.info("Signup Success .");

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void updatePassword(String id , String oldPass , String newPass , String newPass2) throws AuthException , UserNotFoundException , InvalidCredentialsException {

        if(!newPass.equals(newPass2)){
            throw new AuthException("New Password doesn't match");
        }
        if(!checkUserExists(id)){
            throw new UserNotFoundException(id);
        }
        if(!checkUserExists(id , oldPass)){
            throw new InvalidCredentialsException();
        }

        Connection conn = DBConnector.getInstance();

        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.updatePassword);
            stmt.setString(1 , newPass);
            stmt.setString(2 , id);
            stmt.executeUpdate();
            logger.info("Password Updated");
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private boolean checkUserExists(String userId){

        Connection conn = DBConnector.getInstance();

        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.getUserByID);
            stmt.setString(1 , userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    private boolean checkUserExists(String userId , String password){

        Connection conn = DBConnector.getInstance();

        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.getUserByIDAndPassword);
            stmt.setString(1 , userId);
            stmt.setString(2 , password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
