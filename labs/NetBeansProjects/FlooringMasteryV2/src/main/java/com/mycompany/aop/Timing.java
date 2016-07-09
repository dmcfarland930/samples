/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.aop;

import java.util.Calendar;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class Timing {

    private long timeStart;
    private long timeAfter;

    public void getTimeAtStart() {
        Calendar c = Calendar.getInstance();
        this.timeStart = c.getTimeInMillis();
    }

    public void getTimeAtEnd(JoinPoint jp) {
        
        Calendar c = Calendar.getInstance();
        String action = jp.getSignature().getName();
        this.timeAfter = c.getTimeInMillis();
        long timeTotal = this.timeAfter - this.timeStart;
        System.out.println("Time taken to "+action+": " + timeTotal + " millisec");
    }
}
