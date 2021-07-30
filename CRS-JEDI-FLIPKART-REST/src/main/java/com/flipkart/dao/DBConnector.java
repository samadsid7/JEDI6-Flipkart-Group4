package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {

    private static Connection instance;

    private DBConnector(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs?user=brijesh1in");
            System.out.println("Connected!");

        }
        catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getInstance(){

        if(instance==null)
            new DBConnector();
        return instance;
    }
}
