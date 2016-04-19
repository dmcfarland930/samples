/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
package com.mycompany.morevariablesandprinting;

/**
 *
 * @author apprentice
 */
public class MoreVariablesAndPrinting {
    
    public static void main(String[] args){
        
        //Declare String and integer Variables
        String myName, myEyes, myTeeth, myHair;
        int myAge, myHeight, myWeight;
        
        //Give values to Strings and integers
        myName = "Zed A. Shaw";
        myAge = 35; //not a lie
        myHeight = 74; //inches
        myWeight = 180; //lbs
        myEyes = "Blue";
        myTeeth = "White";
        myHair = "Brown";
        
        //Print out info about this Zed fella
        System.out.println("Let's talk about "+ myName);
        System.out.println("He's "+ myHeight + " inches tall.");
        System.out.println("He's "+ myWeight + " pounds heavy.");
        System.out.println("Actually that's not too heavy.");
        System.out.println("He's got " + myEyes + " eyes and " + myHair + 
                " hair.");
        System.out.println("His teeth are usually " + myTeeth + " depending"
                + "on the coffee.");
        
        //the tricky line
        System.out.println("If I add " + myAge + ", " + myHeight + ", and " 
                + myWeight + " I get " + (myAge + myHeight + myWeight) + ".");
             
    }
    
}
