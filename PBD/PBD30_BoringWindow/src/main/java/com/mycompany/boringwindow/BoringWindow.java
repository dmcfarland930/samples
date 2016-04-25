/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.boringwindow;

import javax.swing.*;
/**
 *
 * @author apprentice
 */

//extends creates a sub class. We want a window.
public class BoringWindow extends JFrame{
    
    public static void main(String[] args) {
        
        //create a new JFrame window
        JFrame f = new BoringWindow();
        
        //set how the window will close
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        //set window dimensions
        f.setSize(300, 200);
        //let me see it
        f.setVisible(true);
    }
}
