/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.luckysevens;

import java.text.*;
import java.util.Random;

/**
 *
 * @author Daniel McFarland
 */
public class LuckySevens {

    DecimalFormat moneyFormat = new DecimalFormat("0.00");
    ConsoleIO console = new ConsoleIO(this);
    double bet = 0;
    int rolls = 0;
    double highDollar = 0;
    double highRoll = 0;
    double winnings = 0;

    public void run() {

        System.out.println("Welcome to Lucky Sevens: The game of futility!");

        //ask user for their bet
        bet = console.zeroCheckDouble("How much money you like to put down? $",
                "Come on man, put something more than nothing!", "Please enter a number!");

        //create a new double 'winnings' to be manipulated
        winnings = bet;

        loopRolls();

        //show results
        displayResults();

    }

    public int diceRollandAdd() {

        Random diceRoll = new Random();
        int dice1 = diceRoll.nextInt(6) + 1;
        int dice2 = diceRoll.nextInt(6) + 1;
        int diceTotal = dice1 + dice2;

        return diceTotal;
    }

    public double manipulateWinnings(int diceTotal) {

        if (diceTotal == 7) {
            this.winnings += 4;
        } else {
            this.winnings -= 1;
        }//end of money change
        return this.winnings;
    }

    public void showRollWinnings() {
        System.out.println("Roll " + (this.rolls + 1)
                + " Winnings: $" + moneyFormat.format(this.winnings)+"\n");
    }

    public void updateHighScores() {

        if (this.winnings >= this.highDollar) {
            this.highDollar = this.winnings;
            this.highRoll = this.rolls;

        }

    }

    public void loopRolls() {

        highDollar = this.winnings;

        for (this.rolls = 0; this.winnings > 0; this.rolls++) {

            int diceTotal = diceRollandAdd();

            this.winnings = manipulateWinnings(diceTotal);
            
            showRollWinnings();
            
            updateHighScores();

            //track the high score
        }//end for loop
    }

    public void displayResults() {

        System.out.println("You went broke after " + this.rolls + " rolls.");
        if (bet == this.highDollar) {
            System.out.println("You never earned more than your inital bet of $" + moneyFormat.format(bet));
            System.out.println("That should teach you to never gamble.");
        } else {
            System.out.println("After " + this.highRoll + " rolls, you had $"
                    + moneyFormat.format(this.highDollar) + "."
                    + " You should have quit there, but this is Lucky Sevens!\n"
                    + "You'll never win! Thus, a metaphor for life...");
        }

    }

}//end of class
