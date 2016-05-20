/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author apprentice
 */
public class Triangle extends Shape {

    private double base;
    private double side2;
    private double side3;
    private double height;

    public Triangle(double base, double height, double side2, double side3) {

        this.base = base;
        this.height = height;
        this.side2 = side2;
        this.side3 = side3;

    }

    @Override
    public double area() {

        return (base * height) / 2;

    }

    @Override
    public double perimeter() {

        return base + side2 + side3;

    }

}
