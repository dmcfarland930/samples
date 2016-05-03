/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.studentquizscores.App;
import com.mycompany.dto.Quizzes;
import com.mycompany.dto.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class QuizzesDao {

    private List<Quizzes> quizzes = new ArrayList();
    private int nextId = 1;

    public QuizzesDao() {
        quizzes = decode();

        for (Quizzes myQuiz : quizzes) {
            if (myQuiz.getQuizId() == nextId) {
                nextId++;
            }

        }
    }

    public Quizzes create(Quizzes quiz) {

        quizzes = decode();
        quiz.setQuizId(nextId);
        nextId++;
        quizzes.add(quiz);

        encode();
        return quiz;
    }

    public Quizzes get(Integer id) {

        for (Quizzes myQuiz : quizzes) {
            if (myQuiz.getQuizId() == id) {
                return myQuiz;
            }
        }
        return null;
    }

    public void update(Quizzes quiz) {

        for (Quizzes myQuiz : quizzes) {
            if (myQuiz.getQuizId() == quiz.getQuizId()) {
                quizzes.remove(quiz);
                quizzes.add(quiz);
            }
        }

        encode();
    }

    public void delete(Quizzes quiz) {
        Quizzes found = null;

        for (Quizzes myQuiz : quizzes) {
            if (myQuiz.getQuizId() == quiz.getQuizId()) {
                found = myQuiz;
                break;
            }

        }

        quizzes.remove(found);
        encode();
    }

    private void encode() {

        final String TOKEN = "::";
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("quizzes.txt"));

            for (Quizzes myQuiz : quizzes) {

                out.print(myQuiz.getQuizId());
                out.print(TOKEN);
                
                out.print(myQuiz.getStudId());
                out.print(TOKEN);

                out.print(myQuiz.getScore());
                out.print(TOKEN);
                
                out.print(myQuiz.getAverage());
                out.println();
            }

            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(QuizzesDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            out.close();

        }

    }

    public List<Quizzes> decode() {

        Scanner sc = null;
        List<Quizzes> quizList = new ArrayList();

        try {

            sc = new Scanner(new BufferedReader(new FileReader("quizzes.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Quizzes myQuiz = new Quizzes();

                int id = Integer.parseInt(stringParts[0]);
                int studId = Integer.parseInt(stringParts[1]);
                double score = Double.parseDouble(stringParts[2]);
                double average = Double.parseDouble(stringParts[3]);

                myQuiz.setQuizId(id);
                myQuiz.setStudId(studId);
                myQuiz.setScore(score);
                myQuiz.setAverage(average);

                quizList.add(myQuiz);

            }

        } catch (FileNotFoundException ex) {

            Logger.getLogger(QuizzesDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            sc.close();

        }

        return quizList;

    }
}
