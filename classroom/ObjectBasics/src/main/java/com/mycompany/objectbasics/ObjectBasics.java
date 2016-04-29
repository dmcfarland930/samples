/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.objectbasics;

/**
 *
 * @author apprentice
 */
public class ObjectBasics {

    public static void main(String[] args) {

        int minBalance = BankAccount.MIN_BALANCE;
        
        BankAccount account = new BankAccount(56d);

        
        account.deposit(45);

        System.out.println(""+account.getOwnerName());
    }
}
