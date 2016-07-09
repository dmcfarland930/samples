/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.olympian;

/**
 *
 * @author apprentice
 */
public class Olympian {

    private Event event;
    private String country = "USA";

    public Olympian(Event event, String filename) {

        this.event = event;
        System.out.println("My filename is: "+filename);

    }

    public String competeInEvent() {

        System.out.println("Now competing for " + country + ": ");
        return event.compete();

    }
    
    public void setCountry(String country){
        
        this.country = country;
        
    }

}
