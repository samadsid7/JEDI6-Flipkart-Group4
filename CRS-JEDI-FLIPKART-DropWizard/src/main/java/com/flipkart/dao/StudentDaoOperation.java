
/**
 * @author : JEDI6 Group4
 */
package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exceptions.RegistrationNotCompleteException;
import com.flipkart.input.Helper;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoOperation implements StudentDaoInterface {
    private final Connection conn;
    private PreparedStatement stmt = null;
    private static Logger logger = Logger.getLogger(StudentDaoOperation.class);
    public StudentDaoOperation(){
        conn = DBConnector.getInstance();
    }


    /**
     * Method to view all the enrolled courses
     * @param studentId
     * @throws SQLException
     */

    public List<Course> viewEnrolledCourses(String studentId)
    {
        List<Course> ans= new ArrayList<>();
        try{
            stmt = conn.prepareStatement(SQLQueriesConstant.VIEW_ENROLLED_COURSES);
            stmt.setString(1 , studentId);
            ResultSet rs = stmt.executeQuery();


            while(rs.next()){
                String course_name = rs.getString("courseName");
                String courseId = rs.getString("courseCode");
                String description = rs.getString("description");
                int seats=rs.getInt("seats");
                Course course=new Course(courseId,course_name,seats,description);


                ans.add(course);

            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return ans;

    }

    /**
     * Method to add course in database
     * @param courseCode : code for selected course
     * @param studentId : ID of student
     * @return void
     * @throws SQLException
     */


    public void addCourse(String studentId, String courseCode) throws SQLException , RegistrationNotCompleteException {
        try
        {

            if(getCountOfRegisterdCourses(studentId) < 4){
                throw new RegistrationNotCompleteException();
            }
            stmt = conn.prepareStatement(SQLQueriesConstant.IS_COURSE_REGISTERED);
            stmt.setString(1, studentId);
            stmt.setString(2, courseCode);
            ResultSet rs1 = stmt.executeQuery();
            rs1.next();
            stmt = conn.prepareStatement(SQLQueriesConstant.GET_AVAILABLE_SEATS);
            stmt.setString(1, courseCode);
            ResultSet rs2 = stmt.executeQuery();
            rs2.next();
            if ((getCountOfRegisterdCourses(studentId) < 6) && (rs1.getInt("cnt") == 0) && (rs2.getInt("seats") > 0)) {
                stmt = conn.prepareStatement(SQLQueriesConstant.ADD_COURSE);
                stmt.setString(1, studentId);
                stmt.setString(2, courseCode);
                stmt.executeUpdate();
                stmt = conn.prepareStatement(SQLQueriesConstant.DECREASE_SEATS);
                stmt.setString(1, courseCode);
                stmt.executeUpdate();
                logger.info(String.format("%s registered", courseCode));
            }
            else if(rs1.getInt("cnt") != 0){
                logger.warn("This course is already registered");
            }
            else if(rs2.getInt("seats") <= 0){
                logger.warn("No seats left for this course");
            }
            else {
                logger.warn("Maximum Courses added. Drop Course to add more.");
            }
        }
        catch (SQLException e)
        {
            logger.info(e.getMessage());
        }
    }

    /**
     * Drop Course selected by student
     * @param courseCode : code for selected course
     * @param studentId : ID of student
     * @return void
     * * @throws SQLException
     */

    public void dropCourse(String studentId, String courseCode) throws SQLException {
        try
        {
            if (getCountOfRegisterdCourses(studentId) > 4) {
                stmt = conn.prepareStatement(SQLQueriesConstant.DROP_COURSE_QUERY);
                stmt.setString(1, courseCode);
                stmt.setString(2, studentId);
                stmt.execute();

                stmt = conn.prepareStatement(SQLQueriesConstant.INCREASE_SEATS);
                stmt.setString(1, courseCode);
                stmt.execute();
            }
            else {
                logger.warn("******  Can't Drop . Number of courses can't be less than 4 . *******");
            }
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
    }

    /**
     * Method to view grade card of the student
     * @param studentId
     * @throws SQLException
     * @return Student's grade card
     */

    public List<StudentGrade> viewGradeCard(String studentId) throws SQLException {

        List<StudentGrade> gradeCard = new ArrayList<>();
        try
        {
                stmt = conn.prepareStatement(SQLQueriesConstant.VIEW_GRADE);
                stmt.setString(1, studentId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String courseCode = rs.getString("courseCode");
                    String grade = rs.getString("grade");
                    StudentGrade obj = new StudentGrade(courseCode, grade);
                    gradeCard.add(obj);
                }
        }
        catch(SQLException e)
        {
            logger.info(e.getMessage());
        }
        catch(Exception e)
        {
            logger.info(e.getMessage());
        }
        return gradeCard;
    }



    /**
     * Method to pay fee of the student
     * @param studentId
     * @throws SQLException
     */

    public void payFees(String studentId) {

        Connection conn = DBConnector.getInstance();

        try{
           if(checkFeeAlreadyPaid(studentId)){
               logger.info("Fees already Paid");
           }
            else {
                System.out.println("******* Choose method to pay: **********");
                System.out.println("******* 1. Debit / Credit Card *********");
                System.out.println("******* 2. Netbanking ***********");
                System.out.println("******** 3. UPI ***********");
                String modeP[]={"Debit/Credit","netBanking","UPI"};
                Integer i = Helper.scanInt();
                switch(i) {
                    case 1:  String cardNumber = Helper.scanString("Card Number ");
                             String pin = Helper.scanString("PIN");
                                break;
                    case 2:  String bankingId = Helper.scanString("Customer ID");
                             String password = Helper.scanString("Password");
                                break;
                    case 3:  String UPI = Helper.scanString("UPI ID");
                             String upiPin = Helper.scanString("UPI PIN");
                                break;
                    default: System.out.println("******** invalid input. Enter Again. ********");
                                payFees(studentId);
                }
                stmt = conn.prepareStatement(SQLQueriesConstant.ADD_PAYMENT);
                stmt.setString(1, studentId);
                stmt.setInt(2,1);
                stmt.setString(3,modeP[i-1]);

                stmt.executeUpdate();
                NotificationDaoOperation notificationDaoOperation=new NotificationDaoOperation();
                String transactionId=notificationDaoOperation.sendNotification(studentId,modeP[i-1]);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();

        }
    }
    /**
     * Method to add course to student
     * @param studentId : StudentId
     * @param courseCode : CourseCode
     */

    public void addSingleCourse(String studentId , String courseCode){

        Connection conn = DBConnector.getInstance();
        logger.info(studentId);
        logger.info(courseCode);
        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.ADD_SINGLE_COURSE);
            stmt.setString(1 , studentId);
            stmt.setString(2 , courseCode);
            stmt.executeUpdate();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Method to check if the fees has already been paid
     * @param studentId
     */
    public boolean checkFeeAlreadyPaid(String studentId){

        Connection conn = DBConnector.getInstance();

        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.SELECT_PAYMENT_ROW);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int status = rs.getInt("feesPaid");
                if(status==1)
                    return true;
            }
        }catch (SQLException ex){
            logger.error(ex.getMessage());
        }
        return false;

    }

    /**
     * Method to get count of Registrered Courses
     * @param studentId
     * @return no of registered courses
     */
    private int getCountOfRegisterdCourses(String studentId){

        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.NUMBER_OF_REGISTERED_COURSES);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int count = rs.getInt("cnt");
                return count;
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
}
