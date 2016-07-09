/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.archibaldsquest;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ArchibaldsQuest {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        int nextroom = 1;
        String choice;

        System.out.println("~~~Archibald's Quest~~~");
        System.out.println("");
        System.out.println("Good morning, Archibald.");
        System.out.println("Ye hath found yeself in an abandoned pub.");
        System.out.println("The door hath been mysteriously shut by magical forces.");
        while (nextroom != 0) {
            if (nextroom == 1) {
                System.out.println("Empty beersteins litter the pubbery. Mice are fighting for scraps of bread.");
                System.out.println("Ye see ye \"keg\" in the corner. Ye pub extends to a \"room\" to the left.");
                System.out.println("");
                System.out.println("What shall ye do?");
                System.out.print(">");
                choice = keyboard.nextLine();
                System.out.println("");
                switch (choice) {
                    case "keg":
                        nextroom = 2;
                        break;
                    case "room":
                        nextroom = 4;
                        break;
                    default:
                        System.out.println(choice + " doth a confusing command... Repeat thyself.");
                        System.out.println("");
                        break;
                }
            }
            if (nextroom == 2) {
                System.out.println("Thou hath approached the wooden keg.");
                System.out.println("The keg appears to be empty, but large enough to \"enter\".");
                System.out.println("");
                System.out.println("Woulds't thou \"enter\" the keg or \"go back\"?");
                System.out.print(">");
                choice = keyboard.nextLine();
                System.out.println("");
                if (choice.equalsIgnoreCase("go back")) {
                    nextroom = 1;
                } else if (choice.equalsIgnoreCase("enter")) {
                    nextroom = 3;
                } else {
                    System.out.println(choice + " doth a confusing command... Repeat thyself.");
                    System.out.println("");
                }
            }
            if (nextroom == 3) {
                System.out.println("Ye enter ye keg.");
                System.out.println("Ye feel cozy.");
                System.out.println("");
                System.out.println("Woulds't thou \"stay\" in ye keg or \"leave\"?");
                System.out.print(">");
                choice = keyboard.nextLine();
                System.out.println("");
                switch (choice) {
                    case "leave":
                        nextroom = 2;
                        break;
                    case "stay":
                        System.out.println("Thou hath decided to remain in ye keg.");
                        System.out.println("It is dark in ye keg.");
                        System.out.println("Ye hear faint sounds of thunder in the distance as raindrops\n"
                                + "pitter upon the thatched roof of ye pub.");
                        System.out.println("Ye begin to feel drowsy and fall asleep in ye keg.");
                        System.out.println("Ye remain cozy in ye keg for the rest of ye days.");
                        System.out.println("");
                        System.out.println("Thus is a happy end for Archibald.");
                        nextroom = 0;
                        break;
                    default:
                        System.out.println(choice + " doth a confusing command... Repeat thyself.");
                        System.out.println("");
                        break;
                }
            }
            if (nextroom == 4) {
                System.out.println("Thou hath found yeself in a dining hall.");
                System.out.println("Ye wonder why ye rats in ye room before hath not\n"
                        + " gone for ye cheese on don table ahead.");
                System.out.println("Ye see ye wooden box under the table.");
                System.out.println("Ye set of stairs ascend to another room above.");
                System.out.println("");
                System.out.println("Dost thou \"open ye box\", \"ascend\" ye stairs, or \"go back\"?");
                System.out.print(">");
                choice = keyboard.nextLine();
                System.out.println("");
                if (choice.equalsIgnoreCase("go back")) {
                    nextroom = 1;
                } else if (choice.equalsIgnoreCase("open ye box")) {
                    System.out.println("Ye crouch under ye table.");
                    System.out.println("Ye box hath a clasp that hath been broken.");
                    System.out.println("Ye open ye box.");
                    System.out.println("...");
                    System.out.println("Ye box was a mimic monster!");
                    System.out.println("Arms burst through ye sides of ye box, grabbing thou\n"
                            + "as it reveals its teeth!");
                    System.out.println("Ye mimic monster slowly brings ye into it's mouth.");
                    System.out.println("Thus is a sad end for ye Archibald...");
                    nextroom = 0;
                } else if (choice.equalsIgnoreCase("ascend")) {
                    nextroom = 5;
                } else {
                    System.out.println(choice + " doth a confusing command... Repeat thyself.");
                    System.out.println("");
                }
            }

        }

    }
}
