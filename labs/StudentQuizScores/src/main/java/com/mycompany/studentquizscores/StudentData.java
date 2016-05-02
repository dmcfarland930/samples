/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.studentquizscores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class StudentData {

    String studentName;
    int studentID = 1000;
    ConsoleIO console = new ConsoleIO();
    List<Float> lstQuizScores = new ArrayList();
    Map<Integer, StudentData> studentInfo = new HashMap();

    public StudentData() {

    }

    public void run() {

        boolean run = true;
        while (run == true) {
            displayMenu();
            String userChoice = console.getString(">");

            switch (userChoice) {
                case "1":
                    displayStudentRoster();
                    break;
                case "2":
                    addStudent();
                    break;
                case "3":
                    removeStudent();
                    break;
                case "4":
                    viewStudentQuizScores();
                    break;
                case "5":
                    viewStudentAverage();
                    break;
                case "6":
                    createScore();
                    break;
                case "7":
                    removeScore();
                    break;
                case "8":
                    viewClassAverage();
                    break;
                case "9":
                    viewTopClass();
                    break;
                case "10":
                    viewBottomClass();
                    break;
                case "q":
                case "quit":
                    run = false;
                    break;
                default:
                    System.out.println("That is an invlaid entry!");
                    break;
            }
        }

        System.out.println("Good bye!");
    }


    public void displayMenu() {

        System.out.println("What would you like to do?");
        System.out.println("====================================");
        System.out.println("    1) View Student Roster");
        System.out.println("    2) Add Student    ");
        System.out.println("    3) Remove Student");
        System.out.println("    4) View Quiz Scores");
        System.out.println("    5) View Quiz Score Avg");
        System.out.println("    6) Add Scores");
        System.out.println("    7) Remove Scores");
        System.out.println(" ---------------------------------- ");
        System.out.println("    8) View Total Quiz Score Average");
        System.out.println("    9) View Top of Class            ");
        System.out.println("    10) View Bottom of Class        ");
        System.out.println("                                    ");
        System.out.println("                            [quit]  ");
        System.out.println("====================================");

    }

    public boolean displayStudentRoster() {

        boolean isEmpty = false;
        Set<Integer> studentSet = studentInfo.keySet();

        if (studentSet.isEmpty()) {
            System.out.println("Your roster is empty!");
            isEmpty = true;
        } else {

            System.out.println("====================================");
            System.out.println(" ID #   | Student Name");
            System.out.println("------------------------------------");
            for (Integer studentID : studentSet) {

                StudentData objectStudent = studentInfo.get(studentID);
                String studNames = objectStudent.getStudentName();
                System.out.println(" " + studentID + "   | " + studNames);
            }
            System.out.println("====================================");
        }
        return isEmpty;
    }

    public void addStudent() {
        StudentData objectStudent = new StudentData();
        System.out.println("Enter the first name for your student. Enter 'q' to cancel.");
        String firstName = console.getString(">");
        if (firstName.equals("q")) {
            //do nothing
        } else {
            System.out.println("Enter the last name of your student.");
            String lastName = console.getString(">");

            String fullName = firstName + " " + lastName;
            studentID++;
            objectStudent.setStudentName(fullName);
            System.out.println(fullName + " added to the roster.");
            studentInfo.put(studentID, objectStudent);
        }

    }

    public void removeStudent() {

        boolean isEmpty;
        Set<Integer> studentSet = studentInfo.keySet();

        isEmpty = displayStudentRoster();

        while (!isEmpty) {
            System.out.println("Enter the ID for student you would like to remove.");

            int studID = console.getInteger(">", "That is an invalid entry.");

            if (studentSet.contains(studID)) {

                studentInfo.remove(studID);
                break;

            } else {
                System.out.println("That student ID does not exist.");
            }
        }

    }

    public void createScore() {

        boolean run = true;
        boolean isEmpty;
        String userInput;
        Set<Integer> studentSet = studentInfo.keySet();
        int studID = 0;

        isEmpty = displayStudentRoster();
        while (run == true && !isEmpty) {

            boolean valid = false;

            while (!valid) {
                System.out.println("Enter the ID of a student to add their quiz scores. Enter 'q' to quit");

                userInput = console.getString(">");

                if (userInput.equals("q")) {

                    run = false;
                    valid = true;

                } else {

                    try {
                        studID = Integer.parseInt(userInput);
                        valid = true;
                    } catch (Exception ex) {

                        System.out.println("That entry is invalid.");
                    }

                }
            }

            if (studentSet.contains(studID)) {
                while (run == true) {
                    StudentData objectStudent = studentInfo.get(studID);
                    String studNames = objectStudent.getStudentName();
                    System.out.println("\nEnter your quiz score for " + studNames + ". Enter 'q' to quit.");
                    userInput = console.getString(">");
                    if (userInput.equals("q")) {
                        run = false;
                    } else {
                        try {

                            Float quizGrade = Float.parseFloat(userInput);
                            objectStudent.addScoreToList(quizGrade, objectStudent);

                        } catch (Exception ex) {

                            System.out.println("That entry is invalid");

                        }
                    }

                }
            } else if (run == true) {
                System.out.println("That student does not exist.");
            }
        }

    }

    public void removeScore() {
        boolean run = true;
        boolean isEmpty;
        String userInput;
        Set<Integer> studentSet = studentInfo.keySet();
        StudentData objectStudent = new StudentData();
        int studID = 0;

        isEmpty = displayStudentRoster();
        while (run == true && !isEmpty) {

            boolean valid = false;

            while (!valid) {
                System.out.println("Enter the ID of a student to remove their quiz scores. Enter 'q' to quit");

                userInput = console.getString(">");

                if (userInput.equals("q")) {

                    run = false;
                    valid = true;

                } else {

                    try {
                        studID = Integer.parseInt(userInput);
                        valid = true;
                    } catch (Exception ex) {

                        System.out.println("That entry is invalid.");
                    }

                }
            }

            objectStudent = studentInfo.get(studID);
            String studNames = objectStudent.getStudentName();
            List<Float> scoreList = objectStudent.getStudentQuizScores();

            if (studentSet.contains(studID) && !scoreList.isEmpty()) {

                while (run == true) {

                    if (scoreList.isEmpty()) {
                        System.out.println("There are no more scores.");
                        break;
                    }
                    System.out.print(studNames + "'s quiz scores: ");
                    for (Float scores : scoreList) {
                        System.out.print(scores + " ");
                    }
                    System.out.println("Enter your quiz score to remove. Enter 'q' to quit.");
                    userInput = console.getString(">");

                    if (userInput.equals("q")) {
                        run = false;
                    } else {

                        try {

                            Float quizGrade = Float.parseFloat(userInput);

                            if (!scoreList.contains(quizGrade)) {
                                System.out.println("That score does not exist!");
                            } else {
                                objectStudent.removeScoreFromList(quizGrade, objectStudent);
                            }

                        } catch (Exception ex) {

                            System.out.println("That entry is invalid");

                        }
                    }

                }
            } else if (run == true) {
                if (scoreList.isEmpty()) {
                    System.out.println("There are no scores to remove!");
                } else {
                    System.out.println("That student does not exist.");
                }
            }
            break;
        }

    }

    public void viewStudentQuizScores() {

        boolean isEmpty;
        Set<Integer> studentSet = studentInfo.keySet();

        isEmpty = displayStudentRoster();
        while (!isEmpty) {
            System.out.println("Enter the ID of student you to view their scores.");
            int studID = console.getInteger(">", "That is an invalid entry!");

            if (studentSet.contains(studID)) {

                StudentData objectStudent = studentInfo.get(studID);
                String studNames = objectStudent.getStudentName();
                List<Float> scoreList = objectStudent.getStudentQuizScores();

                if (scoreList.isEmpty()) {
                    System.out.println(studNames + " has no grades to view!");
                } else {
                    System.out.print(studNames + "'s quiz scores: ");
                    for (Float scores : scoreList) {
                        System.out.print(scores + " ");
                    }
                }
            } else {
                System.out.println("That student does not exist.");
            }
            break;
        }

    }

    public void viewStudentAverage() {

        boolean isEmpty;
        Set<Integer> studentNameSet = studentInfo.keySet();

        isEmpty = displayStudentRoster();

        while (!isEmpty) {
            System.out.println("Enter the ID of the student to view their average.");
            int studID = console.getInteger(">", "That is an invalid entry.");

            if (studentNameSet.contains(studID)) {

                StudentData objectStudent = studentInfo.get(studID);
                List<Float> scoreList = objectStudent.getStudentQuizScores();
                String studName = objectStudent.getStudentName();
                float average = calculateAverageGrades(scoreList);

                if (scoreList.isEmpty()) {
                    System.out.println(studName + " has no grades to view!");
                } else {
                    System.out.println(studName + "'s average Quiz Score is: " + average);

                }

            } else {
                System.out.println("That student does not exist.");
            }
            break;
        }
    }

    public String getStudentName() {
        return this.studentName;

    }

    public void addScoreToList(float quizScore, StudentData studentObject) {

        //Get the desired Student Object by passing in the studentName as the Key
        //Get the current score list for the student (based off the key)
        List<Float> scoreList = new ArrayList();
        scoreList = studentObject.getStudentQuizScores();

        //Add a new score to the list
        scoreList.add(quizScore);

        //Set the score list to the Object's <List> value (update the object list)
        setQuizScore(scoreList);

    }

    public void removeScoreFromList(float quizScore, StudentData studentObject) {

        //Get the desired Student Object by passing in the studentName as the Key
        //Get the current score list for the student (based off the key)
        List<Float> scoreList = new ArrayList();
        scoreList = studentObject.getStudentQuizScores();

        //Add a new score to the list
        scoreList.remove(quizScore);

        //Set the score list to the Object's <List> value (update the object list)
        setQuizScore(scoreList);

    }

    public void viewClassAverage() {

        float sumOfAverages = 0;
        float classAverage;

        List<Float> averageList = createAveragesList();

        for (Float quizAverages : averageList) {

            sumOfAverages += quizAverages;

        }

        classAverage = sumOfAverages / averageList.size();

        if (Float.isNaN(classAverage)) {
            System.out.println("You have no grades to view!");
        } else {
            System.out.println("The class average is: " + classAverage);
        }
    }

    public void viewTopClass() {

        float highScore = Float.MIN_VALUE;
        Set<Integer> studentSet = studentInfo.keySet();
        List<Float> averageList = createAveragesList();
        for (Float average : averageList) {

            if (average >= highScore) {

                highScore = average;
            }

        }

        if (highScore == Float.MIN_VALUE) {
            System.out.println("There are no grades to view!");
        } else {
            System.out.println("The highest quiz average is: " + highScore);

            System.out.println("***TOP GUN***");
            for (Integer studID : studentSet) {

                StudentData objectStudent = studentInfo.get(studID);
                List<Float> scoreList = objectStudent.getStudentQuizScores();
                String studName = objectStudent.getStudentName();
                float averages = calculateAverageGrades(scoreList);

                if (averages == highScore) {
                    System.out.println(studName);
                }
            }
        }
    }

    public void viewBottomClass() {

        float lowScore = Float.MAX_VALUE;
        Set<Integer> studentSet = studentInfo.keySet();
        List<Float> averageList = createAveragesList();

        for (Float average : averageList) {

            if (average <= lowScore) {

                lowScore = average;
            }

        }

        if (lowScore == Float.MAX_VALUE) {
            System.out.println("There are no grades to view!");
        } else {
            System.out.println("The lowest quiz average is: " + lowScore);

            System.out.println("***BOTTOM OF CLASS***");
            for (Integer studID : studentSet) {

                StudentData objectStudent = studentInfo.get(studID);
                List<Float> scoreList = objectStudent.getStudentQuizScores();
                String studName = objectStudent.getStudentName();
                float averages = calculateAverageGrades(scoreList);

                if (averages == lowScore) {
                    System.out.println(studName);
                }
            }
        }
    }

    public List createAveragesList() {

        float average;

        Set<Integer> studentSet = studentInfo.keySet();
        List<Float> averageList = new ArrayList();
        for (Integer studName : studentSet) {

            float total = 0;
            StudentData objectStudent = studentInfo.get(studName);

            List<Float> quizScoreList = objectStudent.getStudentQuizScores();

            for (Float quizScores : quizScoreList) {

                total += quizScores;
            }

            average = total / quizScoreList.size();
            averageList.add(average);

        }

        return averageList;
    }

    public List<Float> getStudentQuizScores() {
        return this.lstQuizScores;
    }

    public void setQuizScore(List<Float> studentScoreList) {

        this.lstQuizScores = studentScoreList;
    }

    public void setStudentName(String fullName) {

        this.studentName = fullName;
    }

    public Map getStudentInfo() {

        return this.studentInfo;
    }

    public float calculateAverageGrades(List<Float> scoreList) {

        float total = 0;
        float average = 0;

        for (Float quizScores : scoreList) {

            total += quizScores;

        }

        average = total / scoreList.size();

        return average;

    }
}
