/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class Exceptions {

    public static void main(String[] args) {

//        String value = null;
//        
//        if (value != null){
//        
//        value.concat("asdsa");
//        
//    }
        int input = getUserInput("Please enter an integer");

    }

    public static int getUserInput(String prompt){

        Scanner keyboard = new Scanner(System.in);

        boolean isValid = false;
        int input = 0;

        while (!isValid) {

            System.out.println(prompt);

            String userInput = keyboard.nextLine();

            try {
                input = Integer.parseInt(userInput);
                isValid = true;

            } catch (Exception ex) {
                
            }

        }
        
        return input;
    }
    

    public static void readFile() {
        FileInputStream fi = null;
        try {
            FileInputStream fi = new FileInputStream("/adf");

            int i = 0;
            i++;
            System.out.println(i);

            fi.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exceptions.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(Exceptions.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            //here we'd close the file
            fi.close();
        }

    }
