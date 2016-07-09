/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.collatzsequence;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class CollatzSequence {

    public static void main(String[] args) {

        int userInput;
        int count = 0;
        int highNum = 0;
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter an integer and watch the magic happen: ");
        userInput = keyboard.nextInt();

        while (userInput >= 1) {

            System.out.print(userInput + "    ");

            if (userInput == 1) {
                break;

            } else if (userInput % 2 == 0) {

                userInput = userInput / 2;

            } else {

                userInput = (3 * userInput) + 1;

            }
            count++;
            if (userInput > highNum) {
                highNum = userInput;
            }

        }
        System.out.println("");
        System.out.println("Terminated after " + count + " steps.");
        System.out.println("The highest number in this sequence was: " + highNum);
    }
}
