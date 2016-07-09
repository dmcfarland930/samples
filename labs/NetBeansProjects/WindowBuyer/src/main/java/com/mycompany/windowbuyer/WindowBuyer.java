/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.windowbuyer;

import java.util.Scanner;
import java.text.*;

/**
 *
 * @author apprentice
 */
public class WindowBuyer {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.##");
        
        double glassPrice = 3.50;
        double trimPrice = 2.25;
        double height = 0;
        double width = 0;
                
        
        double  area, perim, cost1, cost2, cost3, cost4,
                newPrice, winAmount, winAmount2, fullCost, newFullCost, 
                totalCost, totalCost2;
        
        final double MAX_WINDOW_HEIGHT = 25.5;
        final double MAX_WINDOW_WIDTH = 18.75;
        final double MIN_WINDOW_HEIGHT = 1.0;
        final double MIN_WINDOW_WIDTH = 1.0;
        
        String stringHeight;
        String stringWidth;
        String stringPay;
        String stringWin;
        String stringWin2;
        
        //Display welcome message
        System.out.println("Welcome to Window Pains. A window will cost you "
                + "$3.50 per sq/ft. Trim is $2.25 per ft/trim.");
        System.out.println("");
        
        //Receive input for window height
        System.out.println("What is the height of your window (inches)?");
        
            while (height > MAX_WINDOW_HEIGHT || height < MIN_WINDOW_HEIGHT){
                
                System.out.println("");
                System.out.println("Your window must have a height of less "
                        + "than 330 inches and larger than 12 inches.");
                System.out.println("");
                System.out.print("Please enter a valid height: ");
                stringHeight = sc.nextLine();
                height = Double.parseDouble(stringHeight);
                //convert inches to feet
                height = height * 0.0833;
                
            } 
                      
        //recieve input for window width
        System.out.println("");
        System.out.println("What is the width of your window in (inches)?");
                
            while (width > MAX_WINDOW_WIDTH || width < MIN_WINDOW_WIDTH){
                
                System.out.println("");
                System.out.println("Your window must be wider than 12 inches and less "
                + "than 225 inches");
                System.out.println("");
                System.out.print("Please enter a valid width: ");
                stringWidth = sc.nextLine();
                width = Double.parseDouble(stringWidth);
                //convert to feet
                width = width * 0.0833;
            }    
        
        //calculate prices and sizes    
        area = height  * width;
        cost1 = glassPrice * area;
        perim = (height*2.0)+(width*2.0);
        cost2 = perim * trimPrice;
        fullCost = cost1 + cost2;
        
        //display window information
        System.out.println("");
        System.out.println("The height of your window is "+df.format(height)+ 
                " ft.");
        System.out.println("The width of your window is "+df.format(width)
                + " ft.");
        System.out.println("The area of your window is "+df.format(area)
                +" sq/ft" );
        System.out.println("The perimeter of your trim is "+df.format(perim)
                +" ft");
        System.out.println("The cost of your window is $"+df.format(cost1));
        System.out.println("The cost of your trim is $"+df.format(cost2));
        System.out.println("");
        
        System.out.println("An entire window with trim will cost you $"
                +df.format(fullCost));
        System.out.println("");
        
        System.out.println("How many windows would you like?");
        stringWin = sc.nextLine();
        winAmount = Double.parseDouble(stringWin);
        totalCost = winAmount * fullCost;
        
        //display total price for window purchase
        System.out.println("");
        System.out.println("Your final total is $" +df.format(totalCost));
        System.out.println("");
        
        //begin special pricing
        System.out.println("If you have a special pricing option, "
                + "enter your unique price per sq/ft: $");
        stringPay = sc.nextLine();
        newPrice = Double.parseDouble(stringPay);
        
        //calculate new cost
        cost3 = newPrice * area;
        cost4 = newPrice * perim;
        newFullCost = cost3 + cost4;
        
        //display cost information
        System.out.println("");
        System.out.println("For the special price of $"+df.format(newPrice)
                + " per sq/ft, "
                +"you can get a full window for $"+df.format(newFullCost)
                + ".");
        
        //ask for amount of windows to purchase
        System.out.println("");
        System.out.println("How many windows would you like?");
        stringWin2 = sc.nextLine();
        winAmount2 = Double.parseDouble(stringWin2);
        totalCost2 = winAmount2 * newFullCost;
        System.out.println("");
        
        //display final total for special price
        System.out.println("Your final total is $" +df.format(totalCost2));
        
    }
}
