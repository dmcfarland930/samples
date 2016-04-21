/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.interactivequiz;

import java.util.Scanner;
/**
 *
 * @author apprentice
 */
public class InteractiveQuiz {
    
    public static void main(String[] args) {
    
        Scanner keyboard = new Scanner(System.in);
        int answer;
        int score = 0;
        
        boolean valid = false;
        
        //Welcome
        System.out.println("Welcome to the Avocado Fan Club Quiz!");
        System.out.println("~~~~~~~~~~We Love Avocados!~~~~~~~~~~");
        System.out.println("");
        
        //Question 1 answer is 3
        while (!valid){
        System.out.println("Q1) What type of food is the avocado?");
        System.out.println("            1) Meat");
        System.out.println("            2) Vegatable");
        System.out.println("            3) Fruit");
        System.out.println("");    
        answer = keyboard.nextInt();
        
        switch (answer){
            
            case 1:
                System.out.println("");
                System.out.println("Sorry! That's wrong...");
                System.out.println("");
                valid = true;
                break;
            case 2:
                System.out.println("");
                System.out.println("Sorry! That's wrong...");
                System.out.println("");
                valid = true;
                break;
            case 3:
                System.out.println("");
                System.out.println("That's correct!");
                System.out.println("");
                score++;
                valid = true;
                break;    
            default:
                System.out.println("");
                System.out.println("That is not a choice!");
                System.out.println("");
                System.out.println("Please choose a valid option.");
                System.out.println("");
        }      
        }
        
        //Question 2 answer is 1
        valid = false;
        while (!valid){
        System.out.println("Q2) What country is the world's greatest producer"
                + " of avocados?");
        System.out.println("            1) Mexico");
        System.out.println("            2) Australia");
        System.out.println("            3) Kenya");
        System.out.println("");    
        answer = keyboard.nextInt();
        
        switch (answer){
            
            case 1:
                System.out.println("");
                System.out.println("That's correct!");
                System.out.println("");
                score++;
                valid = true;
                break;
            case 2:
                System.out.println("");
                System.out.println("Sorry! That's wrong...");
                System.out.println("");
                valid = true;
                break;
            case 3:
                System.out.println("");
                System.out.println("Sorry! That's wrong...");
                System.out.println("");
                valid = true;
                break;    
            default:
                System.out.println("");
                System.out.println("That is not a choice!");
                System.out.println("");
                System.out.println("Please choose a valid option.");
                System.out.println("");
        }      
        }
        
        //Question 3 answer is 3
        valid = false;
        while (!valid){
        System.out.println("Q3) How far can I throw an avocado?");
        System.out.println("            1) 30 ft");
        System.out.println("            2) 65 ft");
        System.out.println("            3) Over them mountains");
        System.out.println("");    
        answer = keyboard.nextInt();
        
        switch (answer){
            
            case 1:
                System.out.println("");
                System.out.println("Sorry! That's wrong...");
                System.out.println("");
                valid = true;
                break;
            case 2:
                System.out.println("");
                System.out.println("Sorry! That's wrong...");
                System.out.println("");
                valid = true;
                break;
            case 3:
                System.out.println("");
                System.out.println("That's correct!");
                System.out.println("");
                score++;
                valid = true;
                break;    
            default:
                System.out.println("");
                System.out.println("That is not a choice!");
                System.out.println("");
                System.out.println("Please choose a valid option.");
                System.out.println("");
        }
        
        //results
            System.out.println("");
            System.out.println("You got " +score+ " out of 3 correct.");
            
        }
        
        //ending message
            switch(score){
                case 1:
                System.out.println("You need to eat more avocados.");    
                break;
                case 2:
                System.out.println("You are a avocado friend.");
                break;
                case 3:
                System.out.println("You are an avocado super fan!");
                break;
                default:                    
                System.out.println("You have no place in the Avocado"    
                        + " Fan Club.");
            }
            
    }
      
}
