/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.numberguessingtries;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class NumberGuessingTries {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();
        int guess;
        int random = 1 + (generator.nextInt(10));
        int count=0;
        boolean correct = false;

        System.out.println("I'm thinking of a number between 1-10. Can you guess it?");
        while (!correct) {
            System.out.print("Your guess: ");
            guess = keyboard.nextInt();
            if (guess == random) {
                correct = true;
            } else if (guess > 10 || guess < 1) {
                System.out.println("That's number does not fit the range!");
            } else {
                System.out.println("That's wrong! Guess again!");
                count++;
            }
        }
        System.out.println("You guessed it! I was thinking of " + random + "!");
        System.out.println("It took you " +(count+1)+" tries!");
    }
}
