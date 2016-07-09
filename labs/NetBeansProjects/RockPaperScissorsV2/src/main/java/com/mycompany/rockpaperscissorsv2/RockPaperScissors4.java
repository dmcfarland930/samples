/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.rockpaperscissorsv2;

import java.util.Random;

/**
 *
 * @author Daniel McFarland
 */
public class RockPaperScissors4 implements Game{

    ConsoleIO console = new ConsoleIO();

    Random generate = new Random();

    String humanP;
    String computerObject;
    int computerP = 0;
    int wins = 0;
    int losses = 0;
    int ties = 0;
    int result = 0;
    int count = 0;
    int rounds = 0;
    boolean play = true;

    @Override
    public void run() {

        //Welcome message
        console.readString("<><><><>Welcome to the Thunderdome!<><><><>\n");

        //run until user decides to end the game
        while (play) {

            //ask user for desired rounds and check for validity. 
            rounds = console.checkMinMaxInt("How many rounds would you like "
                    + "to play?\nYou can play up to ten.\n>", 1, 10, "\nThat's not in the range of"
                    + " one to ten.\n",
                    "\nThat is not a number!\n", "\nThat's not in the range of"
                    + " one to ten.\n");

            //loop through gameplay
            loopGame();

            //display results of battle
            showResults();

            //restore game components
            resetGameComponents();

            //ask player to play again
            play = console.yesCheck("\nWould you like to play again? [yes/no]\n>", "Not a valid entry!");

        }//end of game loop

        //see ya
        console.readString("Thanks for playing!");

    }

    public void loopGame() {
        for (this.count = 0; this.count < this.rounds; this.count++) {

            //check validity of choice
            humanP = humanPlay();

            //generate choice for computer
            computerP = generate.nextInt(3) + 1;
            
            //create computer object
            computerObject = setComputerObject();

            //determine result of human vs computer match up
            checkPlayerChoice();
        }

    }

    public String humanPlay() {

        //user input
        humanP = console.getString("--------------------\n"
                + "Round: " + (this.count + 1) + "/" + this.rounds + "\n"
                + "Pick a weapon.\n    1) ROCK\n    2) PAPER\n    3) SCISSORS\n"
                + "       [quit]\n>");
        humanP = humanP.toUpperCase();

        return humanP;

    }

    public void checkPlayerChoice() {

        console.readString("<><><><><><><><><><>\n");
        //switch if human chose rock, paper, or scissors
        switch (this.humanP) {
            case "ROCK":
            case "PAPER":
            case "SCISSORS":
            case "1":
            case "2":
            case "3":
                setHumanObject();

            case "Q":
            case "QUIT":

                earlyQuit();
                break;

            default:
                checkInvalidPlay();
                break;

        }//end switch for "weapon choice"

    }

    public void setHumanObject() {

        if (this.humanP.equalsIgnoreCase("rock") || this.humanP.equalsIgnoreCase("1")) {

            this.humanP = "ROCK";
            compareToComputer();

        } else if (this.humanP.equalsIgnoreCase("paper") || this.humanP.equalsIgnoreCase("2")) {

            this.humanP = "PAPER";
            compareToComputer();

        } else if (this.humanP.equalsIgnoreCase("scissors") || this.humanP.equalsIgnoreCase("3")) {

            this.humanP = "SCISSORS";
            compareToComputer();

        }//end else if chain

    }

    public void compareToComputer() {

        switch (this.humanP) {
            case "ROCK":
            case "1":
                checkRockFight();
                break;
            case "PAPER":
            case "2":
                checkPaperFight();
                break;
            default:
                checkScissorsFight();
                break;
        }
    }

    public String setComputerObject() {
        switch (this.computerP) {
            case 1:
                computerObject = "ROCK";
                break;
            case 2:
                computerObject = "PAPER";
                break;
            default:
                computerObject = "SCISSORS";
                break;
        }

        return computerObject;
    }

    public void checkRockFight() {
        switch (this.computerP) {
            case 1:
                this.result = 3;
                determineResult("Our " + this.computerObject + "s fought each\nother until they were\ntoo"
                        + " exhausted to\ncontinue...");
                break;
            case 2:
                this.result = 2;
                determineResult("My " + this.computerObject + " smothered\nyour " + this.humanP + " into"
                        + " submission!");
                break;
            default:
                this.result = 1;
                determineResult("Your " + this.humanP + " smashed my \n" + this.computerObject + "S with it's\n"
                        + "unrivaled strength!");
                break;
        }
    }

    public void checkPaperFight() {
        switch (this.computerP) {
            case 1:
                this.result = 1;
                determineResult("Your " + this.humanP + " covered my\n" + this.computerObject + " and "
                        + "made him too\ncozy to fight!");
                break;
            case 2:
                this.result = 3;
                determineResult("Our " + this.computerObject + " folded themselves\ninto origami cranes "
                        + "and\nflew away together!");
                break;
            default:
                this.result = 2;
                determineResult("My " + this.computerObject + " made\nribbons of your "
                        + this.humanP + "!");
                break;
        }
    }

    public void checkScissorsFight() {

        switch (this.computerP) {
            case 1:
                this.result = 2;
                determineResult("My " + this.computerObject + " countered\nyour " + this.humanP + " with\n"
                        + "a devastating blow!");
                break;
            case 2:
                this.result = 1;
                determineResult("Your  " + this.humanP + " teleported\nbehind my " + this.computerObject + " "
                        + "and cut it\ninto a million pieces!");
                break;
            default:
                this.result = 3;
                determineResult("SCISSOR ME TIMBERS!");
                break;
        }
    }

    public void determineResult(String message) {
        console.readString("You chose " + this.humanP + "!");
        console.readString("I chose " + computerObject + "!");
        console.readString("\n" + message + "\n");

        switch (this.result) {
            case 1:
                console.readString("You win!");
                this.wins++;
                break;
            case 2:
                console.readString("You lose!");
                this.losses++;
                break;
            default:
                console.readString("It's a tie!");
                this.ties++;
                break;
        }
    }

    public void checkInvalidPlay() {

        switch (this.humanP) {
            case "ROCK":
            case "PAPER":
            case "SCISSORS":
            case "1":
            case "2":
            case "3":
                //valid choices
                break;

            default:
                console.readString("Invalid entry! Try again!");
                this.count--;//do not allow counter to change with invalid entry

        }
    }

    public void resetGameComponents() {

        this.wins = this.losses = this.ties = this.count = this.rounds = 0;
    }

    public void earlyQuit() {

        if (this.humanP.equalsIgnoreCase("Q") || this.humanP.equalsIgnoreCase("QUIT")) {
            console.readString("Too tired to go on huh?");
            this.count = this.rounds;
        }

    }

    public void showResults() {

        console.readString("\n<><>FINAL RESULTS<><>");
        console.readString("  Wins: " + this.wins);
        console.readString("Losses: " + this.losses);
        console.readString("  Ties: " + this.ties + "\n");
        if (this.wins > this.losses) {
            if (this.count == this.rounds) {
                console.readString("You beat me! Defeated by a human... I can't believe it!");
            } else {
                console.readString("Quitting while your ahead just means you are a coward.");
            }//end human player had more wins

        } else if (this.wins < this.losses) {
            if (this.count == this.rounds) {
                console.readString("You lost! Of course, you were never a match for my powerful"
                        + " computer brain.");
            } else {
                console.readString("You lost a lot... Couldn't handle the beating any longer?");
            }//end computer player had more wins

        } else if (this.wins == this.losses) {
            console.readString("The battle ends in a draw. Too much blood was spilt"
                    + " all for nought.");
        }
    }

    public String getName() {
        return "Rock, Paper, Scissors";
    }

}
