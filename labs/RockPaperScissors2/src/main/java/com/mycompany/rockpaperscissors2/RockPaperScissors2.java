/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.rockpaperscissors2;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Daniel McFarland
 */
public class RockPaperScissors2 {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Random generate = new Random();

        int rounds = 0;
        int computerP;

        String humanP;

        //Welcome message and round validity check
        System.out.println("<><><><>Welcome to the Thunderdome!<><><><>");

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
        for (int count = 0; count != rounds; count++) {

            System.out.println("--------------------");//Decorative line
            System.out.println("Round: " + (count + 1) + "/" + rounds);
            System.out.println("Pick a weapon.");
            System.out.println("    1) ROCK\n    2) PAPER\n    3) SCISSORS\n    4) [quit]");
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
                                    break;
                                case 2:
                                    computerPlay(2, humanP, "My PAPER smothered\nyour " + humanP + " into"
                                            + " submission!", 2);
                                    break;
                                default:
                                    computerPlay(3, humanP, "Your " + humanP + " smashed my \nSCISSORS with it's\n"
                                            + "unrivaled strength!", 1);
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
                                    break;
                                case 2:
                                    computerPlay(2, humanP, "Our PAPERS folded themselves\ninto origami cranes "
                                            + "and\nflew away together!", 3);
                                    break;
                                default:
                                    computerPlay(3, humanP, "My SCISSORS made\nribbons of your "
                                            + humanP + "!", 2);
                                    break;
                            }//end human chose paper
                            break;

                        default:
                            humanP = "SCISSORS";
                            switch (computerP) {//human chose scissors
                                case 1:
                                    computerPlay(1, humanP, "My ROCK countered\nyour " + humanP + " with\n"
                                            + "a devastating blow!", 2);
                                    break;
                                case 2:
                                    computerPlay(2, humanP, "Your  " + humanP + " teleported\nbehind my PAPER "
                                            + "and cut it\ninto a million pieces!", 1);
                                    break;
                                default:
                                    computerPlay(3, humanP, "SCISSOR ME TIMBERS!", 3);
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
}//end class
