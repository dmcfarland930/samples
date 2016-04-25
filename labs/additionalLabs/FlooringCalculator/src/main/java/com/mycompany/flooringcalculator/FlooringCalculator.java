/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringcalculator;

import java.util.Scanner;
import java.text.*;
/**
 *
 * @author apprentice
 */
public class FlooringCalculator {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
        
        double width, length, cost, roomWidth, roomLength, roomWidthIn, 
                roomLengthIn, areaTile, areaRoom, areaRoomIn,
                tileAmount, costTotal, timeWork, costLabor, quartCost,
                finalTotal;
        
        //Constant for amount of flooring per hour in sq/ft
        final int FLOORING_LIMIT = 20;
        //Constant for cost of labor per hour
        final double HOUR_COST = 86;
        
        //Ask the user for width length and cost of 1 unit of flooring
        System.out.println("Welcome to Floorer Bouquet!");
        System.out.println("");
        System.out.print("Please enter the width of the unit of flooring "
                + "(inches): ");
        width = keyboard.nextDouble();
        System.out.println("");
        
        System.out.print("Please enter the length of the unit of flooring "
                + "(inches): ");
        length = keyboard.nextDouble();
        System.out.println("");
        //Calculate area of tile
        areaTile = width * length;
        
        System.out.print("Please enter the cost per unit of flooring: ");
        cost = keyboard.nextDouble();
        System.out.println("");
        System.out.println("You are requesting a "+df.format(width)+"x"
                +df.format(length)+" inch tile for $"+df.format(cost)
                +" per unit.");
        System.out.println("");
        
        //Ask the user for the area of the room they would like to cover
        System.out.print("What is the width of your room (feet): ");
        roomWidth = keyboard.nextDouble();
        //Convert width of room from feet to inches
        roomWidthIn = roomWidth * 12;
        System.out.print("What is the length of your room (feet): ");
        roomLength = keyboard.nextDouble();
        //Convert length of room from feet to inches
        roomLengthIn = roomLength * 12;
        
        //Calculate room area
        areaRoom = roomWidth * roomLength;
        //Caculate room area in sq/in
        areaRoomIn = roomWidthIn * roomLengthIn;
        //Calculate amount of tiles
        tileAmount = areaRoomIn / areaTile;
        costTotal = tileAmount * cost;
        
        //Show results of purchase
        System.out.println("");
        System.out.println("To cover a "+df.format(areaRoom)+" sq/ft room,"
                + " you will need "+df.format(tileAmount)+ " tile(s) at the"
                + " dimensions you entered.");
        System.out.println("At $"+df.format(cost)+" per tile, your final"
                + " total will be $"+df.format(costTotal)+".");
        
        //Now determine cost of labor
        System.out.println("");
        System.out.println("Our labor team charges a rate of $"+HOUR_COST+""
                + "/hr at 15 minute increments.");
        quartCost = HOUR_COST / 4;
        //Calculate amount of time to complete floor
        timeWork = areaRoom / 20;
        if(Math.round(timeWork) < 1){
            System.out.println("It is estimated that our labor team will complete"
                + " your floor in less than an hour.");
        }else{
        System.out.println("It is estimated that our labor team will complete"
                + " your floor in about "+Math.round(timeWork)+" hour(s).");
        }
        //Convert work hours to minutes
        timeWork *= 60;
        timeWork /= 15;
        costLabor = timeWork * quartCost;
        System.out.println("");
        System.out.println("Final Total");
        System.out.println("-----------------------------");
        System.out.println("          Cost of tiles: $"+df.format(costTotal));
        System.out.println("Estimated cost of labor: $"+df.format(costLabor));
        finalTotal = costTotal + costLabor;
        System.out.println("");
        System.out.println("             Final Cost: $"+df.format(finalTotal));
        
        
    }
}
