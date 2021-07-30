package com.flipkart.bean;

import java.util.*;

public class CourseCatalog {

    private Map<String , Course> courses;

    private static CourseCatalog instance;

    private CourseCatalog(){

        courses = new HashMap<String , Course>();
        courses.put("CS001" , new Course("CS001" , "Java" , 10));
        courses.put("CS002",new Course("CS002" , "Design and Analysis of Algorithm" , 12));
        courses.put("MT003",new Course("MT003" , "Applied Mathematics" , 5));
        courses.put("ITE004",new Course("ITE004", "Real Analysis" , 15));
    }
    public static CourseCatalog getInstance(){

        if(instance==null){
            instance = new CourseCatalog();
        }
        return instance;
    }

    public void addCourse(Course course){

    }
    public void removeCourse(String courseCode){

    }

    public void updateCourse(Course course){

        
    }

    public Course getCourse(String courseId){

        return courses.getOrDefault(courseId , null);
    }

    public ArrayList<Course> getAllCourses(){
        ArrayList<Course> data = new ArrayList<>();
        for(Map.Entry course : courses.entrySet()){
            data.add((Course)course.getValue());
        }
        return data;
    }

}
