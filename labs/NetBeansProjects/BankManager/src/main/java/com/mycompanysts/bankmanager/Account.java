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
public abstract class Account {

    protected String pin;
    protected Double balance;
    protected Double pendingBalance;
    protected boolean isLocked;
    ConsoleIO io = new ConsoleIO();
    DecimalFormat df = new DecimalFormat("0.00");

    public abstract void withdraw();

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void deposit() {

        Double amount = enterDeposit();
        if (amount >= 10000) {
            io.readString("Your desposit exceeds $10,000.");
            io.readString("$" + df.format(amount) + " added to your pending balance.");

            pendingBalance += amount;
        } else {

            io.readString("You deposited $" + df.format(amount));
            balance += amount;

        }

        showBalance();
    }

    public void showBalance() {
        io.readString("Your current balance is : " + balance);
        if (pendingBalance != 0) {
            io.readString("Your Pending balance is : " + df.format(pendingBalance));
        }

    }

    public Double enterDeposit() {
        Double amount = io.getDouble("Enter Deposit Amount: ", "That is an invalid amount!");
        return amount;
    }

    public Double enterWithdrawl() {

        Double withdrawAmount = io.getDouble("Input withdrawl amount: ", "That amount is invalid!");
        return withdrawAmount;

    }

}
