/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.howoldareyouspecifically;

import java.util.Scanner;
/**
 *
 * @author apprentice
 */
public class HowOldAreYouSpecifically {
    
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        
        String name;
        int age;
        
        System.out.print("Hey, what's your name? (Sorry I keep forgetting) ");
        name = keyboard.next();
        
        System.out.print("Ok, "+name+ " , how old are you? ");
        age = keyboard.nextInt();
        
        if(age < 16){
            
            System.out.println("");
            System.out.println("You can't drive "+name);
            
        }else if(age >= 16 && age <= 17){
            
            System.out.println("");
            System.out.println("You can drive, but you can't vote "+name);
            
        }else if(age >= 18 && age <= 24){
            
            System.out.println("");
            System.out.println("You can vote, but not rent a car "+name);
            
        }else if(age >= 25){
            
            System.out.println("");
            System.out.println("You can do pretty much anything "+name);
            
        }else{
        
            System.out.println("This ain't right.");
            
        }    
        
    }
        
        
        
}
