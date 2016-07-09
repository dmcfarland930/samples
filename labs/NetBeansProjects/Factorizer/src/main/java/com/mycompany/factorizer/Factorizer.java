/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.factorizer;

/**
 *
 * @author Daniel McFarland
 */
public class Factorizer{

    int inputNum = 0;
    int perfect = 0;
    int prime = 0;
    int primeCount = 0;
    int perfectCount = 0;

    
    public void run() {

        ConsoleIO console = new ConsoleIO();

        //ask user for input
        console.readString("Welcome to the Factorizer app! We're gonna have some fun!\n");

        //check for valid input
        inputNum = console.zeroCheckInt("Please enter a number that you want Factorized: ",
                "Your number must be a positive number!", "That entry is invalid.");

        //do a modulus test and check for prime and perfect numbers
        this.perfect = returnPerfect();
        this.prime = returnPrime();

        //show factors of user input number and tell the user whether or not
        //their number was prime or perfect
        resultsShow();

    }//end main

    public int returnPerfect() {
        ConsoleIO console = new ConsoleIO();
        for (int i = 1; i < this.inputNum; i++) {
            if (this.inputNum % i == 0) {
                console.readString(i + "\n");
                perfectCount = perfectCount + i;
            }
        }

        return perfectCount;
    }

    public int returnPrime() {

        for (int i = 1; i < this.inputNum; i++) {
            if (this.inputNum % i == 0) {
                primeCount++;
            }
        }

        return primeCount;
    }

    public void resultsShow() {
        ConsoleIO console = new ConsoleIO();

        if (this.perfectCount == this.inputNum) {
            console.readString(this.inputNum + " is a perfect number.\n");
        } else {
            console.readString(this.inputNum + " is not a perfect number.\n");
        }

        if (this.primeCount == 1) {
            console.readString(this.inputNum + " is a prime number.\n");
        } else {
            console.readString(this.inputNum + " is not a prime number.\n");
        }
    }

}//end class
