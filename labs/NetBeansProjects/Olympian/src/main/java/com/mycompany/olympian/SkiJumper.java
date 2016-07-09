/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.olympian;

/**
 *
 * @author apprentice
 */
public class SkiJumper {

    private Event event;

    public SkiJumper() {
        event = new SkiJumpEvent();
    }

    public String competeInEvent() {
        return event.compete();
    }

}
