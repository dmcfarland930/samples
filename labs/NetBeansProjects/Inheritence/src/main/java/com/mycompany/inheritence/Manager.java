/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.inheritence;

/**
 *
 * @author apprentice
 */
public class Manager extends Employee {

    public Manager() {

        super("Bill", "Jones");
        
    }

    @Override
    public void doWork() {
        
        System.out.println("Manager: Doing work. Life is good.");
    }

    public void hire() {
        System.out.println("Manager: Hiring");
    }

    public void fire() {
        System.out.println("Manager: Firing");
    }
}
