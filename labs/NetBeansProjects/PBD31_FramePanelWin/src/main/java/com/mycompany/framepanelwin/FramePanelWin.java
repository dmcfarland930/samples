/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.framepanelwin;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author apprentice
 */

//This program makes a sort of canvas for the paint component
public class FramePanelWin {
    
    public static void main(String[] args) {
        Frame613 f = new Frame613();
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
}

//Creates the actual frame named Frame613
class Frame613 extends JFrame
{
    public Frame613(){
        setTitle("613 rocks!");
        setSize(300, 200);
        setLocation(100, 200);
        
        Panel613 panel = new Panel613();
        Container cp = getContentPane();
        cp.add(panel);
        
    }
}

class Panel613 extends JPanel{
    //let's draw a message
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        g.drawString("Hi", 75, 100);
    }
}