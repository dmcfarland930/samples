/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.studentquizscores.App;
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
public class StudentDao {

    private List<Student> students = new ArrayList();
    private int nextId = 1000;

    public StudentDao() {
        students = decode();

        for (Student myStudent : students) {
            if (myStudent.getId() == nextId) {
                nextId++;
            }

        }
    }

    public Student create(Student student) {//create

        student.setId(nextId);

        nextId++;

        students.add(student);

        encode();
        return student;

    }

    public Student get(Integer id) {//read

        for (Student myStudent : students) {
            if (myStudent.getId() == id) {
                return myStudent;
            }
        }
        return null;
    }

    public void update(Student student) {//update

        for (Student myStudent : students) {
            if (myStudent.getId() == student.getId()) {
                students.remove(myStudent);
                students.add(student);
            }
        }

        encode();
    }

    public void delete(Student student) {//delete

        Student found = null;

        for (Student myStudent : students) {
            if (myStudent.getId() == student.getId()) {
                found = myStudent;
                break;
            }
        }

        students.remove(found);
        encode();
    }

    private void encode() {

        final String TOKEN = "::";

        PrintWriter out = null;

        try {

            out = new PrintWriter(new FileWriter("students.txt"));

            for (Student myStudent : students) {

                out.print(myStudent.getId());
                out.print(TOKEN);

                out.print(myStudent.getFirstName());
                out.print(TOKEN);

                out.print(myStudent.getLastName());
                out.println();

            }

            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            out.close();

        }

    }

    public List<Student> decode() {

        Scanner sc = null;
        List<Student> studentList = new ArrayList();

        try {

            sc = new Scanner(new BufferedReader(new FileReader("students.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split("::");

                Student myStudent = new Student();

                int id = Integer.parseInt(stringParts[0]);
                //String firstName = stringParts[1];
                //String lastName = stringParts[2];
                //String cohort = stringParts[3];

                myStudent.setId(id);
                myStudent.setFirstName(stringParts[1]);
                myStudent.setLastName(stringParts[2]);

                studentList.add(myStudent);
            }

        } catch (FileNotFoundException ex) {

            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);

        } finally {

            sc.close();

        }

        return studentList;

    }

}
