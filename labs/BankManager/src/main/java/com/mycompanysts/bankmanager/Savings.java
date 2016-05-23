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
public class Savings extends Account {

    ConsoleIO io = new ConsoleIO();
    DecimalFormat df = new DecimalFormat("0.00");

    public Savings(Double beginBalance) {
        this.balance = beginBalance;
        pendingBalance = 0.0d;
    }

    public void depositSavings(Double amount) {
        deposit();
    }

    @Override
    public void withdraw() {

        showBalance();
        if (balance <= 0) {
            io.readString("Your balance is empty! Unable to withdraw!");
        } else if (balance > 0) {

            Double withdrawAmount = enterWithdrawl();
            balance -= withdrawAmount;
            io.readString("$" + df.format(withdrawAmount) + " withdrawn from your savings account. "
                    + "You will be charged a $5 withdrawl fee.");
            balance -= 5;
        }

    }
}
