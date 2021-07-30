package com.flipkart.constant;

public class SQLQueriesConstant {

    // All the Professor queries will be here
    /**
     * This file contains constant queries .
     */
    public static final String viewEnrolledStudents = "SELECT  rC.studentID , name , branch from registeredCourse rC\n" +
            "    JOIN users on users.userid = rC.studentID\n" +
            "    JOIN student on student.studentID = rC.studentID\n" +
            "    WHERE courseCode = ?;";
    public static final String getProfWithCourse = "SELECT * FROM instructor where profId = ? and courseCode = ?";
    public static final String addGrades = "update registeredCourse set grade = ? where studentId = ? and courseCode = ?";



    public static final String getUserByID = "SELECT userId from users WHERE userId = ?";
    public static final String getUserByIDAndPassword = "SELECT userId from users WHERE userId = ? and password = ?";
    public static final String updatePassword = "UPDATE users SET password = ? WHERE userId = ?";




    // Student
    public static final String addStudent = "INSERT INTO student values (? , ? , ?)";
    public static final String IS_COURSE_REGISTERED = "select count(*) as cnt from registeredCourse where studentId = ? AND courseCode = ?;";
    public static final String GET_AVAILABLE_SEATS = "select seats from courseCatalog where courseCode = ?;";
    public static final String ADD_SINGLE_COURSE = "INSERT INTO registeredCourse values(? , ? ,'NA' , 0)";
    public static final String DECREASE_SEATS="update courseCatalog set seats = seats-1 where courseCode = ? ";
    public static final String ADD_COURSE ="insert into registeredCourse (studentId,courseCode) values ( ? , ? )";
    public static final String DROP_COURSE_QUERY = "delete from registeredcourse where courseCode = ? AND studentId = ?;";
    public static final String INCREASE_SEATS  = "update courseCatalog set seats = seats + 1 where  courseCode = ?;";
    public static final String NUMBER_OF_REGISTERED_COURSES=" select count(studentId) as cnt from registeredCourse where studentId = ?;";
    public static final String VIEW_GRADE = "select courseCode,grade from registeredCourse where studentId = ?;";
    public static final String ADD_PAYMENT ="INSERT INTO payment values ( ? , ? , ?)";
    public static final String SELECT_PAYMENT_ROW =  "select * from payment where studentId = ?";
    public static final String VIEW_ENROLLED_COURSES = "SELECT * FROM courseCatalog WHERE courseCode in (select courseCode from registeredCourse where studentid = ?)";



    // Admin
    public static final String UPDATE_STUDENT="update student set isApproved = true where studentID = ?";
    public static final String DELETE_COURSE = "delete from courseCatalog where courseCode = ?";
    public static final String INSERT_PROFESSOR= "insert into professor(profId, department,designation) values (?, ?, ?)";
    public static final String INSERT_USER= "insert into users(userId, name,password, role) values (?, ?, ?, ?)";

    public static final String INSERT_COURSE = "insert into courseCatalog(courseCode, courseName, description, seats) values (?, ?, ?, ?)";
    public static final String VIEW_STUDENT = "select * from Student join users on users.userid = student.studentId";
    public static final String VIEW_PROFESSOR= "select * from Professor join users on users.userid = Professor.profId";
}
