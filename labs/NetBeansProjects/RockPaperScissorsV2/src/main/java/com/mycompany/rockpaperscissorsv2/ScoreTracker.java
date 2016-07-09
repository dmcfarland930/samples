/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.rockpaperscissorsv2;

/**
 *
 * @author apprentice
 */
public class ScoreTracker {
    
    private final RockPaperScissors4 rockPaperScissors;
    
    public ScoreTracker(RockPaperScissors4 rockPaperScissors){
        this.rockPaperScissors = rockPaperScissors;
    
}

    public int getWins(int result, int wins) {
        if (result == 1) {
            wins++;
        }
        return wins;
    }

    public int getLosses(int result, int losses) {
        if (result == 2) {
            losses++;
        }
        return losses;
    }

    public int getTies(int result, int ties) {
        if (result == 3) {
            ties++;
        }
        return ties;
    }
}
