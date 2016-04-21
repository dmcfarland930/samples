/**
 * Else if creates another conditional statement to pass through to check 
 * validity. Else is used when no statements are true.
 * 
 * Removing the else from else if creates a brand new statement. Because the 
 * statement cars < people is not true, it turns to else.
 */
package com.mycompany.elseandif;

/**
 *
 * @author apprentice
 */
public class ElseAndIf {
    
    public static void main(String[] args) {
        
        int people = 30;
        int cars = 40;
        int buses = 15;
        
        //compare cars and people
        if (cars > people){
            
            System.out.println("We should take the cars.");
        
        } else if (cars < people) {
            
            System.out.println("We should not take the cars.");
            
        } else {
            
            System.out.println("We can't decide.");
            
        }
        
        //compare buses to cars
        if (buses > cars){
            
            System.out.println("That's too many buses.");
        
        } else if (buses < cars) {
            
            System.out.println("Maybe we could take the buses.");
            
        } else {
            
            System.out.println("We still can't decide.");
            
        }
        
        //compare buses to people
        if (people > buses){
            
            System.out.println("All right, let's just take the buses.");
            
        } else {
            
            System.out.println("Fine let's stay home then.");
            
        }
    }
    
}
