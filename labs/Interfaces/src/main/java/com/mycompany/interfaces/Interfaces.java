/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class Interfaces {

    public static void main(String[] args) {

        Ball ball = new Ball();

        ball.printStuff();

        Colorable ball2 = new Ball();
        Colorable tv = new Television();
        
        ball2.setColor("Green");
        tv.setColor("Black");

        List<Colorable> list = new ArrayList();
        list.add(tv);
        list.add(ball2);
        
        for(Colorable colorItem : list){
            
            printColor(colorItem);
            
        }
        
        Debuggable debuggableBall = ball;
        
        debuggableBall.logError("- I am ball error.");
        
        Colorable colorableBall = ball;
        
    }

    public static void printColor(Colorable item) {
        System.out.println("My color is " + item.getColor());

    }
}
