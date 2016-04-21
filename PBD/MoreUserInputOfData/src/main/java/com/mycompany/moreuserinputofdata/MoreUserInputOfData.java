/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.moreuserinputofdata;

import java.util.Scanner;
import java.text.*;
/**
 *
 * @author apprentice
 */
public class MoreUserInputOfData {
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
        
        String firstName, lastName, logIn;
        int grade, id;
        double gpa;
        
        System.out.println("Please enter the following information:");
        System.out.println("");
           
        System.out.print("First name: ");
        firstName = keyboard.next();
        
        System.out.print("Last name: ");
        lastName = keyboard.next();
        
        System.out.print("Grade (9-12): ");
        grade = keyboard.nextInt();
        
        System.out.print("Student ID: ");
        id =  keyboard.nextInt();
        
        System.out.print("Login: ");
        logIn = keyboard.next();
        
        System.out.print("GPA: ");
        gpa = keyboard.nextDouble();

        
        System.out.println("");
        System.out.println("Your Information:");
        System.out.println("        Login:"+logIn);
        System.out.println("        ID:"+id);
        System.out.println("        Name:"+lastName+" "+firstName);
        System.out.println("        GPA:"+df.format(gpa));
        System.out.println("        Grade:"+grade);
    }
    
}
