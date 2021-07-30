package com.flipkart.dao;

import com.flipkart.bean.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CatalogDaoInterface {

    public ArrayList<Course> getAllCourses();
}
