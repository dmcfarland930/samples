/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.enteryourpin;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class EnterYourPIN {

    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        int pin = 2548;

        System.out.println("WELCOME TO THE BANK OF DAN");
        System.out.print("ENTER YOUR PIN: ");
        int entry = keyboard.nextInt();

        while (entry != pin) {
            System.out.println("\nINCORRECT PIN. TRY AGAIN.");
            System.out.print("ENTER YOUR PIN: ");
            entry = keyboard.nextInt();
        }

        System.out.println("\nPIN ACCEPTED. YOU NOW HAVE ACCESS TO YOUR ACCOUNT.");
        //while loops are like if statements in that they both are executed by a certain condition
        //however, if statements do not loop
        //entry does not need int infront inside the loop because it has already been declared
        //if you remove entry = keyboard.nextInt(); you will not be able to reenter the PIN
        //because it is no longer in the loop.
        
    }
}
