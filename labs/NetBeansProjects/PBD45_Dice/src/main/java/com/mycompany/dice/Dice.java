/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dice;

import java.util.Random;

/**
 *
 * @author apprentice
 */
public class Dice {

    public static void main(String[] args) {

        Random diceRoll = new Random();

        int dice1, dice2, diceTotal;

        dice1 = diceRoll.nextInt(6) + 1;
        dice2 = diceRoll.nextInt(6) + 1;
        diceTotal = dice1 + dice2;

        System.out.println("Here come the dice!");
        System.out.println("Roll #1: " + dice1);
        System.out.println("Roll #2: " + dice2);
        System.out.println("  Total: " + diceTotal);

    }
}
