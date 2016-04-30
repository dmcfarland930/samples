/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.studentquizscores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StudentQuizScores {

    String userChoice;
    String firstName;
    String lastName;
    String studentName;
    double average;
    boolean go = true;
    boolean valid = false;
    ConsoleIO console = new ConsoleIO();
    QuizScoreList qsl = new QuizScoreList();
    Map<String, List> students = new HashMap();
    Set<String> studentNames = new HashSet();
    List quizScores = new ArrayList();

    public StudentQuizScores studentData(Map students, QuizScoreList qsl, double average){
        
       
        StudentQuizScores sqs = new StudentQuizScores();
        
        
        
        
        return sqs;
       
    }
    
    
    public void run() {

        while (0 < 1) {
            go = true;
            valid = false;

            showStudentRoster();
            userChoice = getMenuChoice();
            compareUserChoice();
            studentNames = students.keySet();

        }
    }

    public String getMenuChoice() {
        this.userChoice = console.getString("What would you like to do?\n"
                + "1) Add Student\n2) Remove Student\n3) Add Scores\n"
                + "4) Remove Scores\n5) View Scores\n"
                + "           [quit]\n");

        return this.userChoice;
    }

    public void addStudent() {

        quizScores = new ArrayList();
        this.firstName = console.getString("Enter the student's first name\n");
        this.lastName = console.getString("Enter the student's last name\n");
        this.studentName = (firstName + " " + lastName);

        students.put(studentName, quizScores);

    }

    public void removeStudent() {

        this.studentName = console.getString("Enter the name of the student you would like to remove"
                + " to your roster.\n");

        students.remove(studentName, quizScores);

    }

    public void compareUserChoice() {

        if (this.userChoice.equals("add student") || this.userChoice.equals("1")) {

            addStudent();

        } else if (this.userChoice.equals("remove student") || this.userChoice.equals("2")) {

            removeStudent();

        } else if (this.userChoice.equals("add scores") || this.userChoice.equals("3")) {
            while (!valid) {

                this.studentName = console.getString("Enter the name your student.\n");

                if (students.containsKey(this.studentName)) {

                    quizScores = qsl.addQuizScores(this.studentName, students);

                    students.put(this.studentName, quizScores);

                    valid = true;

                } else {
                    System.out.println("Student not found!");
                }
            }

        } else if (this.userChoice.equals("remove scores") || this.userChoice.equals("4")) {

            this.studentName = console.getString("Enter the name your student.\n");

            if (students.containsKey(this.studentName)) {

                quizScores = qsl.removeQuizScores(this.studentName);

                students.put(studentName, quizScores);

                go = console.yesCheck("Would you like to remove another score for " + this.studentName + "?\n", "Invalid option!");

            } else {
                System.out.println("Student not found!");
            }
        } else if (this.userChoice.equals(
                "view student scores") || this.userChoice.equals("5")) {

            while (go == true) {
                this.studentName = console.getString("Who's score would you like to see?\n");

                if (students.containsKey(this.studentName)) {
                    System.out.println("\n=========================\n"
                            + "         SCORES     \n"
                            + "     --------------     ");

                    System.out.print(this.studentName);
                    
                    List<Double> scores = this.students.get(this.studentName);
                                        
                    if(scores.isEmpty()){
                        System.out.println(" has no grades yet!"); 
                    }else{
                        System.out.print(":");
                        
                    for (Double quizScore : scores) {

                        System.out.print(" "+quizScore);

                    }
                    
                    this.average = qsl.getQuizAverage(scores);
                    System.out.println("\nAverage Score: " + this.average);
                    
                    }
                    
                    
                    
                }else if (this.studentName.equals("all")) {
                    System.out.println("\n=========================\n"
                            + "         SCORES     \n"
                            + "     --------------     ");

                    for (String keys : this.studentNames) {

                        List<Double> scores = students.get(keys);

                        System.out.print("\n" + keys + ": ");
                        for (Double quizScore : scores) {

                            System.out.print(quizScore + ", ");

                        }//end value print
                        double avgAll = qsl.getQuizAverage(scores);
                        System.out.println("Average all: " + avgAll);
                    }//end key print
                } else {
                    System.out.println("\nStudent not found!");
                }//end if else
                go = console.yesCheck("\nWould you like to view another student's score?\n", "Invalid option!");
                
            }//end loop
        }
    }

    public void showStudentRoster() {
        System.out.println("\n=========================\n"
                + "      STUDENT ROSTER     \n"
                + "     --------------     ");

        for (String keys : this.studentNames) {

            System.out.println("     " + keys);

        }
        System.out.println("=========================\n");

    }

    public void showHighQuizScores(Map students) {

        List <Map> highScores = new ArrayList();
        
        
        
        
        for (String keys : this.studentNames) {

            List<Double> scores = this.students.get(keys);

            for (Double quizScore : scores) {

                quizScore = qsl.getQuizAverage(scores);
                System.out.println("Avg: " + quizScore);
            }//end value print

        }//end key print
    }

}
