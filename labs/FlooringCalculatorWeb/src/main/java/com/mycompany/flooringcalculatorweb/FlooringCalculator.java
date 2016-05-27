/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringcalculatorweb;

import java.util.Scanner;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Order;

/**
 *
 * @author apprentice
 */
public class FlooringCalculator {

    double widthTile, lengthTile, costTile, roomWidth, roomLength, roomWidthIn,
            roomLengthIn, areaTile, areaRoom, areaRoomIn,
            tileAmount, tileTotal, timeWork, costLabor, quartCost,
            finalTotal;
    String time;
    String costPerTile;
    String area;

    public void run(double widthTile, double lengthTile, double costTile, double widthRoom, double lengthRoom) {

        this.widthTile = widthTile;
        this.lengthTile = lengthTile;
        this.costTile = costTile;
        this.roomWidth = widthRoom;
        this.roomLength = lengthRoom;

        DecimalFormat df = new DecimalFormat("#.##");

        //Constant for cost of labor per hour
        final double HOUR_COST = 86;

        //Calculate area of tile
        areaTile = widthTile * lengthTile;
        //Ask the user for the area of the room they would like to cover
        System.out.print("What is the width of your room (feet): ");
        //Convert width of room from feet to inches
        roomWidthIn = roomWidth * 12;
        System.out.print("What is the length of your room (feet): ");
        //Convert length of room from feet to inches
        roomLengthIn = roomLength * 12;

        //Calculate room area
        areaRoom = roomWidth * roomLength;
        //Caculate room area in sq/in
        areaRoomIn = roomWidthIn * roomLengthIn;
        //Calculate amount of tiles
        tileAmount = areaRoomIn / areaTile;
        tileTotal = tileAmount * costTile;

        //Show results of purchase
        System.out.println("");
        area = ("To cover a " + df.format(areaRoom) + " sq/ft room,"
                + " you will need " + df.format(tileAmount) + " tile(s) at the"
                + " dimensions you entered.");
        costPerTile = "At $" + df.format(costTile) + " per tile, your tile"
                + " total will be $" + df.format(tileTotal) + ".";

        //Now determine cost of labor
        System.out.println("");
        System.out.println("Our labor team charges a rate of $" + HOUR_COST + ""
                + "/hr at 15 minute increments.");
        quartCost = HOUR_COST / 4;
        //Calculate amount of time to complete floor
        timeWork = areaRoom / 20;
        if (Math.round(timeWork) < 1) {
            time = "It is estimated that our labor team will complete"
                    + " your floor in less than an hour.";
        } else {
            time = "It is estimated that our labor team will complete"
                    + " your floor in about " + Math.round(timeWork) + " hour(s).";
        }
        //Convert work hours to minutes
        timeWork *= 60;
        timeWork /= 15;
        costLabor = timeWork * quartCost;
        System.out.println("");
        System.out.println("Final Total");
        System.out.println("-----------------------------");
        System.out.println("          Cost of tiles: $" + df.format(tileTotal));
        System.out.println("Estimated cost of labor: $" + df.format(costLabor));
        finalTotal = tileTotal + costLabor;
        System.out.println("");
        System.out.println("             Final Cost: $" + df.format(finalTotal));

    }

    public double getTileTotal() {
        return tileTotal;
    }

    public double getCostLabor() {
        return costLabor;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public String getTime() {
        return time;
    }

    public String getCostPerTile() {
        return costPerTile;
    }

    public String getArea(){
        return area;
    }
}
