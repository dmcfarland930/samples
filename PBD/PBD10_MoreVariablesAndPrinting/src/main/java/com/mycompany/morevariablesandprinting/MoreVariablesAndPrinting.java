/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.morevariablesandprinting;

/**
 *
 * @author apprentice
 */
public class MoreVariablesAndPrinting {
    
    public static void main(String[] args){
        
        //Declare String and integer Variables
        String name, eyes, teeth, hair;
        int age;
        float height, weight;
        
        
        //Give values to Strings and integers
        name = "Zed A. Shaw";
        age = 35; //not a lie
        height = Math.round(74 * 2.54f); //cm
        weight = Math.round(180 / 2.2f); //kg
        eyes = "Blue";
        teeth = "White";
        hair = "Brown";
        
        //Print out info about this Zed fella
        System.out.println("Let's talk about "+ name);
        System.out.println("He's "+ height + " centimeters tall.");
        System.out.println("He's "+ weight + " kilograms heavy.");
        System.out.println("Actually that's not too heavy.");
        System.out.println("He's got " + eyes + " eyes and " + hair + 
                " hair.");
        System.out.println("His teeth are usually " + teeth + " depending "
                + "on the coffee.");
        
        //the tricky line
        System.out.println("If I add " + age + ", " + height + ", and " 
                + weight + " I get " + (age + height + weight) + ".");
        
        
             
    }
    
}
