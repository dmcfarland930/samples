/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author apprentice
 */
public class Square extends Shape {

    private double width;

    public Square(double width) {

        this.width = width;

    }

    @Override
    public double area() {

        return width*width;

    }

    @Override
    public double perimeter() {

        return (width * 4);
    }

}
