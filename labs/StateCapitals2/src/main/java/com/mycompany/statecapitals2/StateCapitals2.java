/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.statecapitals2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */

public class StateCapitals2 {

    public static void main(String[] args) {
        Map<String, Capitals> myMap = new HashMap();
        Capitals capitals = new Capitals();
        ConsoleIO console = new ConsoleIO();

                
        myMap.put("Alabama", capitals.setStateInfo("Montgomery", 205000, 156));
        myMap.put("Alaska", capitals.setStateInfo("Juneau", 31000, 3255));
        myMap.put("Arizona", capitals.setStateInfo("Phoenix", 1445000, 517));
        myMap.put("Arkansas", capitals.setStateInfo("Little Rock", 193000, 116));
        myMap.put("Oregon", capitals.setStateInfo("Salem", 160614, 48));
        myMap.put("West Virginia", capitals.setStateInfo("Charleston", 50821, 32));
        myMap.put("Ohio", capitals.setStateInfo("Columbus", 822553, 223));
        myMap.put("New York", capitals.setStateInfo("Albany", 98424, 22));
        myMap.put("Hawaii", capitals.setStateInfo("Honolulu", 374658, 68));

        Set<String> states = myMap.keySet();


        System.out.println("\nSTATES/CAPITAL PAIRS:");
        System.out.println("=======================");
        
        for (String myKey : states) {

            capitals = myMap.get(myKey);
            System.out.println(myKey + " - " + capitals.getName() + " | Pop: " 
                    + capitals.getPopulation() + " | Area: " + capitals.getSqMile() + " sq mi");

        }

        int userInput = console.getInteger("\nPlease enter the lower limit for "
                + "capital city populations: \n", "\nThat is an invalid entry");

            
            
            
                    
        System.out.println("\nLISTING CAPITALS WITH POPULATIONS GREATER THAN " + userInput + ":\n");

        for (String myKey : states) {

            Capitals value;
            capitals = myMap.get(myKey);
            
            int pop = capitals.getPopulation();
            
            if( pop > userInput){
                        
                System.out.println(myKey + " - " + capitals.getName() + " | Pop: " + capitals.getPopulation() + " | Area: " + capitals.getSqMile() + " sq mi");
                
            }
        }

    }

}
