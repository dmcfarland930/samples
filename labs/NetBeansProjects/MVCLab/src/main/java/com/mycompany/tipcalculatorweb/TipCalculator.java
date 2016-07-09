/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.tipcalculatorweb;

/**
 *
 * @author apprentice
 */
public class TipCalculator {
    
    private double amountEntry;
    private double tipEntry;
    private double tip;
    private double total;
    
    public void run(double amountEntry, double tipEntry){
        
        this.amountEntry = amountEntry;
        this.tipEntry = tipEntry;
        
        this.tip = amountEntry * (tipEntry/100);
        this.total = amountEntry + tip;
        
        
    }

    public double getAmountEntry() {
        return amountEntry;
    }

    public double getTipEntry() {
        return tipEntry;
    }

    public double getTip() {
        return tip;
    }

    public double getTotal() {
        return total;
    }
    
    
    
}
