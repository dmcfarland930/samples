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
    private int quarterCount = 0;
    private int dimeCount = 0;
    private int nickelCount = 0;
    private int pennyCount = 0;
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

    public int getQuarterCount() {
        return quarterCount;
    }

    public void setQuarterCount(int quarterCount) {
        this.quarterCount = quarterCount;
    }

    public int getDimeCount() {
        return dimeCount;
    }

    public void setDimeCount(int dimeCount) {
        this.dimeCount = dimeCount;
    }

    public int getNickelCount() {
        return nickelCount;
    }

    public void setNickelCount(int nickelCount) {
        this.nickelCount = nickelCount;
    }

    public int getPennyCount() {
        return pennyCount;
    }

    public void setPennyCount(int pennyCount) {
        this.pennyCount = pennyCount;
    }

}
