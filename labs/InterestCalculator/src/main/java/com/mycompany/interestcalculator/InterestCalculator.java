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

        double investment = 0;
        double annualInterestRate = 0;
        double investAge = 0;

        //get investment
        investment = zeroCheck(investment, "Please enter your investment: $",
                "Please enter an investment greater than 0.");

        //get interest rate
        annualInterestRate = zeroCheck(annualInterestRate, "Please enter your annual interest rate: ",
                "Please enter an interest rate greater than 0.");

        //determine choice of compound rate
        int choice = returnChoice(annualInterestRate);

        //get amount of time money stays in fund
        investAge = zeroCheck(investAge, "How many years would you like keep "
                + "your investment with us?: ", "Please enter an amount that is greater than 0.");

        //display balance
        showMeTheMoney(investment, investAge, annualInterestRate, choice);
    }//end main
    
    
    
    
    
    
    
    
    
    
    
    
    

    public static double interestBoost(double investment, double interestRate, double compounder) {

        double newInterestRate = interestRate / compounder;

        for (int count = 0; count < compounder; count++) {
            investment = investment * (1 + (newInterestRate) / 100);
        }
        return investment;
    }

    public static double zeroCheck(double query, String message1, String message2) {

        Scanner keyboard = new Scanner(System.in);

        while (query <= 0) {

            System.out.print(message1);
            query = keyboard.nextDouble();
            if (query <= 0) {
                System.out.println("");
                System.out.println("That entry is invalid.");
                System.out.println(message2);
                System.out.println("");

            } else {
                System.out.println("");
            }//end validity check
        }//end validity loop
        return query;
    }

    public static int returnChoice(double annualInterestRate) {

        String confirm = "";

        int choice = 0;

        if (annualInterestRate >= 0) {//execute when interest rate entry is > 0

            while (!(confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y"))) {
                //loop confirming the rate of compounded interest
                choice = 0;

                choice = compoundCheck(choice);

                confirm = yesCheck(choice);

            }//end compound loop
        }//end validity of interest rate and compound
        return choice;
    }

    public static int compoundCheck(int choice) {

        Scanner keyboard = new Scanner(System.in);

        String compound;

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
        return choice;

    }

    public static String yesCheck(int choice) {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Is this correct? [yes/no]");
        String confirm = keyboard.next();

        switch (confirm.toLowerCase()) {

            case "yes":
            case "y":
                System.out.println("");
                break;

            case "no":
            case "n":
                break;

            default:
                System.out.println("");
                System.out.println("Not a valid entry!");
                System.out.println("");
                break;

        }//end of Y/N switch

        return confirm;
    }

    public static void showMeTheMoney(double investment, double investAge, double annualInterestRate, int choice) {

        final int QUARTERLY_COMPOUND = 4;
        final int MONTHLY_COMPOUND = 12;
        final int DAILY_COMPOUND = 365;
        double yearInterest = 0;
        double interestEarnings = 0;

        double startInvest = investment;

        for (int year = 1; year <= investAge; year++) {

            switch (choice) {

                case 1:
                    investment = interestBoost(investment, annualInterestRate, DAILY_COMPOUND);

                    yearInterest = investment - startInvest;
                    interestEarnings = yearInterest / DAILY_COMPOUND;
                    break;

                case 2:
                    investment = interestBoost(investment, annualInterestRate, MONTHLY_COMPOUND);

                    yearInterest = investment - startInvest;
                    interestEarnings = yearInterest / MONTHLY_COMPOUND;
                    break;

                case 3:
                    investment = interestBoost(investment, annualInterestRate, QUARTERLY_COMPOUND);

                    yearInterest = investment - startInvest;
                    interestEarnings = yearInterest / QUARTERLY_COMPOUND;
                    break;

                default:
                    break;
            }

            startInvest = cubaGooding(year, choice, startInvest, investment, yearInterest, interestEarnings);

        }//end display loop
    }

    public static double cubaGooding(int year, int choice, double startInvest, double investment, double yearInterest, double interestEarnings) {

        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("--------------------------------------");
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
        startInvest = investment;

        return startInvest;
    }
}//end class
