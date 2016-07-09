/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class Collections {

    public static void main(String[] args) {

        List<Integer> myList = new ArrayList();

        myList.add(1);
        myList.add(23);

        Iterator it = myList.iterator(); //an object that the collection owns and takes care of. we ask it for information

        while (it.hasNext()) {

            int value = (int) it.next();
            System.out.println(value);

        }

        for (Integer value : myList) {
            System.out.println(value);

        }

        List<String> stringList = new ArrayList();

        stringList.add("Jones");
        stringList.add("Sally");

        for (String myString : stringList) {
            System.out.println(myString);
        }

        Iterator<String> it2 = stringList.iterator();

        while (it2.hasNext()) {
            String theString = it2.next();
        }

        stringList.remove("Jones");

        int size = stringList.size();

        boolean exists = stringList.contains("Jones");

        Map<String, Integer> populations = new HashMap();

//            myMap.put(1, "x");
//            myMap.put("x", 2);
        populations.put("USA", 300000000);
        populations.put("Canada", 20000000);
        populations.put("UK", 600000);
        populations.put("Japan", 13000000);

        System.out.println("Map size is: " + populations.size());
        
        int usaValue = populations.get("USA");
        
        Set<String> keySet = populations.keySet();  
        
        for(String key : keySet){
            
            Integer value = populations.get(key);
            System.out.println(key+": "+value);
            
        }
        
        Collection <Integer> values = populations.values();
        
        for(Integer value : values){
            System.out.println(value);
            
        }
        
    }

    private List<String> getStringList() {

        List<String> myStringList = new ArrayList();
        return myStringList;

    }

}
