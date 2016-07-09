/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompanysts.bankmanager;

import java.text.DecimalFormat;

/**
 *
 * @author apprentice
 */
public class BankManager {
    //static 

    public static void main(String[] args) {

        ConsoleIO io = new ConsoleIO();
        Checking checking = new Checking(1000.00);
        Savings savings = new Savings(5000.00);
        DecimalFormat df = new DecimalFormat("0.00");
        boolean isRunning = true;
        checking.setPin("1234");

        String pinInput = io.getString("Enter Your PIN number:");
        if (pinInput.equals(checking.getPin())) {

            while (isRunning) {

                io.readString("\nCHECKING BALANCE: " + df.format(checking.balance));
                if (savings.balance > 0) {
                    io.readString("SAVINGS BALANCE: " + df.format(savings.balance) + "\n");
                } else {
                    io.readString("You Owe: " + (savings.balance) * (-1) + "\n");
                }

                io.readString("1. Deposit to Checking");
                io.readString("2. Withdraw from Checking");
                io.readString("3. Deposit to Savings");
                io.readString("4. Withdraw from Savings");
                io.readString("5. Exit");

                int option = io.getInteger("\nEnter Your Option: ", "That is an invalid option!");

                switch (option) {
                    case 1:
                        checking.deposit();
                        break;
                    case 2:
                        checking.withdraw();
                        break;
                    case 3:
                        savings.deposit();
                        break;
                    case 4:
                        savings.withdraw();
                        break;
                    case 5:
                        isRunning = false;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
