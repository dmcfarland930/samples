/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.righttrianglecheck;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class RightTriangleCheck {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        int side1;
        int side2;
        int side3;

        System.out.println("Enter three integers.");
        System.out.print("Side 1: ");
        side1 = keyboard.nextInt();
        while (side1 <= 0) {
            System.out.println("Enter a side larger than 0.");
            System.out.print("Try again: ");
            side1 = keyboard.nextInt();
        }
        System.out.print("Side 2: ");
        side2 = keyboard.nextInt();
        while (side2 < side1){
            System.out.println(side2+" is smaller than "+side1+".");
            System.out.print("Try again: ");
            side2 = keyboard.nextInt();
        }
        System.out.print("Side 3: ");
        side3 = keyboard.nextInt();
        while (side3 < side2){
            System.out.println(side3+" is smaller than "+side2+".");
            System.out.print("Try again: ");
            side3 = keyboard.nextInt();
        }
        
        System.out.println("Your sides are: "+side1+" "+side2+" "+side3);
        if((side1*side1)+(side2*side2) == (side3*side3)){
            System.out.println("Your sides make a right triangle!");
        }else{
            System.out.println("Your sides do not make a right triangle.");
        }
        
    }
}
