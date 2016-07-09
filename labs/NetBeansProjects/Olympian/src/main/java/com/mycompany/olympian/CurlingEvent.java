/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.olympian;

/**
 *
 * @author apprentice
 */
public class CurlingEvent implements Event {

    @Override
    public String compete() {
  
        System.out.println("Curling: sweeeeeeeeeeeeep");
        return "Curling";
    
    }
    
}
