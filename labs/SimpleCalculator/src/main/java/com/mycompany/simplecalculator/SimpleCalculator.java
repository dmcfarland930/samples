/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.simplecalculator;

/**
 *
 * @author apprentice
 */
public class SimpleCalculator {

    ConsoleIO console = new ConsoleIO(this);
    private double result;


    public double addOperands(double operand1, double operand2) {
        result = operand1 + operand2;
        return result;
    }

    public double subtractOperands(double operand1, double operand2) {

        result = operand1 - operand2;
        return result;
    }

    public double multiplyOperands(double operand1, double operand2) {

        result = operand1 * operand2;
        return result;
    }

    public double divideOperands(double operand1, double operand2) {


        result = operand1 / operand2;
        return result;
    }

}
