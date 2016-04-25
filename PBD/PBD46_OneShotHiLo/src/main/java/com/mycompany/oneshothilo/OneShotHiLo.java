/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.oneshothilo;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class OneShotHiLo {

    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();
        int guess;
        int random = 1 + (generator.nextInt(100));

        System.out.println("I'm thinking of a number between 1-100. Can you guess it?");
        System.out.print("Your guess: ");
        guess = keyboard.nextInt();
        if (guess < random) {
            System.out.println("Too low! I was thinking of " + random);
        } else if(guess > random){
            System.out.println("Too high! I was thinking of " + random);
        }else if(guess == random){
            System.out.println("You got it! I was thinking of " + random + "! Are you a wizard?");
        }else{
            System.out.println("That number is out of this world! I wouldn't even imagine that.");
        }
    }
}
