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
public class Shoe extends Product {

    ConsoleIO console = new ConsoleIO();

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public String setSizeInput() {
        String productSize = console.checkEmptyString("Enter the size of your product:",
                "You cannot leave this field blank!");
        return productSize;
    }

}
