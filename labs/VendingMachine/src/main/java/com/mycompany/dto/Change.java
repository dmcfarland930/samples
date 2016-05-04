/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dto;

/**
 *
 * @author apprentice
 */
public class Change {
    
    int transId;
    public static final float QUARTER = .25f;
    public static final float DIME = .10f;
    public static final float NICKEL = .05f;
    public static final float PENNY = .01f;
    private float enteredAmount;
    private float changeAmount;
    Inventory inv;

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public float getEnteredAmount() {
        return enteredAmount;
    }

    public void setEnteredAmount(float enteredAmount) {
        this.enteredAmount = enteredAmount;
    }

    public float getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(float changeAmount) {
        this.changeAmount = changeAmount;
    }
    
    
}
