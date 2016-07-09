/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controllers;

import com.mycompany.dao.QuizzesDao;
import com.mycompany.dao.StudentDao;
import com.mycompany.dto.Quizzes;
import com.mycompany.dto.Student;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class StudentController {

    ConsoleIO console = new ConsoleIO();
    private StudentDao studDao = new StudentDao();
    QuizzesDao quizDao = new QuizzesDao();
    List<Student> students = new ArrayList();
    QuizController qc = new QuizController();

    public void run() {

        boolean runAgain = true;

        while (runAgain == true) {

            console.readString("====================================");
            console.readString("   What would you like to do?");
            console.readString("====================================");
            console.readString("    1) View Student Roster");
            console.readString("    2) Add Student");
            console.readString("    3) Remove Student");
            console.readString("    4) View Quiz Scores");
            console.readString("    5) View Quiz Score Avg");
            console.readString("    6) Add Scores");
            console.readString("    7) Remove Scores");
            console.readString(" ---------------------------------- ");
            console.readString("    8) View Total Quiz Score Average");
            console.readString("    9) View Top of Class");
            console.readString("    10) View Bottom of Class");
            console.readString("                                    ");
            console.readString("                            [quit]");
            console.readString("====================================");

            String selection = console.getString(">");

            switch (selection) {
                case "1":
                    //view roster
                    viewRoster();
                    break;
                case "2":
                    //add student
                    addStudent();
                    break;
                case "3":
                    //remove student
                    removeStudent();
                    break;
                case "4":
                    //view score
                    qc.viewScores();
                    break;
                case "5":
                    //view quiz score average
                    qc.viewAverage();
                    break;
                case "6":
                    //add score
                    qc.addScore();
                    break;
                case "7":
                    //remove score
                    qc.removeScore();
                    break;
                case "8":
                    //calculate class average
                    qc.viewClassAverage();
                    break;
                case "9":
                    //see top of class
                    qc.viewTopClass();
                    break;
                case "10":
                    //see bottom of class
                    qc.viewBottomClass();
                    break;
                case "q":
                    //quit
                    runAgain = false;
                    break;
            }
        }
        console.readString("\n   GOOD BYE!");
    }

    public boolean viewRoster() {

        boolean empty = false;

        console.readString("====================================");
        console.readString(" ID #   | Student Name");
        console.readString("------------------------------------");
        List<Student> studentsFromFile = studDao.decode();
        if (studentsFromFile.isEmpty()) {

            console.readString("\n        YOUR ROSTER IS EMPTY!\n");
            empty = true;

        } else {

            for (Student myStudent : studentsFromFile) {

                int id = myStudent.getId();
                String firstName = myStudent.getFirstName();
                String lastName = myStudent.getLastName();

                console.readString("   " + id + " | " + firstName + " " + lastName);

            }
        }
        return empty;
    }

    public void addStudent() {
        boolean add = true;

        while (add == true) {

            console.readString("Enter the first name for your student. Enter 'q' to quit.");
            String firstName = console.getString(">");

            if (!firstName.equalsIgnoreCase("q")) {
                console.readString("Enter the last name of your student.");
                String lastName = console.getString(">");

                Student myStudent = new Student();
                myStudent.setFirstName(firstName);
                myStudent.setLastName(lastName);
                String fName = myStudent.getFirstName();
                String lName = myStudent.getLastName();

                console.readString("\n'" + fName + " " + lName + "' added.\n");
                studDao.create(myStudent);
                add = console.yesCheck("Would you like to add another student?\n"
                        + "[yes/no]\n>", "That is an invalid entry!");
            } else {
                add = false;
            }

        }
    }

    public void removeStudent() {

        boolean found;
        boolean empty;
        boolean remove = true;
        while (remove == true) {
            empty = viewRoster();
            if (!empty) {
                console.readString("====================================");
                String entry = console.getString("Please enter the student's ID to\n"
                        + "remove them from your roster.\n"
                        + "Enter 'q' to cancel.\n>");
                if (!entry.equalsIgnoreCase("q")) {
                    int id = Integer.parseInt(entry);
                    found = studSearch(id);
                    if (!found) {
                        console.readString("\nThat student does not exist!\n");
                    } else {
                        List<Student> studentsFromFile = studDao.decode();

                        List<Quizzes> quizzes = quizDao.decode();

                        for (Student myStudent : studentsFromFile) {

                            int idOnFile = myStudent.getId();

                            myStudent.setId(idOnFile);
                            String firstName = myStudent.getFirstName();
                            String lastName = myStudent.getLastName();

                            for (Quizzes myQuiz : quizzes) {
                                int quizOnFileStudId = myQuiz.getStudId();
                                if (id == quizOnFileStudId) {
                                    int quizId = myQuiz.getQuizId();
                                    myQuiz.setQuizId(quizId);
                                    quizDao.delete(myQuiz);
                                    break;
                                }
                                break;
                            }

                            if (id == idOnFile) {
                                studDao.delete(myStudent);
                                console.readString("\n'" + firstName + " " + lastName + "' removed.\n");
                            }
                        }
                    }
                }

                remove = console.yesCheck("Would you like to remove another\nstudent?"
                        + " [yes/no]\n>",
                        "That is an invalid entry!");
            } else {
                remove = false;
            }
        }
    }

    public boolean studSearch(int idEntry) {

        boolean found = false;
        int idPass = idEntry;

        List<Student> studentsFromFile = studDao.decode();
        for (Student myStudent : studentsFromFile) {
            int studId = myStudent.getId();
            if (studId == idPass) {
                found = true;
                break;
            }
        }
        return found;
    }

}
