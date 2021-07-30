package com.flipkart.session;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;


public class Session {

    private static Role role;
    private static String userId;
    private Session(){

    }
    public static void createSession(Role userRole , String username){
        role = userRole;
        userId = username;
    }
    public static Role getRole(){
        return role;
    }

    public static String getUserId(){
        return userId;
    }
}
