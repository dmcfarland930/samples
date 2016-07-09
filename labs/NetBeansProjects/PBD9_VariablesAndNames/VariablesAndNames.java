/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.variablesandnames;

/**
 *
 * @author apprentice
 */
public class VariablesAndNames {

    public static void main(String[] args){
        
        int cars, drivers, passengers, carsNotDriven, carsDriven;
        double spaceInACar, carpoolCapacity, averagePassengersPerCar;
        
        cars = 100;
        spaceInACar = 4;
        drivers = 30;
        passengers = 90;
        carsNotDriven = cars - drivers;
        carsDriven = drivers;
        carpoolCapacity = carsDriven * spaceInACar;
        averagePassengersPerCar = passengers / carsDriven;
        
        System.out.println("There are " + cars + " cars available.");
        System.out.println("There are only " + drivers + " drivers available.");
        System.out.println("There will be " + carsNotDriven + " empty cars today.");
        System.out.println("We can transport " + carpoolCapacity + " people today.");
        System.out.println("There are " + passengers + " cars available.");
        System.out.println("We need to put about " + averagePassengersPerCar + " in each car.");
        
        
    }
    
}
