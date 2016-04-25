/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.interestcalculator;

import java.util.Scanner;
import java.text.*;

/**
 *
 * @author Daniel McFarland
 */
public class InterestCalculator {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");

        double investment = 0;
        double annualInterestRate = 0;
        double startInvest;
        double yearInterest = 0;
        double interestEarnings = 0;

        double investAge = 0;
        int choice = 0;

        String confirm = "";
        String compound;

        //get investment
        investment = zeroCheck(investment, "Please enter your investment: $", "That is an invalid investment.",
                "Please enter an investment greater than 0.");
        startInvest = investment;

        //get interest rate
        while (annualInterestRate <= 0) {
            System.out.print("Please enter your annual interest rate: ");
            annualInterestRate = keyboard.nextDouble();
            if (annualInterestRate <= 0) {
                System.out.println("");
                System.out.println("That is an invalid interest rate.");
                System.out.println("Please enter an interest rate greater than 0.");
                System.out.println("");
                
            } else {//execute when interest rate entry is > 0

                while (!(confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y"))) {
                //loop confirming the rate of compounded interest

                    while (choice < 1) {
                        System.out.println("");
                        System.out.println("How often will your interest will compound? \n"
                                + "     1) Daily\n"
                                + "     2) Monthly\n"
                                + "     3) Quarterly");
                        compound = keyboard.next();
                        System.out.println("");

                        switch (compound.toLowerCase()) {

                            case "daily":
                            case "1":
                                System.out.println("Your interest will be compounded daily.");
                                choice = 1;
                                break;

                            case "monthly":
                            case "2":
                                System.out.println("Your interest will be compounded monthly.");
                                choice = 2;
                                break;

                            case "quarterly":
                            case "3":
                                System.out.println("Your interest will be compounded quarterly.");
                                choice = 3;
                                break;

                            default:
                                System.out.println("That is an invalid choice.");
                                break;
                        }//end compound message
                    }

                    System.out.println("Is this correct? [yes/no]");
                    confirm = keyboard.next();

                    switch (confirm.toLowerCase()) {

                        case "yes":
                        case "y":
                            System.out.println("");
                            break;

                        case "no":
                        case "n":
                            choice = 0;//reset coumpound choice 
                            break;

                        default:
                            System.out.println("");
                            System.out.println("Not a valid entry!");
                            System.out.println("");
                            break;

                    }//end of Y/N switch
                }//end compound loop
            }//end validity of interest rate and compound
        }//end interest rate loop

        //get amount of time money stays in fund
        investAge = zeroCheck(investAge, "How many years would you like keep your invest with us?: ",
                "The length of investment is invalid.", "Please enter an amount that is greater than 0.");
        System.out.println("------------------------------");

        //display balance
        for (int year = 1; year <= investAge; year++) {

            switch (choice) {

                case 1:
                    investment = interestBoost(investment, annualInterestRate, 365);

                    yearInterest = investment - startInvest;
                    interestEarnings = yearInterest / 365;
                    break;

                case 2:
                    investment = interestBoost(investment, annualInterestRate, 12);

                    yearInterest = investment - startInvest;
                    interestEarnings = yearInterest / 12;
                    break;

                case 3:
                    investment = interestBoost(investment, annualInterestRate, 4);

                    yearInterest = investment - startInvest;
                    interestEarnings = yearInterest / 4;
                    break;

                default:
                    break;
            }

            System.out.println("Year " + (year));
            System.out.println("--------------------------------------");
            System.out.println(" Beginning of Year Balance: $" + df.format(startInvest));

            switch (choice) {

                case 1:
                    System.out.println("    Interest added per day: $" + df.format(interestEarnings));
                    break;
                case 2://monthly interest
                    System.out.println("  Interest added per month: $" + df.format(interestEarnings));
                    break;
                case 3:
                    System.out.println("Interest added per quarter: $" + df.format(interestEarnings));
                    break;

                default:
                    break;
            }//end compound message
            System.out.println("  Interest added this year: $" + df.format(yearInterest));
            System.out.println("       End of Year Balance: $" + df.format(investment));
            System.out.println("");
            System.out.println("--------------------------------------");
            startInvest = investment;

        }//end display loop
    }//end main

    public static double interestBoost(double investment, double interestRate, double compounder) {
        double newInterestRate = interestRate / compounder;
        double finalInterestRate = newInterestRate;
        for (int count = 0; count < compounder; count++) {
            investment = investment * (1 + (finalInterestRate) / 100);
        }
        return investment;
    }

    public static double zeroCheck(double query, String message1, String message2, String message3) {

        Scanner keyboard = new Scanner(System.in);

        while (query <= 0) {

            System.out.print(message1);
            query = keyboard.nextDouble();
            if (query <= 0) {
                System.out.println("");
                System.out.println(message2);
                System.out.println(message3);
                System.out.println("");

            } else {
                System.out.println("");
            }//end validity check
        }//end validity loop
        return query;
    }
}//end class
