/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.ChangeDao;
import com.mycompany.dao.InventoryDao;

/**
 *
 * @author apprentice
 */
public class VendingController {

    ConsoleIO console = new ConsoleIO();
    InventoryDao invDao = new InventoryDao();
    ChangeDao changeDao = new ChangeDao();

    public void runApp() {

        menuDisplay();

    }

    public void menuDisplay() {

        String inPrice;
        float inPriceF = -1;
        String adminPass = "DOGMEAT";
        boolean run = true;
        while (run == true) {

            System.out.println("Hello, hungry traveler.");
            System.out.println("Enter your money for sustanence.");

            System.out.println("+====================================+");
            System.out.println("  ID # | PRICE | NAME         | STOCK ");
            System.out.println("--------------------------------------");

            System.out.println("Enter 0 to quit.");
            inPrice = console.checkEmptyString("Enter amount of change: ", "You have to put something in!");
            
            if (inPrice.equals(adminPass)) {
                //open admin menu
                adminMenuDisplay();
            }
            
            try {
                inPriceF = Float.parseFloat(inPrice);
            } catch (Exception ex) {
                if (inPrice.equals(adminPass)){
                    inPrice = "0";
                    inPriceF = Float.parseFloat(inPrice);
                }else{
                System.out.println("We don't accept that kind of change!");
                }
            }

            if (inPriceF == 0) {
                run = false;
            } else if (inPriceF > 0) {
                //select item method

            }

        }

    }

    public void adminMenuDisplay() {

        boolean run = true;
        while (run == true) {
            System.out.println("Welcome to the secret admin level!");
            System.out.println("What would you like to do?");
            System.out.println("  1) Add item to inventory");
            System.out.println("  2) Remove item from inventory");
            System.out.println("  3) Update price of item");
            System.out.println("  4) View Stock");
            System.out.println("  5) Update Stock");
            System.out.println("  6) View transaction record");
            System.out.println(" --------------------------------");
            System.out.println("  0) Quit");

            String selection = console.getString("\nEnter option: ");

            switch (selection) {

                case "1":
                    //add item
                    break;
                case "2":
                    //remove item
                    break;
                case "3":
                    //update price
                    break;
                case "4":
                    //view stock;
                    break;
                case "5":
                    //update stock
                    break;
                case "6":
                    //view record
                    break;
                case "0":
                    menuDisplay();
                    run=false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;

            }
        }

    }
    
    public void addItem(){
        
        
        
        
    }

}
