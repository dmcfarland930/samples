/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.statecapitals;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StateCapitals {

    public static void main(String[] args) {
        Map<String, String> myMap = new HashMap();

        myMap.put("Alabama", "Montgomery");
        myMap.put("Alaska", "Juneua");
        myMap.put("Arizona", "Phoenix");
        myMap.put("Arkansas", "Little Rock");

        Set<String> states = myMap.keySet();

        System.out.println("STATES:\n");
        for (String myKey : states) {

            System.out.println(myKey);

        }

        System.out.println("\nCAPITALS:\n");
        for (String myKey : states) {

            String value = myMap.get(myKey);
            System.out.println(value);

        }

        System.out.println("\nSTATES AND CAPITALS:\n");
        for (String myKey : states) {

            String value = myMap.get(myKey);
            System.out.println(myKey+ " " +value);

        }

    }

}
