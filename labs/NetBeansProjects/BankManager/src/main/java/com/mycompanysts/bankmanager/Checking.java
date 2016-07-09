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
public class Checking extends Account {

    ConsoleIO io = new ConsoleIO();
    DecimalFormat df = new DecimalFormat("0.00");

    public Checking(Double beginBalance) {
        this.balance = beginBalance;
        pendingBalance = 0.0d;
    }

    public void depositChecking(Double amount) {
        deposit();
    }

    @Override
    public void withdraw() {

        showBalance();

        if (!isLocked) {
            Double withdrawAmount = enterWithdrawl();

            balance -= withdrawAmount;
            io.readString("$" + df.format(withdrawAmount) + " withdrawn from your checking account!");

            if (balance == 0) {
                io.readString("Your balance is empty!");

            } else if (balance < 0 && balance > -100) {
                io.readString("Your account has been overdrafted. You will be charged"
                        + " a $10 fee.");
                balance -= 10;
            } else if (balance < 0 && balance <= -100) {
                io.readString("Your have overdrafted over $100. You account is locked!");
                isLocked = true;
            }

        } else {
            io.readString("Account is Locked.");
        }
    }

}
