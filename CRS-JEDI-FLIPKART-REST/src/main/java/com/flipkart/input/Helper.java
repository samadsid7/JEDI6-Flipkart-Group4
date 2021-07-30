package com.flipkart.input;

import java.util.Scanner;

public class Helper {

    public static Integer scanInt(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Select an Option : ");
        String s = scan.next();
        try{
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            System.out.println("Number not valid");
        }
        return null;
    }

    public static Integer scanInt(String message){

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter " + message + " : ");
        String s = scan.next();
        try{
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            System.out.println("Number not valid");
            return scanInt(message);
        }
    }

    public static String scanString(String message){
        Scanner scan = new Scanner(System.in);

        System.out.print(String.format("Enter %s : " , message));

        String value = scan.nextLine();
        if(value.length()==0){
            System.out.print(String.format("%s can't be empty" , message));
            return scanString(message);
        }
        return value;
    }
}
