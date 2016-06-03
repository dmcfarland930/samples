/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Taxes {
    
    @NotEmpty(message="You cannot leave this field blank!")
    @Size (min=2, max=2, message="You must enter a state abbreviation!")
    private String state;
    @DecimalMax("999999999999.0") @DecimalMin("0.0") 
    private double taxRate;
    

    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    
    
}
