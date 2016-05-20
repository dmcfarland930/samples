/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author apprentice
 */
public class Circle extends Shape {

    private double radius;
    private final double PI = 3.14;

    public Circle(double radius) {

        this.radius = radius;

    }

    @Override
    public double area() {

        return PI * (radius * radius);

    }

    @Override
    public double perimeter() {

        return 2 * PI * radius;

    }
}
