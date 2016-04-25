/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.swinginput;

import javax.swing.*;
/**
 *
 * @author apprentice
 */
public class SwingInput {
    public static void main(String[] args) {
        
        //Open a window to input name
        String name = JOptionPane.showInputDialog("What is your name?");
        
        //Open a window to input age
        String input = JOptionPane.showInputDialog("How old are you?");
        //Parse the string into an integer for math stuff
        int age = Integer.parseInt(input);
        
        System.out.print("Hello, " +name+ ". ");
        System.out.println("Next year, you'll be "+ (age+1));
        
        System.exit(0);
    }
    
}
