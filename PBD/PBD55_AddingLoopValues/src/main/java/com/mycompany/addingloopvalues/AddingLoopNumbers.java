/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.addingloopvalues;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class AddingLoopNumbers {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        
        int userNum = 1;
        int currentNum = 0;
        
        System.out.println("I will add up the numbers you give me.");
        while (userNum !=0){
            
            System.out.print("Enter a number: ");
            userNum = keyboard.nextInt();
            currentNum = currentNum + userNum;          
            
            if(userNum!=0){
                System.out.println("The total so far is "+currentNum);
            }
        }
        
        System.out.println("The total is "+currentNum);
    }
    
}
