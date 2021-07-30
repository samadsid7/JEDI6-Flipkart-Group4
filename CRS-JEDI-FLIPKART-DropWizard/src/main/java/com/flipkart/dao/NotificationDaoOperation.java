package com.flipkart.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author JEDI-6 Group 4
 * Class to implement Notification Dao Operations
 * Used for adding the notification to the database
 *
 */


public class NotificationDaoOperation implements NotificationDaoInterface{
    private final Connection conn;
    String Uid;
    private static Logger logger = Logger.getLogger(NotificationDaoOperation.class);
    public NotificationDaoOperation(){
        conn = DBConnector.getInstance();
    }


    /**
     * Send Notification using SQL commands
     * @param studentId: student to be notified
     * @param mode: mode of payment used
     * @throws SQLException
     */

    @Override
    public String sendNotification(String studentId, String mode)
    {
        final String sql = "INSERT INTO notification values (? , ? , ? , ? , ?)";
        try {
            UUID uniqueKey = UUID.randomUUID();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , studentId);
            stmt.setString(2 , mode);
            stmt.setString(3,"payment succesful");
            stmt.setInt(4,0);
            String uid=uniqueKey.toString();
            stmt.setString(5,uid);
            stmt.executeUpdate();
            Uid=uid;
            logger.info("******* Payment Succesfully completed. ******");
            logger.info("****** Transaction Id is "+uid+" ********");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Uid;
    }
}
