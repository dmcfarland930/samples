/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.shapesandperimeters;

/**
 *
 * @author apprentice
 */
public class Main {
    
    public static void main(String[] args) {
        
        Shape square = new Square(4);
        System.out.println("Area of Square with a width of 4: "+square.area());
        System.out.println("Perimeter of Square with a width of 4: "+square.perimeter());
        
        
        Shape rectangle = new Rectangle(4,8);
        System.out.println("Area of Rectangle with a width of 4 and height of 8: "+rectangle.area());
        System.out.println("Perimeter of Rectangle with a width of 4 and height of 8: "+rectangle.perimeter());
        
        Shape triangle = new Triangle(3, 6, 7, 8);
        System.out.println("Area of Triangle with a base of 3 and a height of 6: "+triangle.area());
        System.out.println("Perimeter of Triangle with a side of 3 another side of 7 and another side of 8: "+triangle.perimeter());
        
        Shape circle = new Circle(6);
        System.out.println("Area of Circle with a radius of 6: "+circle.area());
        System.out.println("Circumference of Circle with a radius of 6: "+circle.perimeter());
    }
    
}
