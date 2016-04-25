/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.threecardmonte;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ThreeCardMonte {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();

        int choice = 0;
        int slipsPick = 1 + (generator.nextInt(3));
        boolean valid = false;
        String card1 = "##";
        String card2 = "##";
        String card3 = "##";

        System.out.println("You walk into a smoke filled room.");
        System.out.println("");
        System.out.println("A man introduces himself as Slippery James and places three cards on the table.");
        System.out.println("Find the Ace, or Slippery James gets your kidney.");

        System.out.println("");

        while (!valid) {
            System.out.println("Which card has the ace?");
            System.out.println("");
            System.out.println("      " + card1 + "    " + card2 + "    " + card3);
            System.out.println("      " + card1 + "    " + card2 + "    " + card3);
            System.out.println("      1     2     3");
            System.out.println("");
            choice = keyboard.nextInt();
            System.out.println("");
            
            if (choice < 1 || choice > 3) {
                System.out.println("That's not an option!");
                System.out.println("Slippery James grows impatient...");
                System.out.println("");
            } else {
                valid = true;
            }
        }

        switch (slipsPick) {
            case 1:
                card1 = "AA";
                if (choice == slipsPick) {
                    System.out.println("You got! Not this time, Slippery James!");
                } else {
                    System.out.println("You missed! Slippery James gets your kidney!");
                }
                break;

            case 2:
                card2 = "AA";
                if (choice == slipsPick) {
                    System.out.println("You got! Not this time, Slippery James!");
                } else {
                    System.out.println("You missed! Slippery James gets your kidney!");
                }
                break;

            case 3:
                card3 = "AA";
                if (choice == slipsPick) {
                    System.out.println("You got! Not this time, Slippery James!");
                } else {
                    System.out.println("You missed! Slippery James gets your kidney!");
                }
                break;

            default:
                break;
        }
        System.out.println("");
        System.out.println("      " + card1 + "    " + card2 + "    " + card3);
        System.out.println("      " + card1 + "    " + card2 + "    " + card3);
        System.out.println("      1     2     3");
    }

}
