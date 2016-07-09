/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.lambdas;

/**
 *
 * @author apprentice
 */
public class Address {
    
    private String streetAddress;
    private String city;

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public int getNumberOfCharactersInCity(){
        return city.length();
    }
    
}
