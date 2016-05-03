/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controllers;

import com.mycompany.dao.QuizzesDao;
import com.mycompany.dao.StudentDao;
import com.mycompany.dto.Quizzes;
import com.mycompany.dto.Student;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class QuizController {

    DecimalFormat df = new DecimalFormat("0.00");
    ConsoleIO console = new ConsoleIO();
    List<Quizzes> quizzes = new ArrayList();
    QuizzesDao quizDao = new QuizzesDao();

    public void viewScores() {
        StudentController sc = new StudentController();
        boolean empty;

        empty = sc.viewRoster();
        if (!empty) {
            System.out.println("====================================");
            String entry = console.getString("Please enter the student's ID to\n"
                    + "view their score. Enter 'q' to\ncancel\n"
                    + ">");
            if (!entry.equalsIgnoreCase("q")) {
                int id = Integer.parseInt(entry);
                boolean found = sc.studSearch(id);
                if (!found) {
                    System.out.println("\nThat student does not exist!\n");
                } else {
                    displayScores(id);
                }
            }
        }
    }

    public void addScore() {
        boolean add = true;
        boolean add2 = true;
        boolean empty;
        while (add == true) {
            StudentController sc = new StudentController();
            StudentDao studDao = new StudentDao();
            Quizzes myQuiz = new Quizzes();
            Student myStudent = new Student();
            empty = sc.viewRoster();
            if (!empty) {
                System.out.println("====================================");
                String entry = console.getString("Please enter the student's ID to\n"
                        + "add a score. Enter 'q' to cancel."
                        + "\n>");
                if (!entry.equalsIgnoreCase("q")) {
                    int id = Integer.parseInt(entry);
                    boolean found = sc.studSearch(id);
                    if (!found) {
                        System.out.println("\nThat student does not exist!\n");
                    } else {
                        List<Student> studentsFromFile = studDao.decode();
                        for (Student studentOnFile : studentsFromFile) {
                            int idOnFile = studentOnFile.getId();
                            while (add2 == true && idOnFile == id) {
                                String entry2 = console.getString("Please enter your student's quiz\nscore."
                                        + " Enter 'q' to cancel.\n>");
                                if (!entry2.equalsIgnoreCase("q")) {
                                    double score = Double.parseDouble(entry2);
                                    quizzes.add(myQuiz);
                                    myQuiz.setStudId(idOnFile);
                                    myQuiz.setScore(score);
                                    quizDao.create(myQuiz);
                                    myStudent.setScores(quizzes);
                                    studDao.update(myStudent);

                                    add2 = console.yesCheck("\nWould you like to add another score\n"
                                            + "for this student? [yes/no]\n>", "That is an invalid entry.");
                                } else {
                                    add2 = false;
                                }
                            }
                        }
                    }
                }
            }
            add = false;
        }
    }

    public void removeScore() {

        boolean empty;
        boolean remove = true;
        boolean remove2 = true;
        StudentController sc = new StudentController();
        while (remove == true) {
            sc.viewRoster();
            System.out.println("====================================");
            String entry = console.getString("Please enter the student's ID to\n"
                    + "remove their score. Enter 'q' to\ncancel\n"
                    + ">");
            if (!entry.equalsIgnoreCase("q")) {
                int id = Integer.parseInt(entry);
                boolean found = sc.studSearch(id);
                if (!found) {
                    System.out.println("\nThat student does not exist!\n");
                } else {
                    while (remove2 == true) {
                        empty = displayScores(id);
                        System.out.println("====================================");
                        if (!empty) {
                            String entry2 = console.getString("Enter the Quiz Number to remove the\n"
                                    + "score. Enter 'q' to cancel.\n"
                                    + ">");
                            if (!entry2.equalsIgnoreCase("q")) {
                                int quizIdEntry = Integer.parseInt(entry2);
                                boolean found2 = scoreSearch(quizIdEntry);
                                if (!found2) {
                                    System.out.println("\nThat quiz does not exist!\n");
                                } else {
                                    Quizzes myQuiz = new Quizzes();
                                    myQuiz.setQuizId(quizIdEntry);
                                    quizDao.delete(myQuiz);

                                    System.out.println("\n'Quiz" + quizIdEntry + "' removed.\n");
                                    remove2 = console.yesCheck("Would you like to remove another\nscore"
                                            + " for this student? [yes/no]\n>", "That is an invalid entry.");
                                }
                            } else {
                                remove2 = false;
                            }

                        }else{
                            break;
                        }
                    }
                }
            }
            remove = false;
        }

    }

    public boolean displayScores(int studId) {

        boolean empty = false;
        boolean found = false;
        int id = studId;
        StudentDao studDao = new StudentDao();

        List<Student> studentsFromFile = studDao.decode();

        for (Student studentOnFile : studentsFromFile) {

            int idOnFile = studentOnFile.getId();
            if (id == idOnFile) {
                List<Quizzes> quizzes = quizDao.decode();
                String studFName = studentOnFile.getFirstName();
                String studLName = studentOnFile.getLastName();
                System.out.println("====================================");
                System.out.println(" ID #   | " + studFName + " " + studLName + "'s Scores");
                System.out.println("------------------------------------");
                String studFNameCap = studFName.toUpperCase();
                String studLNameCap = studLName.toUpperCase();

                found = scoreSearch(idOnFile);
                if (!found) {

                    System.out.println("\n   " + studFNameCap + " " + studLNameCap + " HAS NO SCORES!\n");
                    empty = true;
                }

                for (Quizzes myQuiz : quizzes) {
                    int quizStudId = myQuiz.getStudId();
                    int quizId = myQuiz.getQuizId();
                    if (quizStudId == id) {
                        double score = myQuiz.getScore();
                        System.out.println(" Quiz " + quizId + " | " + score);
                    }

                }

            }
        }
        return empty;
    }

    public boolean scoreSearch(int idEntry) {

        boolean found = false;
        int idPass = idEntry;

        List<Quizzes> quizzesFromFile = quizDao.decode();
        for (Quizzes myQuiz : quizzesFromFile) {
            int quizId = myQuiz.getQuizId();
            if (quizId == idPass) {
                found = true;
                break;
            }
        }
        return found;
    }

    public void viewAverage() {

        StudentDao studDao = new StudentDao();
        boolean empty;
        boolean view = true;
        StudentController sc = new StudentController();
        while (view == true) {
            empty = sc.viewRoster();
            if (!empty) {
                System.out.println("====================================");
                String entry = console.getString("Please enter the student's ID to\n"
                        + "view their average. Enter 'q' to\ncancel\n"
                        + ">");
                if (!entry.equalsIgnoreCase("q")) {
                    int id = Integer.parseInt(entry);
                    boolean found = sc.studSearch(id);
                    if (!found) {
                        System.out.println("\nThat student does not exist!\n");
                    } else {
                        List<Student> studentsFromFile = studDao.decode();

                        for (Student studentOnFile : studentsFromFile) {

                            int idOnFile = studentOnFile.getId();
                            if (idOnFile == id) {
                                double average = calculateAverage(id);
                                String studFName = studentOnFile.getFirstName();
                                String studLName = studentOnFile.getLastName();
                                System.out.println("====================================");
                                System.out.println("  " + studFName + " " + studLName + "'s Average: " + df.format(average));

                            }
                        }
                    }
                }
            }
            view = false;
        }

    }

    public void viewClassAverage() {

        StudentDao studDao = new StudentDao();
        List<Student> students = studDao.decode();
        if (students.isEmpty()) {
            System.out.println("====================================");
            System.out.println(" ID #   | Student Name");
            System.out.println("\n        YOUR ROSTER IS EMPTY!\n");
        }

        double average = quizDao.calculateClassAverage();
        System.out.println("====================================");
        System.out.println("Class Average: " + df.format(average));
        System.out.println("------------------------------------");

    }

    public void viewTopClass() {

        double highScore = Double.MIN_VALUE;

        StudentDao studDao = new StudentDao();
        List<Student> students = studDao.decode();

        for (Student studentOnFile : students) {
            int idOnFile = studentOnFile.getId();

            double average = calculateAverage(idOnFile);

            if (average >= highScore) {

                highScore = average;
            }
        }
        if (highScore == Double.MIN_VALUE) {
            System.out.println("\nThere are no averages to view!\n");
        } else {
            System.out.println("====================================");
            System.out.println(" The highest quiz average is: " + df.format(highScore));
            System.out.println("====================================");
            System.out.println("         ***TOP OF CLASS***");
            System.out.println("------------------------------------");
            for (Student stud : students) {

                int id = stud.getId();
                double averages = calculateAverage(id);
                String studFName = stud.getFirstName();
                String studLName = stud.getLastName();

                if (averages == highScore) {
                    System.out.println("        " + studFName + " " + studLName);
                }
            }
        }

    }

    public void viewBottomClass() {

        double highScore = Double.MAX_VALUE;

        StudentDao studDao = new StudentDao();
        List<Student> students = studDao.decode();

        for (Student studentOnFile : students) {
            int idOnFile = studentOnFile.getId();

            double average = calculateAverage(idOnFile);

            if (average <= highScore) {

                highScore = average;
            }
        }
        if (highScore == Double.MAX_VALUE) {
            System.out.println("\nThere are no averages to view!\n");
        } else {
            System.out.println("====================================");
            System.out.println(" The lowest quiz average is: " + df.format(highScore));
            System.out.println("====================================");
            System.out.println("        ***BOTTOM OF CLASS***");
            System.out.println("------------------------------------");
            for (Student stud : students) {

                int id = stud.getId();
                double averages = calculateAverage(id);
                String studFName = stud.getFirstName();
                String studLName = stud.getLastName();

                if (averages == highScore) {
                    System.out.println("        " + studFName + " " + studLName);
                }
            }
        }

    }

    public double calculateAverage(int idEntry) {

        StudentDao studDao = new StudentDao();
        List<Quizzes> quizzes = quizDao.decode();
        List<Student> students = studDao.decode();
        List<Double> scores = new ArrayList();

        double total = 0;
        double average;

        for (Student studentOnFile : students) {
            int idOnFile = studentOnFile.getId();

            if (idOnFile == idEntry) {

                for (Quizzes aQuiz : quizzes) {
                    int quizStudId = aQuiz.getStudId();
                    if (quizStudId == idOnFile) {
                        double score = aQuiz.getScore();
                        scores.add(score);
                    }
                }
                for (Double gotScore : scores) {

                    total += gotScore;
                }
            }
        }
        average = total / scores.size();
        return average;
    }
}
