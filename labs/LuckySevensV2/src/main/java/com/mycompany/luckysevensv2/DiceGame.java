/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.luckysevensv2;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class DiceGame {

    double bet = 0;
    double winnings;
    double rolls = 0;

    public DiceGame(double userBetInput) {

        System.out.println("Welcome to Lucky Sevens: The game of futility!");

    }

    public static double zeroCheck(double query, String message1, String message2) {

        Scanner keyboard = new Scanner(System.in);

        while (query <= 0) {

            System.out.print(message1);
            query = keyboard.nextDouble();
            if (query <= 0) {
                System.out.println("\n" + message2 + "\n");
            } else {
                System.out.println("");
            }//end validity check
        }//end validity loop
        return query;

    }
