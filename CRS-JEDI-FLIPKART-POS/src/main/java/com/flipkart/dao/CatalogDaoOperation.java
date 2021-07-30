package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatalogDaoOperation implements CatalogDaoInterface{
    @Override
    public ArrayList<Course> getAllCourses() {

        Connection conn = DBConnector.getInstance();
        ArrayList<Course> result = new ArrayList<>();
        try {
            final String sql = "SELECT * FROM courseCatalog";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){

                String code = rs.getString("courseCode");
                String description = rs.getString("description");
                String course_name = rs.getString("courseName");
                Integer seats = rs.getInt("seats");

                Course tmp_course = new Course(code, course_name , seats , description);
                result.add(tmp_course);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }
}
