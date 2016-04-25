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
        String computerChoice;
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
                                switch (computerP) {
                                    case 1:
                                        computerPlay(1, humanP, "Our ROCKs fought each\nother until they were\ntoo"
                                                + " exhausted to\ncontinue...", 3);
                                        ties++;
                                        break;
                                    case 2:
                                        computerPlay(2, humanP, "My PAPER smothered\nyour " + humanP + " into"
                                                + " submission!", 2);
                                        losses++;
                                        break;
                                    default:
                                        computerPlay(3, humanP, "Your " + humanP + " smashed my \nSCISSORS with it's\n"
                                                + "unrivaled strength!", 1);
                                        wins++;
                                        break;
                                }//end human chose rock
                                break;

                            case "PAPER":
                            case "2":
                                humanP = "PAPER";
                                switch (computerP) {
                                    case 1:
                                        computerPlay(1, humanP, "Your " + humanP + " covered my\nROCK and "
                                                + "made him too\ncozy to fight!", 1);
                                        wins++;
                                        break;
                                    case 2:
                                        computerPlay(2, humanP, "Our PAPERS folded themselves\ninto origami cranes "
                                                + "and\nflew away together!", 3);
                                        ties++;
                                        break;
                                    default:
                                        computerPlay(3, humanP, "My SCISSORS made\nribbons of your " 
                                                + humanP + "!", 2);
                                        losses++;
                                        break;
                                }//end human chose paper
                                break;

                            default:
                                humanP = "SCISSORS";
                                switch (computerP) {//human chose scissors
                                    case 1:
                                        computerPlay(1, humanP, "My ROCK countered\nyour " + humanP + " with\n"
                                                + "a devastating blow!", 2);
                                        losses++;
                                        break;
                                    case 2:
                                        computerPlay(2, humanP, "Your  " + humanP + " teleported\nbehind my PAPER "
                                                + "and cut it\ninto a million pieces!", 1);
                                        wins++;
                                        break;
                                    default:
                                        computerPlay(3, humanP, "SCISSOR ME TIMBERS!", 3);
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

                }//end gameplay switch
            }//loop containing game
            System.out.println("");
            System.out.println("<><>FINAL RESULTS<><>");
            System.out.println("  Wins: " + wins);
            System.out.println("Losses: " + losses);
            System.out.println("  Ties: " + ties);
            System.out.println("");

            if (wins > losses) {
                if (!humanP.equalsIgnoreCase("q")) {
                    System.out.println("You beat me! Defeated by a human... I can't believe it!");
                } else {
                    System.out.println("Quitting while your ahead just means you are a coward.");
                }//end human player had more wins

            } else if (wins < losses) {
                if (!humanP.equalsIgnoreCase("q")) {
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
        }//end of game loop
    }//end of main
    public static String computerPlay(int computerChoice, String humanChoice, String message, int result){
        String object;
        switch (computerChoice) {
            case 1:
                object = "ROCK";
                break;
            case 2:
                object = "PAPER";
                break;
            default:
                object = "SCISSORS";
                break;
        }
        
        System.out.println("You chose " + humanChoice + "!");
        System.out.println("I chose "+object+"!");
        System.out.println("");
        System.out.println(message);
        System.out.println("");
        switch (result){
            case 1: 
                System.out.println("You win!");
                break;
            case 2:
                System.out.println("You lose!");
                break;
            default:
                System.out.println("It's a tie!");
                break;
        }
        return object;
    }//end method
}//end of class
