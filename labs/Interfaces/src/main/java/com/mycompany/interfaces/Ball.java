/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.interfaces;

/**
 *
 * @author apprentice
 */
public class Ball implements Colorable, Debuggable {

    private String color;

    public void printStuff() {

        System.out.println("Hootie");

    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public void logError(String error) {
        System.out.println("Logging error about ball "+ error);
    }

}
