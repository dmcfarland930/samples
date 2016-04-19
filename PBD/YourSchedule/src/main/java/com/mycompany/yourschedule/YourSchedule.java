/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
 */
package com.mycompany.yourschedule;

/**
 *
 * @author apprentice
 */
public class YourSchedule {
    
    public static void main (String[] args){
        
        //let's name some classes
        String classOne, classTwo, classThree, classFour, classFive, classSix,
                classSeven, classEight;
        //name teachers
        String teachOne, teachTwo, teachThree, teachFour, teachFive, teachSix,
                teachSeven, teachEight;
        
        //assign values
        classOne =  "Java 101";
        classTwo =  "Git 101";
        classThree =  "PoliSci 111";
        classFour =  "Business 105";
        classFive =  "English 101";
        classSix =  "Physics 206";
        classSeven =  "Philosophy 205";
        classEight =  "Art 410";
        teachOne = "Mr. Toner";
        teachTwo = "Mr. Pudelski";
        teachThree = "Mr. McCheese";
        teachFour = "Mr. Trump";
        teachFive = "Mr. Xiao";
        teachSix = "Mr. Nye";
        teachSeven = "Mr. Rogers";
        teachEight = "Mr. Ross";
        
        System.out.println("+---------------------------------------+");
        System.out.println("| 1 |            "+classOne+"|     "+teachOne+"|");
        System.out.println("| 2 |             "+classTwo+"|  "+teachTwo+"|");
        System.out.println("| 3 |         "+classThree+"|  "+teachThree+"|");
        System.out.println("| 4 |        "+classFour+"|     "+teachFour+"|");
        System.out.println("| 5 |         "+classFive+"|      "+teachFive+"|");
        System.out.println("| 6 |         "+classSix+"|       "+teachSix+"|");
        System.out.println("| 7 |      "+classSeven+"|    "+teachSeven+"|");
        System.out.println("| 8 |             "+classEight+"|      "
                +teachEight+"|");
        System.out.println("+---------------------------------------+");        
    }
}
