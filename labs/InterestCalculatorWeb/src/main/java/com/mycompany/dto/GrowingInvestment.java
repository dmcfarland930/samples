/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dto;

/**
 *
 * @author apprentice
 */
public class GrowingInvestment {
    
    String year;
    String startInvest;
    String interestEarnings;
    String yearInterest;
    String investment;

    public String getStartInvest() {
        return startInvest;
    }

    public void setStartInvest(String startInvest) {
        this.startInvest = startInvest;
    }

    public String getInterestEarnings() {
        return interestEarnings;
    }

    public void setInterestEarnings(String interestEarnings) {
        this.interestEarnings = interestEarnings;
    }

    public String getYearInterest() {
        return yearInterest;
    }

    public void setYearInterest(String yearInterest) {
        this.yearInterest = yearInterest;
    }

    public String getInvestment() {
        return investment;
    }

    public void setInvestment(String investment) {
        this.investment = investment;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
    
}
