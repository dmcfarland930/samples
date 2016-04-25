/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.rockpaperscissors;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Daniel McFarland
 */
public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Random generate = new Random();

        int computerP;

        String humanP;

        //Welcome message and round validity check
        System.out.println("<><><><>Welcome to the Thunderdome!<><><><>");

        System.out.println("--------------------");//Decorative line
        System.out.println("Pick a weapon.");
        System.out.println("    1) ROCK\n    2) PAPER\n    3) SCISSORS");

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
                                break;
                            case 2:
                                System.out.println("I chose PAPER!");
                                System.out.println("");
                                System.out.println("My PAPER smothered\nyour " + humanP + " into"
                                        + " submission!");
                                System.out.println("");
                                System.out.println("You lose!");
                                break;
                            default:
                                System.out.println("I chose SCISSORS!");
                                System.out.println("");
                                System.out.println("Your " + humanP + " smashed my \nScissors with it's\n"
                                        + "unrivaled strength!");
                                System.out.println("");
                                System.out.println("You win!");
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
                                break;
                            case 2:
                                System.out.println("I chose PAPER!");
                                System.out.println("");
                                System.out.println("Our PAPERS folded themselves\ninto origami cranes "
                                        + "and\nflew away together!");
                                System.out.println("");
                                System.out.println("It's a tie!");
                                break;
                            default:
                                System.out.println("I chose SCISSORS!");
                                System.out.println("");
                                System.out.println("My SCISSORS made\nribbons of your " + humanP + "!");
                                System.out.println("");
                                System.out.println("You lose!");
                                break;
                        }//end human chose paper
                        break;

                    default:
                        humanP = "SCISSORS";
                        System.out.println("You chose " + humanP);
                        switch (computerP) {//human chose scissors
                            case 1:
                                System.out.println("I chose ROCK!");
                                System.out.println("");
                                System.out.println("My ROCK countered\nyour " + humanP + " with\n"
                                        + "a devastating blow!");
                                System.out.println("");
                                System.out.println("You lose!");
                                break;
                            case 2:
                                System.out.println("I chose PAPER!");
                                System.out.println("");
                                System.out.println("Your  " + humanP + " teleported\nbehind my PAPER and "
                                        + "cut it\ninto a million pieces!");
                                System.out.println("");
                                System.out.println("You win!");
                                break;
                            default:
                                System.out.println("");
                                System.out.println("SCISSOR ME TIMBERS!");
                                System.out.println("");
                                System.out.println("It's a tie!");
                                break;
                        }//end human chose scissors
                        break;

                }//end switch for "weapon choice"
                break;

            case "QUIT":
            case "Q":
            case "4":
                System.out.println("Too tired to go on huh?");
                break;

            default:
                System.out.println("");
                System.out.println("Invalid entry! Try again!");
                System.out.println("");
                break;

        }
    }//end of main
}//end of class
