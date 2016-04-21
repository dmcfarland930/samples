/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spaceboxing;

import java.util.Scanner;
import java.text.*;

/**
 *
 * @author apprentice
 */
public class SpaceBoxing {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
        
        int planet;
        double earthWt;
        double weight = 0;        
        boolean valid = false;
        
        System.out.print("Please enter your earth weight: ");
            earthWt = keyboard.nextDouble();
        
        //run loop until user enters a valid planet number    
        while (!valid){    
        System.out.println("");
        System.out.println("I have information on the following planets:");
        
        System.out.println("1. Venus   2. Mars    3. Jupiter");
        System.out.println("4. Saturn  5. Uranus  6. Neptune");
            
        System.out.println("");
        System.out.print("Which planet are you visiting? ");
            planet = keyboard.nextInt();
            
            
            switch(planet){
                
                case 1:
                    weight = earthWt * 0.78;
                    valid = true;
                    break;
                    
                case 2:
                    weight = earthWt * 0.39;
                    valid = true;
                    break;
                
                case 3:
                    weight = earthWt * 2.65;
                    valid = true;
                    break;
                
                case 4:
                    weight = earthWt * 1.17;
                    valid = true;
                    break;
                
                case 5:
                    weight = earthWt * 1.05;
                    valid = true;
                    break;    
                
                case 6:
                    weight = earthWt * 1.23;
                    valid = true;
                    break;
                    
                default:
                    System.out.println("");
                    System.out.println("That planet does not exist. ");
                    System.out.println("Please enter a valid planet "
                            + "number. ");
                    
            }
        }    
            
        System.out.println("");
        System.out.println("Your weight would be "+df.format(weight)+ 
                " pounds on that planet.");
    }
    
}
