/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.statecapitals2;

/**
 *
 * @author apprentice
 */
public class Capitals {
    
    String name;
    int population;
    int sqMile;
    
    
    public Capitals setStateInfo(String name, int population, int sqMile){
    
        Capitals cap = new Capitals();
        
        cap.setName(name);
        cap.setPopulation(population);
        cap.setSqMile(sqMile);
        
        
        return cap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getSqMile() {
        return sqMile;
    }

    public void setSqMile(int sqMile) {
        this.sqMile = sqMile;
    }
    
}
