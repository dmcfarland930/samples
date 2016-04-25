/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.safesquareroot;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class SafeSquareRoot {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
       int userInput;
        
        System.out.println("SQUARE ROOT!");
        System.out.print("Enter a number: ");
        userInput = keyboard.nextInt();
        while (userInput<0){
            System.out.println("You can't get a square root of a negative number.");
            System.out.print("Try again: ");
            userInput = keyboard.nextInt();
        }
        
        double sqRt = Math.sqrt(userInput);
        System.out.println("The square root of "+userInput+" is "+sqRt);
    }
}
