/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.luckysevens;

import java.util.Scanner;
import java.text.*;
import java.util.Random;

/**
 *
 * @author Daniel McFarland
 */
public class LuckySevens {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        double bet = 0;
        double winnings = 0;
        double highScore = 0;

        int diceTotal, rolls;
        int highRoll = 0;

        System.out.println("Welcome to Lucky Sevens: The game of futility!");

        //ask user for their bet
        System.out.println("");
        while (bet <= 0) {
            System.out.print("How much money you like to put down? $");
            bet = keyboard.nextDouble();
            if (bet <= 0) {
                System.out.println("");
                System.out.println("Come on man, put something more than nothing!");
                System.out.println("");
            } else {
                winnings = bet;
                highScore = winnings;
            }
        }//end entry validation

        //loop until money runs out
        for (rolls = 1; winnings > 0; rolls++) {
            
            diceTotal = diceRollandAdd();
            
            if (diceTotal == 7) {
                winnings += 4;
            } else {
                winnings -= 1;
            }//end of money change

            //track the high score
            if (winnings >= highScore) {

                highScore = winnings;
                highRoll = rolls;

            }//end score tracker
        }//end for loop

        //show results
        System.out.println("");
        System.out.println("You went broke after " + rolls + " rolls.");
        if (bet == highScore) {
            System.out.println("You never earned more than your inital bet of $" + df.format(bet));
            System.out.println("That should teach you to never gamble.");
        } else {
            System.out.println("After " + highRoll + " rolls, you had $" + df.format(highScore) + "."
                    + " You should have quit there, but this is Lucky Sevens!\n"
                    + "You'll never win! Thus, a metaphor for life...");
        }//end of results

    }//end of main

    public static int diceRollandAdd() {

        Random diceRoll = new Random();
        int dice1 = diceRoll.nextInt(6) + 1;
        int dice2 = diceRoll.nextInt(6) + 1;
        int diceTotal = dice1 + dice2;
        
        return diceTotal;
    }
}//end of class
