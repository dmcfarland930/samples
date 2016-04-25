/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fortunecookie;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author apprentice
 */
public class FortuneCookie {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Random generator = new Random();

        int fortune = generator.nextInt(6);
        int lotto;

        switch (fortune) {

            case 0:
                System.out.println("You love Chinese food.");
                System.out.print("Lucky Numbers: ");
                for (int i = 0; i < 6; i++) {

                    if (i == 5) {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto);
                    } else {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto + " - ");
                    }

                }
                break;
                
            case 1:
                System.out.println("Each day is new. You never know where the sun will set.");
                System.out.print("Lucky Numbers: ");
                for (int i = 0; i < 6; i++) {

                    if (i == 5) {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto);
                    } else {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto + " - ");
                    }

                }

                break;
                
            case 2:
                System.out.println("An Irish man is always lucky.");
                System.out.print("Lucky Numbers: ");
                for (int i = 0; i < 6; i++) {

                    if (i == 5) {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto);
                    } else {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto + " - ");
                    }

                }
                break;
                
            case 3:
                System.out.println("Important speeches will make you wonder.");
                System.out.print("Lucky Numbers: ");
                for (int i = 0; i < 6; i++) {

                    if (i == 5) {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto);
                    } else {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto + " - ");
                    }

                }
                break;
                
            case 4:
                System.out.println("You will meet the love of your live if you read this.");
                System.out.print("Lucky Numbers: ");
                for (int i = 0; i < 6; i++) {

                    if (i == 5) {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto);
                    } else {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto + " - ");
                    }

                }
                break;
                
            case 5:
                System.out.println("Don't look behind you.");
                System.out.print("Lucky Numbers: ");
                for (int i = 0; i < 6; i++) {

                    if (i == 5) {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto);
                    } else {
                        lotto = 1 + generator.nextInt(54);
                        System.out.print("" + lotto + " - ");
                    }

                }
                break;

        }

    }

}
