/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.olympian;

/**
 *
 * @author apprentice
 */
public class SkiJumpEvent implements Event{

    @Override
    public String compete() {
        System.out.println("SkiJump: weeeeeeeeeee");
        return "SkiJump";
    }
    
}
