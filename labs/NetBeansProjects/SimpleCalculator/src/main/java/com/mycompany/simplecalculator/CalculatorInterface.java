/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.simplecalculator;

/**
 *
 * @author apprentice
 */
public class CalculatorInterface {

    SimpleCalculator sc = new SimpleCalculator();
    ConsoleIO console = new ConsoleIO();
    boolean calculate = true;

    public void run() {
        while (calculate == true) {
            //recieve userInput
            console.readString("What would you like to do?");
            String userInput = selectOperation();
            if(userInput.equals("q")||userInput.equals("quit")){
                calculate = false;
            }else{
            double result = navigateUserInput(userInput);
            console.readString(""+result);
            calculate = console.yesCheck("\nWould you like to do another calculation?\n",
                    "That is an invalid entry!");
            }
            
        }
            console.readString("Thanks! Come again!");
    }

    public String selectOperation() {
        boolean valid = false;
        String userInput = "";
        while (!valid) {
            userInput = console.getString("1) Add\n"
                    + "2) Subtract\n"
                    + "3) Multiply\n"
                    + "4) Divide\n"
                    + "   [quit]\n>");
            switch (userInput) {
                case "add":
                case "1":
                case "subtract":
                case "2":
                case "multiply":
                case "3":
                case "divide":
                case "4":
                case "quit":
                case "q":
                    valid = true;
                    break;
                default:
                    console.readString("That is not an option!");
                    valid = false;
                    break;
            }
        }
        return userInput;
    }

    public double navigateUserInput(String userInput) {

        console.readString("\nPlease enter your two operands:\n");
        double operand1;
        double operand2;
        double result = 0;
        switch (userInput) {

            case "add":
            case "1":
                operand1 = console.getDouble("Enter operand one: ", "Invalid Entry!");
                operand2 = console.getDouble("Enter operand two: ", "Invalid Entry!");
                result = sc.addOperands(operand1, operand2);
                break;
            case "subtract":
            case "2":
                operand1 = console.getDouble("Enter operand one: ", "Invalid Entry!");
                operand2 = console.getDouble("Enter operand two: ", "Invalid Entry!");
                result = sc.subtractOperands(operand1, operand2);
                break;
            case "multiply":
            case "3":
                operand1 = console.getDouble("Enter operand one: ", "Invalid Entry!");
                operand2 = console.getDouble("Enter operand two: ", "Invalid Entry!");
                result = sc.multiplyOperands(operand1, operand2);
                break;
            case "divide":
            case "4":
                operand1 = console.getDouble("Enter operand one: ", "Invalid Entry!");
                operand2 = console.getDouble("Enter operand two: ", "Invalid Entry!");
                result = sc.divideOperands(operand1, operand2);
                break;
            default:
                console.readString("\nThat is not an option!\n");
                break;
        }
        return result;
    }

}
