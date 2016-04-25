/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.rockpaperscissors4;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Daniel McFarland
 */
public class RockPaperScissors4 {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Random generate = new Random();

        int rounds = 0;
        int wins = 0;
        int losses = 0;
        int ties = 0;
        int computerP;

        boolean gameOver = false;

        String humanP = "";
        String confirm;

        //Welcome message and round validity check
        System.out.println("<><><><>Welcome to the Thunderdome!<><><><>");

        while (!gameOver) {
            while (rounds < 1 || rounds > 10) {
                System.out.println("How many rounds would you like to play?"
                        + "\nYou can play up to ten.");
                System.out.print(">");
                rounds = keyboard.nextInt();

                if (rounds < 1 || rounds > 10) {
                    System.out.println("");
                    System.out.println("Invalid entry! Try again!");
                    System.out.println("");
                }

                //Start of rounds    
            }//resume game
            for (int count = 0; count!=rounds; count++) {

                System.out.println("--------------------");//Decorative line
                System.out.println("Round: " + (count + 1) + "/" + rounds);
                System.out.println("Pick a weapon.");
                System.out.println("    1) ROCK\n    2) PAPER\n    3) SCISSORS"
                        + "\n    4) [quit]");
                //user input
                System.out.print(">");
                humanP = keyboard.next();
                humanP = humanP.toUpperCase();
                //check validity of choice

                computerP = generate.nextInt(3) + 1;

                switch (humanP) {//if not a tie, resume game and check user vs computer
                    case "ROCK":
                    case "1":
                    case "PAPER":
                    case "2":
                    case "SCISSORS":
                    case "3":
                        System.out.println("<><><><><><><><><><>");
                        System.out.println("");
                        switch (humanP) {//switch if human chose rock, paper, or scissors
                            case "ROCK":
                            case "1":
                                humanP = "ROCK";
                                System.out.println("You chose " + humanP + "!");
                                switch (computerP) {
                                    case 1:
                                        System.out.println("I chose ROCK!");
                                        System.out.println("");
                                        System.out.println("Our ROCKs fought each\nother until they were\ntoo"
                                                + " exhausted to\ncontinue...");
                                        System.out.println("");
                                        System.out.println("It was a tie...");
                                        ties++;
                                        break;
                                    case 2:
                                        System.out.println("I chose PAPER!");
                                        System.out.println("");
                                        System.out.println("My PAPER smothered\nyour " + humanP + " into"
                                                + " submission!");
                                        System.out.println("");
                                        System.out.println("You lose!");
                                        losses++;
                                        break;
                                    default:
                                        System.out.println("I chose SCISSORS!");
                                        System.out.println("");
                                        System.out.println("Your " + humanP + " smashed my \nScissors with it's\n"
                                                + "unrivaled strength!");
                                        System.out.println("");
                                        System.out.println("You win!");
                                        wins++;
                                        break;
                                }//end human chose rock
                                break;

                            case "PAPER":
                            case "2":
                                humanP = "PAPER";
                                System.out.println("You chose " + humanP + "!");
                                switch (computerP) {
                                    case 1:
                                        System.out.println("I chose ROCK!");
                                        System.out.println("");
                                        System.out.println("Your " + humanP
                                                + " covered my\nRock and made him too\ncozy to fight!");
                                        System.out.println("");
                                        System.out.println("You win!");
                                        wins++;
                                        break;
                                    case 2:
                                        System.out.println("I chose PAPER!");
                                        System.out.println("");
                                        System.out.println("Our PAPERS folded themselves\ninto origami cranes "
                                                + "and\nflew away together!");
                                        System.out.println("");
                                        System.out.println("It's a tie!");
                                        ties++;
                                        break;
                                    default:
                                        System.out.println("I chose SCISSORS!");
                                        System.out.println("");
                                        System.out.println("My SCISSORS made\nribbons of your " + humanP + "!");
                                        System.out.println("");
                                        System.out.println("You lose!");
                                        losses++;
                                        break;
                                }//end human chose paper
                                break;

                            default:
                                humanP = "SCISSORS";
                                System.out.println("You chose " + humanP + "!");
                                switch (computerP) {//human chose scissors
                                    case 1:
                                        System.out.println("I chose ROCK!");
                                        System.out.println("");
                                        System.out.println("My ROCK countered\nyour " + humanP + " with\n"
                                                + "a devastating blow!");
                                        System.out.println("");
                                        System.out.println("You lose!");
                                        losses++;
                                        break;
                                    case 2:
                                        System.out.println("I chose PAPER!");
                                        System.out.println("");
                                        System.out.println("Your  " + humanP + " teleported\nbehind my PAPER and "
                                                + "cut it\ninto a million pieces!");
                                        System.out.println("");
                                        System.out.println("You win!");
                                        wins++;
                                        break;
                                    default:
                                        System.out.println("");
                                        System.out.println("SCISSOR ME TIMBERS!");
                                        System.out.println("");
                                        System.out.println("It's a tie!");
                                        ties++;
                                        break;
                                }//end human chose scissors
                                break;

                        }//end switch for "weapon choice"
                        break;

                    case "QUIT":
                    case "Q":
                    case "4":
                        System.out.println("");
                        System.out.println("Too tired to go on huh?");
                        humanP = "q";
                        count = rounds;
                        count--;
                        break;

                    default:
                        System.out.println("");
                        System.out.println("Invalid entry! Try again!");
                        System.out.println("");
                        count--;//do not allow counter to change with invalid entry
                        break;

                }//end gameplay loop
            }//loop containing game
            System.out.println("");
            System.out.println("<><>FINAL RESULTS<><>");
            System.out.println("  Wins: " + wins);
            System.out.println("Losses: " + losses);
            System.out.println("  Ties: " + ties);
            System.out.println("");

            if (wins > losses) {
                if (humanP.equalsIgnoreCase("q")) {
                    System.out.println("You beat me! Defeated by a human... I can't believe it!");
                } else {
                    System.out.println("Quitting while your ahead just means you are a coward.");
                }//end human player had more wins

            } else if (wins < losses) {
                if (humanP.equalsIgnoreCase("q")) {
                    System.out.println("You lost a lot... Couldn't handle the beating any longer?");
                } else {
                    System.out.println("You lost! Of course, you were never a match for my powerful"
                            + " computer brain.");
                }//end computer player had more wins

            } else if (wins == losses) {
                System.out.println("The battle ends in a draw. Too much blood was spilt"
                        + " all for nought.");
            }//end human and computer were tied

            //end of results
            System.out.println("");
            confirm = "";

            while (!(confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y"))) {
                rounds = 0;
                System.out.println("Would you like to play again? [yes/no] ");
                System.out.print(">");
                confirm = keyboard.next();
                System.out.println("");

                switch (confirm.toLowerCase()) {
                    case "yes":
                    case "y":
                        break;
                    case "no":
                        System.out.println("Thanks for playing!");
                        confirm = "y";
                        gameOver = true;
                        break;
                    default:
                        System.out.println("Not a valid entry!");
                        break;

                }//end of replay confirm
            }//end of play confirmation loop
        }//end of class
    }//end of main
}//end of class
