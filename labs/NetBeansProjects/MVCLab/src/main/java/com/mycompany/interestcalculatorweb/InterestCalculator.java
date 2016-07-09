/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package com.mycompany.interestcalculatorweb;

import com.mycompany.dto.GrowingInvestment;
import java.util.Scanner;
import java.text.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Daniel McFarland
 */
public class InterestCalculator {

    Scanner keyboard = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#.##");
    DecimalFormat dfMoney = new DecimalFormat("0.00");

    double investment = 0;
    double annualInterestRate = 0;
    double investAge = 0;
    String choice = "";
//    int choice = 0;
    int year = 1;
    final int QUARTERLY_COMPOUND = 4;
    final int MONTHLY_COMPOUND = 12;
    final int DAILY_COMPOUND = 365;
    double yearInterest = 0;
    double interestEarnings = 0;
    double startInvest = 0;
    List<GrowingInvestment> investList = new ArrayList();

    public void run(double investment, double annualInterestRate, double investAge, String compound) {

        this.investment = investment;
        this.annualInterestRate = annualInterestRate;
        this.investAge = investAge;
        this.choice = compound;
        //get investment
//        investment = console.zeroCheckDouble("Please enter your investment: $",
//                "Please enter an investment greater than 0.", "That entry is invalid.");

        //get interest rate
//        annualInterestRate = console.zeroCheckDouble("Please enter your annual interest rate: ",
//                "Please enter an interest rate greater than 0.", "That entry is invalid.");
        //determine choice of compound rate
        //get amount of time money stays in fund
//        investAge = console.zeroCheckDouble("How many years would you like keep "
//                + "your investment with us?: ", "Please enter an amount that is greater than 0.",
//                "That entry is invalid.");
        //display balance
        calculateEarnings();

    }

    public double interestBoost(double investment, double interestRate, double compounder) {

        double newInterestRate = interestRate / compounder;

        for (int count = 0; count < compounder; count++) {
            investment = investment * (1 + (newInterestRate) / 100);
        }
        return investment;
    }

    public void calculateEarnings() {

        
        List<GrowingInvestment> newList = new ArrayList();
        this.startInvest = this.investment;

        for (this.year = 1; this.year <= investAge; this.year++) {

            GrowingInvestment gI = new GrowingInvestment();
            switch (choice) {

                case "daily":
                    this.investment = interestBoost(this.investment, this.annualInterestRate, this.DAILY_COMPOUND);

                    this.yearInterest = this.investment - this.startInvest;
                    this.interestEarnings = this.yearInterest / this.DAILY_COMPOUND;
                    break;

                case "monthly":
                    this.investment = interestBoost(this.investment, this.annualInterestRate, this.MONTHLY_COMPOUND);

                    this.yearInterest = this.investment - this.startInvest;
                    this.interestEarnings = this.yearInterest / this.MONTHLY_COMPOUND;
                    break;

                case "yearly":
                    this.investment = interestBoost(this.investment, this.annualInterestRate, this.QUARTERLY_COMPOUND);

                    this.yearInterest = this.investment - this.startInvest;
                    this.interestEarnings = this.yearInterest / this.QUARTERLY_COMPOUND;
                    break;

                default:
                    break;
            }

            gI.setYear(df.format(this.year));
            gI.setStartInvest(dfMoney.format(this.startInvest));
            gI.setInterestEarnings(dfMoney.format(this.interestEarnings));
            gI.setYearInterest(dfMoney.format(this.yearInterest));
            displayInvestment();
            gI.setInvestment(dfMoney.format(this.investment));

            newList.add(gI);
            this.investList = newList;
        }//end display loop
    }

    public void displayInvestment() {

        System.out.println("--------------------------------------");
        System.out.println("  Year " + (this.year));
        System.out.println("--------------------------------------");
        System.out.println(" Beginning of Year Balance: $" + df.format(this.startInvest));

        switch (choice) {

            case "daily":
                System.out.println("    Interest added per day: $" + df.format(this.interestEarnings));
                break;
            case "monthly"://monthly interest
                System.out.println("  Interest added per month: $" + df.format(this.interestEarnings));
                break;
            case "yearly":
                System.out.println("Interest added per quarter: $" + df.format(this.interestEarnings));
                break;

            default:
                break;
        }//end compound message
        System.out.println("  Interest added this year: $" + df.format(this.yearInterest));
        System.out.println("       End of Year Balance: $" + df.format(this.investment) + "\n");

        this.startInvest = this.investment;

    }

    public List<GrowingInvestment> getInvestList() {
        return investList;
    }

}//end class
