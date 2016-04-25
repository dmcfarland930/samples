/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.basketballscores;

/**
 *
 * @author apprentice
 */
public class Basketball {
    
    public static void main(String[] args) {
        
        int maxScore;
        int minScore;
        int avgScore = 0;
        
        int [] teamScores = {79, 100, 45, 111, 128, 98, 78, 88, 102, 92};
        
        minScore = teamScores[0];
        maxScore = teamScores[0];
        for (int num: teamScores){
            
            avgScore += num;
            
            if (maxScore < num){
                maxScore = num;
            }else if(minScore >= num){
                minScore = num;
            }else{
                
            }
        }
            avgScore /= 10;
            System.out.println("Max: " +maxScore);
            System.out.println("Min: " + minScore);
            System.out.println("Average Score: "+ avgScore);
        
        
        
    }
    
    
}
