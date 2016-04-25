/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.primefinder;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class PrimeFinder {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        int number = -1;
        int factorCount;
        boolean go = true;
        String confirm;
        System.out.println("This program will show you the prime numbers withing a number of "
                + "your choice");
        while (go == true) {
            number = zeroCheck(number);

            System.out.println("List of prime numbers:");
            for (int x = 0; x <= number; x++) {
                factorCount = 0;
                for (int i = 1; i <= x; i++) {
                    if (x % i == 0) {
                        factorCount++;
                    }
                }
                if (factorCount == 2) {
                    System.out.println(x);

                }

            }
            System.out.println("Would you like to play again?");
            confirm = keyboard.next();
            if (confirm.equals("yes")){
                go = true;
            }else if (confirm.equals("no")){
                go = false;
            }
        }
        System.out.println("Thanks for playing!");
    }

    public static int zeroCheck(int query) {
        Scanner keyboard = new Scanner(System.in);
        while (query < 0) {
            System.out.print("Enter a number: ");
            query = keyboard.nextInt();
            if (query < 0) {
                System.out.println("");
                System.out.println("Your number must be a positive number!");
                System.out.println("");
            }//end validity check
        }
        return query;
    }
}
