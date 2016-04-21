/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dumbcalculator;

import java.util.Scanner;
import java.text.*;
/**
 *
 * @author apprentice
 */
public class DumbCalculator {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
        
        double num1, num2, num3, finalNum;
        
        System.out.print("What is your first number?");
        num1 = keyboard.nextDouble();
        
        System.out.print("What is your second number?");
        num2 = keyboard.nextDouble();
        
        System.out.print("What is your third number?");
        num3 = keyboard.nextDouble();
        
        finalNum = (num1 + num2 + num3) / 2;
        
        System.out.println("( "+num1+ " + " +num2+ " +  " +num3+ " ) / "
            +"2 is... "+df.format(finalNum));
        
        
    }
}
