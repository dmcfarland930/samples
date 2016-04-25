/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hilolimited;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class HiLoLimited {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();
        int guess;
        int count = 0;
        int random = 1 + (generator.nextInt(100));

        boolean loopBreak = false;

        System.out.println("I'm thinking of a number between 1-100. Can you guess it in 7 tries?");

        while (!loopBreak && count < 7) {
            System.out.print("Your guess: ");
            guess = keyboard.nextInt();
            if (guess < random) {
                System.out.println("Too low!");
            } else if (guess > random) {
                System.out.println("Too high!");
            } else {
                loopBreak = true;
                System.out.println("You got it! I was thinking of " + random + "! Are you a wizard?");
            }
            count++;
        }

        if (!loopBreak) {
            System.out.println("You didn't guess in 7 tries. You lose!");
        }

    }
}
