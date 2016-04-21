/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.theforgetfulmachine;

import java.util.Scanner;
/**
 *
 * @author apprentice
 */
public class TheForgetfulMachine{
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Give me a word! ");
        keyboard.nextLine();
        System.out.print("Give me a second word! ");
        keyboard.nextLine();
        System.out.println("");
        
        System.out.print("Great, now your favorite number? ");
        keyboard.nextLine();
        System.out.print("And your second-favorite number... ");
        keyboard.nextLine();
        System.out.println("Whew! Wasn't that fun?");
        
    }
}
