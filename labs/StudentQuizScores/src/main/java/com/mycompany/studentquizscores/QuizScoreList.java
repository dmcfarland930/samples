/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.studentquizscores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public class QuizScoreList {

    boolean go = true;
    double quizScore;
    ConsoleIO console = new ConsoleIO();
    List<Double> quizScores = new ArrayList();

    public List addQuizScores(String studentName, Map studentMap) {

        List<Double> quizGrades = (List<Double>) studentMap.get(studentName);

        go = true;
        while (go == true) {
            this.quizScore = console.getDouble("\nEnter a quiz score.\n", "That is an invalid score.\n");

            quizGrades.add(this.quizScore);
            go = console.yesCheck("Would you like to add another score for " + studentName + "?\n", "Invalid option!");
        }

        return quizGrades;
    }
    
    public List removeQuizScores(String studentName) {

        go = true;
        while (go == true) {
            this.quizScore = console.getDouble("\nEnter a quiz score.\n", "That is an invalid score\n");
            quizScores.remove(this.quizScore);
            go = console.yesCheck("Would you like to remove another score for" + studentName + "?\n", "Invalid option!");
        }
        return quizScores;

    }

    public double getQuizAverage(List<Double> quizScores) {

        
        double total = 0;
        double avg = 0;

        for (Double scores : quizScores) {

            total += scores;

        }

        avg = total / quizScores.size();

        return avg;
    }
}
