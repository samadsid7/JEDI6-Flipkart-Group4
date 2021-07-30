package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.CourseNotTaughtException;
import com.flipkart.exceptions.GradesAlreadyGivenException;
import com.flipkart.exceptions.StudentNotFoundException;
import com.mysql.cj.log.Log;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JEDI-06-group-4
 * Class to implement Professor Dao Operations
 *
 */

public class ProfessorDaoOperation implements ProfessorDaoInterface {

    private final Connection conn;
    private final Logger logger = Logger.getLogger(ProfessorDaoOperation.class);
    public ProfessorDaoOperation(){
        conn = DBConnector.getInstance();
    }

    /**
     * Method to get Courses by Professor Id using SQL Commands
     * @param profId: prof id of the professor
     * @return get the courses offered by the professor.
     */
    @Override
    public ArrayList<Course> getCourseByProf(String profId) {

        ArrayList<Course> result = new ArrayList<>();
        try{
            String sql = "SELECT * FROM courseCatalog WHERE coursecode in (select coursecode from instructor where profId = ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , profId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                String code = rs.getString("courseCode");
                String description = rs.getString("description");
                String course_name = rs.getString("courseName");
                Integer seats = rs.getInt("seats");

                Course tmp_course = new Course(code, course_name , seats , description);
                result.add(tmp_course);
            }

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Method to view list of enrolled Students using SQL Commands
     * @param: profId: professor id
     * @param: courseCode: course code of the professor
     * @return: return the enrolled students for the corresponding professor and course code.
     * @throws CourseNotTaughtException
     */
    @Override
    public ArrayList<ArrayList<String>> getEnrolledStudents(String profId , String courseCode) throws CourseNotTaughtException {

        Connection conn = DBConnector.getInstance();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if(!checkCourseIsTaught(profId , courseCode)){
            throw new CourseNotTaughtException(courseCode);
        }
        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.viewEnrolledStudents);
            stmt.setString(1 , courseCode);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                ArrayList<String> tmp = new ArrayList<>();
                String id = rs.getString("studentId");
                String name = rs.getString("name");
                String branch = rs.getString("branch");
                tmp.add(id);
                tmp.add(name);
                tmp.add(branch);
                result.add(tmp);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Method to Grade a student using SQL Commands
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @param grade: grade given to student in that course
     * @return: void
     * @throws GradesAlreadyGivenException
     */
    @Override
    public void addGrades(String studentId, String courseCode, int grade) throws GradesAlreadyGivenException , StudentNotFoundException {

        if (checkIfGradesAlreadyGiven(studentId , courseCode)){
            throw new GradesAlreadyGivenException(studentId);
        }
        if(!checkIfStudentRegisteredForCourse(studentId , courseCode)){
            throw new StudentNotFoundException(studentId);
        }
        try {
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.addGrades);
            stmt.setString(1 , Integer.toString(grade));
            stmt.setString(2 , studentId);
            stmt.setString(3, courseCode);
            stmt.executeUpdate();
            logger.info("Grades Added");

        }catch (SQLException ex){
                logger.error(ex.getMessage());
                ex.printStackTrace();
        }
    }

    /**
     * Method to update student grades using SQL Commands
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @param grade: updated grade given to student in that course
     * @return: void
     */
    @Override
    public void updateGrades(String studentId, String courseCode, String grade) {
        try {
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.addGrades);
            stmt.setString(1 , grade);
            stmt.setString(2 , studentId);
            stmt.setString(3, courseCode);
            stmt.executeUpdate();
            logger.info("Grades Updated");

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Method to choose course which professor wants to teach using SQL Commands
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @return: void
     * @throws CourseAlreadyRegisteredException
     */
    @Override
    public void chooseCourse(String profId , String courseCode) throws CourseAlreadyRegisteredException {

        Connection conn = DBConnector.getInstance();
        if( checkIfSignedUp(profId , courseCode))
            throw new CourseAlreadyRegisteredException(courseCode);

        final String sql = "INSERT INTO instructor values (? , ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , profId);
            stmt.setString(2 , courseCode);
            stmt.executeUpdate();
            System.out.println("Course added successfully .");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Method to update student grades using SQL Commands
     * @param: profId: professor id
     * @param: courseCode: course code for the corresponding
     * @return: whether instructor is signed up for the course or not
     */
    private boolean checkIfSignedUp(String profId , String courseCode){

        final String sql = "SELECT * from instructor where profId = ? and coursecode = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , profId);
            stmt.setString(2 , courseCode);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;

    }

    /**
     * Method to update student grades using SQL Commands
     * @param: studentId:  Student id
     * @param: courseCode: course code for the corresponding
     * @return: whether grades for the course is already provided or not
     */
    private boolean checkIfGradesAlreadyGiven(String studentId , String courseCode){

        Connection conn = DBConnector.getInstance();

        try{
            final String sql = "SELECT * from grades where studentId = ? and courseCode = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            stmt.setString(2 , courseCode);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Method to update student grades using SQL Commands
     * @param: profId:  professor id
     * @param: courseCode: course code for the corresponding
     * @return: whether course is taught by the professor or not
     */
    private boolean checkCourseIsTaught(String profId , String courseCode){

        Connection conn = DBConnector.getInstance();

        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.getProfWithCourse);
            stmt.setString(1 , profId);
            stmt.setString(2 , courseCode);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return true;

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    private boolean checkIfStudentRegisteredForCourse(String userId , String courseCode){

        Connection conn = DBConnector.getInstance();

        try{
            PreparedStatement stmt = conn.prepareStatement(SQLQueriesConstant.IS_COURSE_REGISTERED);
            stmt.setString(1 , userId);
            stmt.setString(2 , courseCode);
            ResultSet rs = stmt.executeQuery();
            if(rs.next() && rs.getInt("cnt") > 0) {
                return true;
            }
        }catch (SQLException ex){
            logger.error(ex.getMessage());
        }
        return false;
    }

    public static void main(String[] args) throws GradesAlreadyGivenException , StudentNotFoundException {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        operation.addGrades("LIT2017022" , "CS009" , 10);
    }
}
