/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dto;

import com.mycompany.flooringmasteryweb.validation.ValidState;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author apprentice
 */
public class Taxes {
    
    private int id;
    
    @Size (min=2, max=2, message="You must enter a state abbreviation!")
    @ValidState
    private String state;
        
    @NotNull(message="Please enter a tax rate!")
    private Double taxRate;
        
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
