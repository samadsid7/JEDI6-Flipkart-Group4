package com.flipkart.dao;

public interface NotificationDaoInterface {


    /**
     * Send Notification using SQL commands
     * @param studentId: student to be notified
     * @param mode: mode of payment used
     */
    String sendNotification(String studentId,String mode);
}
