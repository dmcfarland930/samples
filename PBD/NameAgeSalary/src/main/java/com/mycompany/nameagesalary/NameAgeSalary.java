/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nameagesalary;

import java.util.Scanner;
import java.text.*;
/**
 *
 * @author apprentice
 */
public class NameAgeSalary {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
        
        String name = "";
        int age = 0;
        double wage = 0;
        
        System.out.print("What is your name? ");
        name = keyboard.nextLine();
        System.out.println("");
        
        System.out.print("Hi "+name+"! How old are you? ");
        age = keyboard.nextInt();
        System.out.println("");
        
        System.out.print("So you're " +age+"? How much do you make?" );
        wage = keyboard.nextDouble();
        System.out.println("");
        
        System.out.println("You make $"+df.format(wage)+"?? Can you buy me "
                + "lunch?");
        
        
        
        
        
        
        
    }
    
}
