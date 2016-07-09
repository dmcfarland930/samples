/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.inheritence;

/**
 *
 * @author apprentice
 */
public abstract class Employee {
    
    protected String firstName;
    private String lastName;
    
    public Employee(){
        System.out.println("Employee constructed.");
    }
    
    public Employee(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println(firstName+" "+lastName+" constructed.");
    }
    
        public abstract void doWork();
    
//    public void doWork(){
//        System.out.println("Employee: Do work.");
//    }
    
    public void createObjectives(){
        System.out.println("Employee objectives: Don't get fired");
    }
    
    
    
}
