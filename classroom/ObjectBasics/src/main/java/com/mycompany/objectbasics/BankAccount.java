/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.objectbasics;

/**
 *
 * @author apprentice
 */
public class BankAccount {
    
    public static int MIN_BALANCE = 0;

    private double balance;
    private String ownerName;

    public BankAccount() {
        System.out.println("Create a new bank account");
        MIN_BALANCE = 11;
    }

    public BankAccount(double initialDeposit) {
        this();
        this.balance = initialDeposit;
        System.out.println("Created bank account with balance");

    }
    
    public BankAccount(int inititialDeposit){
        
        
    }

    public BankAccount(double initialDeposit, String ownerName) {
        this(145);

    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void deposit(double money) {
        this.balance = money;
    }

    public double getBalance() {
        return this.balance;
    }
}
