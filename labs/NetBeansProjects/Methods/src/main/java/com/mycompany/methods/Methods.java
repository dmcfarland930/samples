/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methods;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class Methods {
    
    public static void main(String[] args) {
        
        helloWorld();
        
        double pi = calculatePi();
        System.out.println(pi);
        
        doNothing(5);
        
        int sum = add(1,2);
        System.out.println("Sum 1 : "+sum);
        sum = add(3,4);
        System.out.println("Sum 2 : "+sum);
        
        int operand1 = getUserInputInt("Enter the first number to be added: ");
        int operand2 = getUserInputInt("Enter the second number to be added: " );
        
        
        System.out.println("sum is "+sum);
    }
    
    public static void helloWorld(){
        System.out.println("Hello World");
    }
    
    public static double calculatePi(){
        return 3.145;
    }
    
    public static void doNothing (int silly){
        
    }
    
    public static int add1And1(){
        return 1 + 1;
    }
    
    public static int add1And2(){
        return 1 + 2;
    }
    
    public static int add(int number1, int number2){
        
        return number1 + number2;
    }
    
    public static int getUserInputInt(String prompt) {
       
        
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = sc.nextLine();
        
        int input = Integer.parseInt(userInput);
        
        
        return input;
        
    }
    }
    
    

