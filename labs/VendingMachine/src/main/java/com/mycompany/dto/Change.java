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
    private float enteredAmount = 0;
    private float spentAmount;
    private float changeAmount;
    private float quarterCount;
    private float dimeCount;
    private float nickelCount;
    private float pennyCount;
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

    public float getSpentAmount() {
        return spentAmount;
    }

    public void setSpentAmount(float spentAmount) {
        this.spentAmount = spentAmount;
    }

    public float getQuarterCount() {
        return quarterCount;
    }

    public void setQuarterCount(float quarterCount) {
        this.quarterCount = quarterCount;
    }

    public float getDimeCount() {
        return dimeCount;
    }

    public void setDimeCount(float dimeCount) {
        this.dimeCount = dimeCount;
    }

    public float getNickelCount() {
        return nickelCount;
    }

    public void setNickelCount(float nickelCount) {
        this.nickelCount = nickelCount;
    }

    public float getPennyCount() {
        return pennyCount;
    }

    public void setPennyCount(float pennyCount) {
        this.pennyCount = pennyCount;
    }
    
}
