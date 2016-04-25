/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.factorizer;

import java.util.Scanner;

/**
 *
 * @author Daniel McFarland
 */
public class Factorizer {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        int inputNum = -1;
        int perfect = 0;
        int prime = 0;

        //ask user for input
        System.out.println("Welcome to the Factorizer app! We're gonna have some fun!");
        System.out.println("");

        //check for valid input
        inputNum = zeroCheck(inputNum);

        //do a modulus test
        for (int i = 1; i < inputNum; i++) {
            if (inputNum % i == 0) {
                System.out.println(i);
                System.out.println("");
                prime++;
                perfect = perfect + i;
            }
        }//end factorizer loop
        
        if (perfect == inputNum) {
            System.out.println(inputNum + " is a perfect number.");
            System.out.println("");
        } else {
            System.out.println(inputNum + " is not a perfect number.");
            System.out.println("");
        }

        if (prime == 1) {
            System.out.println(inputNum + " is a prime number.");
            System.out.println("");
        } else {
            System.out.println(inputNum + " is not a prime number.");
            System.out.println("");
        }

    }//end main
    public static int zeroCheck(int query){
        Scanner keyboard = new Scanner(System.in);
        while (query< 0) {
            System.out.print("Please enter a number that you want Factorized: ");
            query = keyboard.nextInt();
            if (query< 0) {
                System.out.println("");
                System.out.println("Your number must be a positive number!");
                System.out.println("");
            } else {
                System.out.println("");
            }//end validity check
    }
        return query;
    }
}//end class
