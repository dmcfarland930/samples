/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.factorizerweb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel McFarland
 */
public class Factorizer {

    int inputNum = 0;
    int perfect = 0;
    int prime = 0;
    int primeCount = 0;
    int perfectCount = 0;
    String resultString;
    List<String> results = new ArrayList();

    public void run(int inputNum) {
        //do a modulus test and check for prime and perfect numbers
        this.inputNum = inputNum;
        this.perfect = returnPerfect();
        this.prime = returnPrime();

    }

    public int returnPerfect() {
        List<String> newList = new ArrayList();
        perfectCount = 0;
        for (int i = 1; i < this.inputNum; i++) {
            if (this.inputNum % i == 0) {
                resultString = i + "\n";
                perfectCount = perfectCount + i;
                newList.add(resultString);
            }
            
            this.results = newList;
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

    public int getInputNum() {
        return inputNum;
    }

    public int getPerfect() {
        return perfect;
    }

    public int getPrime() {
        return prime;
    }

    public int getPrimeCount() {
        return primeCount;
    }

    public int getPerfectCount() {
        return perfectCount;
    }

    public String getResultString() {
        return resultString;
    }

    public List<String> getResults() {
        return results;
    }

}//end class
