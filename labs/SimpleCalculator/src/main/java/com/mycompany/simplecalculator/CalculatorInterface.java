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
    ConsoleIO console = new ConsoleIO(sc);
    boolean calculate = true;

    public void run() {
        while (calculate == true) {
            //recieve userInput
            System.out.println("What would you like to do?");
            String userInput = selectOperation();
            if(userInput.equals("q")||userInput.equals("quit")){
                calculate = false;
            }else{
            double result = navigateUserInput(userInput);
            System.out.println(result);
            calculate = console.yesCheck("\nWould you like to do another calculation?\n",
                    "That is an invalid entry!");
            }
            
        }
            System.out.println("Thanks! Come again!");
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
                    System.out.println("That is not an option!");
                    valid = false;
                    break;
            }
        }
        return userInput;
    }

    public double navigateUserInput(String userInput) {

        System.out.println("\nPlease enter your two operands:\n");
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
                System.out.println("\nThat is not an option!\n");
                break;
        }
        return result;
    }

}
