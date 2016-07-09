/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controllers;

import com.mycompany.dao.StudentDao;
import com.mycompany.dto.Student;

/**
 *
 * @author apprentice
 */
public class StudentController {

    private StudentDao dao = new StudentDao();

    ConsoleIO console = new ConsoleIO();

    public void run() {

        boolean runAgain = true;

        while (runAgain == true) {

            console.readString("Choose an option below.");
            console.readString("1. Add student");
            console.readString("2. Remove student");

            int selection = console.getInteger(">", "That is not an option");

            switch (selection) {
                case 1:
                    //add student
                    addStudent();
                    break;
                case 2:
                    //remove student
                    removeStudent();
                    break;
            }
        }
    }

    public void addStudent() {

        console.readString("Please enter new student's name.");
        String name = console.getString(">");

        Student myStudent = new Student();
        myStudent.setFirstName(name);

        dao.create(myStudent);
    }

    public void removeStudent() {

        console.readString("Please enter student's ID to remove.");
        int id = console.getInteger(">", "Not valid");

        
        Student myStudent = new Student();
        myStudent.setId(id);
            dao.delete(myStudent);
        }

    
}
