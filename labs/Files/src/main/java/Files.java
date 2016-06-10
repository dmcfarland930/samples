
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
/**
 *
 * @author apprentice
 */
public class Files {

    public static void main(String[] args) {

        writeFile();

        readFile();

    }

    private static void readFile() {

        Scanner sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("myFile.txt")));

            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                System.out.println(currentLine);

            }

            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            sc.close();
        }
    }

    private static void writeFile() {
        PrintWriter out = null;
        try {

            out = new PrintWriter(new FileWriter("myFile.txt"));

            out.println("this is a line in muh file.");
            out.println("this is a second line in muh file.");
            out.println("a third line.");

            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

}
