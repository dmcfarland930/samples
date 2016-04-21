/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ageinfiveyears;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class AgeInFiveYears {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        
        String name;
        int age, ageOld, ageYoung;
        
        System.out.print("Hello! What is your name? ");
        name = keyboard.next();
        
        System.out.println("");
        System.out.print("Hi "+name+"! How old are you? ");
        age = keyboard.nextInt();
        
        ageOld = age + 5;
        ageYoung = age - 5;
        
        System.out.println("");
        System.out.println("Did you know in five years you will be "+ageOld+
                " years old?");
        System.out.println("And five years ago, you were "+ageYoung+
                "! Imagine that!");   
               
    }
    
    
}
