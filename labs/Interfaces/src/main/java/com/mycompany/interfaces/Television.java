/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.interfaces;

/**
 *
 * @author apprentice
 */
public class Television implements Colorable {

    private String color;

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }

}
