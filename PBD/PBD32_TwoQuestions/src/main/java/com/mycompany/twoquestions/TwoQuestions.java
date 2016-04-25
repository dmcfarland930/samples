package com.mycompany.twoquestions;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class TwoQuestions {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String choice1;
        String choice2;
        String object = "";
        int answer = 0;
        boolean valid;

        //Welcome Message
        System.out.println("TWO QUESTIONS!");
        System.out.println("Think of an object, and I'll try to guess it.");
        valid = false;

        while (!valid) {
            //Question 1
            System.out.println("");
            System.out.println("Question 1) Is it an animal, vegetable, or "
                    + " mineral?");
            choice1 = keyboard.nextLine();

            switch (choice1) {

                case "animal":
                    answer = 1;
                    valid = true;
                    break;

                case "vegetable":
                    answer = 2;
                    valid = true;
                    break;

                case "mineral":
                    answer = 3;
                    valid = true;
                    break;

                default:
                    System.out.println("");
                    System.out.println("That is not an option!");
                    break;

            }//conditional

        }//while loop

        valid = false;
        while (!valid) {
            //Question 2
            System.out.println("");
            System.out.println("Question 2) Is it bigger than a bread box?");
            choice2 = keyboard.nextLine();

            switch (choice2) {
                
                case "yes":
                    
                    switch (answer) {
                        case 1:
                            object = "kangaroo";
                            break;

                        case 2:
                            object = "the world's biggest baby corn";
                            break;
                        case 3:
                            object = "Jupiter";
                            break;
                    }
                    valid = true;
                    break;
                    
                case "no":
                    
                    switch (answer) {
                        case 1:
                            object = "rat";
                            break;

                        case 2:
                            object = "corn on the cob";
                            break;
                        case 3:
                            object = "a pebble";
                            break;

                    }
                    valid = true;
                    break;
                    
                default:
                    System.out.println("");
                    System.out.println("That is not an option!");
                    break; //conditional
            }

        }//while loop
        System.out.println("");
        System.out.println("My guess is that you're thinking of a "+object);
        System.out.println("I would ask you if I'm right, but I don't really care.");
    }

}
