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
public class Coat extends Product {

    ConsoleIO console = new ConsoleIO();

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public String setSizeInput() {
        boolean valid = false;
        String productSize = "";
        console.readString("Enter the size of your product.");
        while (!valid) {
            productSize = console.checkEmptyString("Options: XS-XXL",
                    "You cannot leave this field blank!");
            switch (productSize) {
                case "XS":
                case "S":
                case "M":
                case "L":
                case "XL":
                case "XXL":
                    valid = true;
                    break;
                default:
                    console.readString("\nPlease enter a valid size.\n");

            }
        }
        return productSize;
    }

}
