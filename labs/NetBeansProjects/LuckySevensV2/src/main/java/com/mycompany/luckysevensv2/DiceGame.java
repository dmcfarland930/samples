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
    ConsoleIO console = new ConsoleIO();

    public DiceGame(double userBetInput) {

        console.readString("Welcome to Lucky Sevens: The game of futility!");

    }

    public double zeroCheck(double query, String message1, String message2) {

        Scanner keyboard = new Scanner(System.in);

        while (query <= 0) {

            System.out.print(message1);
            query = keyboard.nextDouble();
            if (query <= 0) {
                console.readString("\n" + message2 + "\n");
            } else {
                console.readString("");
            }//end validity check
        }//end validity loop
        return query;

    }
}
