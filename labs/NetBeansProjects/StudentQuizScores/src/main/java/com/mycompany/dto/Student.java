/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dto;

import java.util.List;

/**
 *
 * @author apprentice
 */
public class Student {
    
    Quizzes quiz = new Quizzes();
    private int id;
    private double average;
    private String firstName;
    private String lastName;
    private List<Quizzes> scores;

    public Quizzes getQuiz() {
        return quiz;
    }

    public void setQuiz(Quizzes quiz) {
        this.quiz = quiz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Quizzes> getScores() {
        return scores;
    }

    public void setScores(List<Quizzes> scores) {
        this.scores = scores;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    

    
    
}
