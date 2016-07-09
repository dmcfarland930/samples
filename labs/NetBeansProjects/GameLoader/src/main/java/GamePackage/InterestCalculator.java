/*
 * This program was written by Daniel McFarland
 * I hope you enjoy it!
 */
package GamePackage;

import java.util.Scanner;
import java.text.*;

/**
 *
 * @author Daniel McFarland
 */
public class InterestCalculator implements Game {

    Scanner keyboard = new Scanner(System.in);
    ConsoleIO console = new ConsoleIO();
    
    double investment = 0;
    double annualInterestRate = 0;
    double investAge = 0;
    int choice = 0;
    int year = 1;
    final int QUARTERLY_COMPOUND = 4;
    final int MONTHLY_COMPOUND = 12;
    final int DAILY_COMPOUND = 365;
    double yearInterest = 0;
    double interestEarnings = 0;
    double startInvest = 0;

    @Override
    public void run() {

        //get investment
        investment = console.zeroCheckDouble("Please enter your investment: $",
                "Please enter an investment greater than 0.", "That entry is invalid.");

        //get interest rate
        annualInterestRate = console.zeroCheckDouble("Please enter your annual interest rate: ",
                "Please enter an interest rate greater than 0.", "That entry is invalid.");

        //determine choice of compound rate
        confirmChoice();

        //get amount of time money stays in fund
        investAge = console.zeroCheckDouble("How many years would you like keep "
                + "your investment with us?: ", "Please enter an amount that is greater than 0.",
                "That entry is invalid.");

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

    public void compoundCheck() {

        String compound;

        while (this.choice < 1) {
            compound = console.getString("\nHow often will your interest will compound? \n"
                    + "     1) Daily\n"
                    + "     2) Monthly\n"
                    + "     3) Quarterly\n");

            switch (compound.toLowerCase()) {

                case "daily":
                case "1":
                    console.readString("\nYour interest will be compounded daily.\n");
                    this.choice = 1;
                    break;

                case "monthly":
                case "2":
                    console.readString("\nYour interest will be compounded monthly.\n");
                    this.choice = 2;
                    break;

                case "quarterly":
                case "3":
                    console.readString("\nYour interest will be compounded quarterly.\n");
                    this.choice = 3;
                    break;

                default:
                    console.readString("\nThat is an invalid choice.\n");
                    break;
            }//end compound message
        }

    }

    public int confirmChoice() {

        boolean confirm = false;

        if (this.annualInterestRate >= 0) {//execute when interest rate entry is > 0

            while (!confirm) {
                //loop confirming the rate of compounded interest
                this.choice = 0;

                compoundCheck();

                confirm = console.yesCheck("Is this correct? [yes/no]\n",
                        "That is not a valid entry!");

            }//end compound loop
        }//end validity of interest rate and compound
        return choice;
    }

    public void calculateEarnings() {

        this.startInvest = this.investment;

        for (this.year = 1; this.year <= investAge; this.year++) {

            switch (choice) {

                case 1:
                    this.investment = interestBoost(this.investment, this.annualInterestRate, this.DAILY_COMPOUND);

                    this.yearInterest = this.investment - this.startInvest;
                    this.interestEarnings = this.yearInterest / this.DAILY_COMPOUND;
                    break;

                case 2:
                    this.investment = interestBoost(this.investment, this.annualInterestRate, this.MONTHLY_COMPOUND);

                    this.yearInterest = this.investment - this.startInvest;
                    this.interestEarnings = this.yearInterest / this.MONTHLY_COMPOUND;
                    break;

                case 3:
                    this.investment = interestBoost(this.investment, this.annualInterestRate, this.QUARTERLY_COMPOUND);

                    this.yearInterest = this.investment - this.startInvest;
                    this.interestEarnings = this.yearInterest / this.QUARTERLY_COMPOUND;
                    break;

                default:
                    break;
            }

            displayInvestment();

        }//end display loop
    }

    public void displayInvestment() {

        DecimalFormat df = new DecimalFormat("0.00");

        console.readString("--------------------------------------");
        console.readString("  Year " + (this.year));
        console.readString("--------------------------------------");
        console.readString(" Beginning of Year Balance: $" + df.format(this.startInvest));

        switch (choice) {

            case 1:
                console.readString("    Interest added per day: $" + df.format(this.interestEarnings));
                break;
            case 2://monthly interest
                console.readString("  Interest added per month: $" + df.format(this.interestEarnings));
                break;
            case 3:
                console.readString("Interest added per quarter: $" + df.format(this.interestEarnings));
                break;

            default:
                break;
        }//end compound message
        console.readString("  Interest added this year: $" + df.format(this.yearInterest));
        console.readString("       End of Year Balance: $" + df.format(this.investment) + "\n");

        this.startInvest = this.investment;

    }

    @Override
    public String getName() {
        return "Interest Calculator";
    }

}//end class
