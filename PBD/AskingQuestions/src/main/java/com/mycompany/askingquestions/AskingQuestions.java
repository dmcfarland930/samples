/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.askingquestions;

import java.util.Scanner;

/**
 **
 * @author apprentice
 */
public class AskingQuestions {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int age;
        int feet;
        int inches;
        double weight;
        
        System.out.print("How old are you? ");
        age = sc.nextInt();
        
        System.out.print("How many feet tall are you? ");
        feet = sc.nextInt();
        
        System.out.print("How many inches tall? ");
        inches = sc.nextInt();
        
        System.out.print("How much do you weigh? ");
        weight = sc.nextDouble();
        
        System.out.println("So, you're "+age+" years old, " +feet+ "\'" +inches+
                "\" tall and "+weight+ " heavy.");
        
        
    }
       
    
}
