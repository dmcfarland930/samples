/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bmicalc;

import java.util.Scanner;
import java.text.*;
/**
 *
 * @author apprentice
 */
public class BMICalc {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
        
        double height, weight, bmi;
        
        double feet, inches;
        
        System.out.print("Your height in m: ");
        height = keyboard.nextDouble();
        
        System.out.print("Your weight in kg: ");
        weight = keyboard.nextDouble();
        
        bmi = weight / height;
        
        System.out.println("Your BMI is " +df.format(bmi));
        System.out.println("");
        
        height = height * 39.37;
        weight = weight * 2.2;
        
        
        System.out.println("Your height in inches:"+df.format(height));
        System.out.println("Your weight in pounds:"+df.format(weight));
        
        
    }
    
}
