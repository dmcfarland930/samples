/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author apprentice
 */
public class Rectangle extends Shape{

    private double width;
    private double height;
    
    public Rectangle(double width, double height){
        
        this.width = width;
        this.height = height;
        
    }
    
    @Override
    public double area() {
        
        return width * height;
        
    }

    @Override
    public double perimeter() {
        
        return (width*2) + (height*2);
        
    }
    
}
