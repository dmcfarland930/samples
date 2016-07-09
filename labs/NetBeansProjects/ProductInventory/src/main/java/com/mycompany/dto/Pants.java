/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dto;

import com.mycompany.controller.ConsoleIO;

/**
 *
 * @author apprentice
 */
public class Pants extends Product {

    ConsoleIO console = new ConsoleIO();

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public String setSizeInput() {
            
        String waist = console.checkEmptyString("Enter the waist size:",
                "You cannot leave this field blank!");
        
        String inseam = console.checkEmptyString("Enter the inseam size:",
                "You cannot leave this field blank");
                       
        return waist+"x"+inseam;

    }

}

