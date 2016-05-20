/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dto;

/**
 *
 * @author apprentice
 */
public class Pants extends Product{
    
    private String inseamSize;
    private String waistSize;

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public void setSize(String size) {
        
        String pantsSize = (waistSize+" x "+inseamSize);
        this.size = pantsSize;
                
    }

    public String getInseamSize() {
        return inseamSize;
    }

    public void setInseamSize(String inseamSize) {
        this.inseamSize = inseamSize;
    }

    public String getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(String waistSize) {
        this.waistSize = waistSize;
    }

    
   
    
}
