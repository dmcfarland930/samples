package com.mycompany.statecapitals2;

/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class ConsoleIO {

    Scanner keyboard = new Scanner(System.in);
    private int integer;
    private float floater;
    private double dub;
    private String userInput;
    private String string;

    public int getInteger(String prompt, String errorMessage) {

        boolean isValid = false;

        while (!isValid) {

            System.out.print(prompt);

            userInput = keyboard.nextLine();

            try {
                integer = Integer.parseInt(userInput);
                isValid = true;

            } catch (Exception ex) {

                System.out.println(errorMessage);

            }

        }

        return integer;
    }

    public int checkMinMaxInt(String prompt, int min, int max, String errorMessageMin,
            String errorMessageMax, String errorMessage) {

        integer = min - 1;

        while (integer < min || integer > max) {

            integer = this.getInteger(prompt, errorMessage);

            if (integer < min) {

                System.out.println(errorMessageMin);
            } else if (integer > max) {

                System.out.println(errorMessageMax);
            }
        }

        return integer;

    }

    public String getString(String prompt) {

        System.out.print(prompt);
        string = keyboard.nextLine();

        return string;
    }

    public float getFloat(String prompt, String errorMessage) {

        boolean isValid = false;

        while (!isValid) {

            System.out.print(prompt);

            userInput = keyboard.nextLine();

            try {
                floater = Float.parseFloat(userInput);
                isValid = true;

            } catch (Exception ex) {

                System.out.println(errorMessage);

            }

        }

        return floater;

    }

    public float checkMinMaxFloat(String prompt, int min, int max, String errorMessage,
            String errorMessage2) {

        floater = min - 1;

        while (floater < min || floater > max) {
            System.out.print(prompt);

            floater = this.getFloat(prompt, errorMessage2);

            if (floater < min || floater > max) {

                System.out.println(errorMessage);
            }
        }

        return floater;

    }

    public double getDouble(String prompt, String errorMessage) {

        boolean isValid = false;

        while (!isValid) {

            System.out.print(prompt);

            userInput = keyboard.nextLine();

            try {
                dub = Double.parseDouble(userInput);
                isValid = true;

            } catch (Exception ex) {

                System.out.println(errorMessage);

            }

        }

        return dub;
    }

    public double checkMinMaxDouble(String prompt, double min, double max, String errorMessageMin,
            String errorMessageMax, String errorMessage) {

        dub = min - 1;

        while (dub < min || dub > max) {

            dub = this.getDouble(prompt, errorMessage);

            if (dub < min) {

                System.out.println(errorMessageMin);
            } else if (dub > max) {

               System.out.println(errorMessageMax);
            }

        }

        return dub;

    }

    public void readString(String prompt) {
        System.out.println(prompt);

    }

    public int zeroCheckInt(String message, String errorMessage, String errorMessage2) {

        integer = 0;

        while (integer <= 0) {

            integer = this.getInteger(message, errorMessage2);
            integer = Integer.parseInt(userInput);

            if (integer <= 0) {
                System.out.println("\n" + errorMessage + "\n");
            } else {
                System.out.println("");
            }
        }

        return integer;
    }

    public double zeroCheckDouble(String message, String errorMessage, String errorMessage2) {

        dub = 0;

        while (dub <= 0) {

            dub = this.getDouble(message, errorMessage2);
            dub = Double.parseDouble(userInput);

            if (dub <= 0) {
                System.out.println("\n" + errorMessage + "\n");
            } else {
                System.out.println("");
            }
        }

        return dub;
    }

    public float zeroCheckFloat(String message, String errorMessage, String errorMessage2) {

        floater = 0;

        while (floater <= 0) {

            floater = this.getFloat(message, errorMessage2);
            floater = Float.parseFloat(userInput);

            if (floater <= 0) {
                System.out.println("\n" + errorMessage + "\n");
            } else {
                System.out.println("");
            }
        }

        return floater;
    }

    public boolean yesCheck(String message1, String message2) {

        boolean valid = false;
        boolean confirm = true;

        while (!valid) {
            //console.readString("Is this correct? [yes/no]");
            String confirmChoice = this.getString(message1);

            switch (confirmChoice.toLowerCase()) {

                case "yes":
                case "y":
                    confirm = true;
                    valid = true;
                    System.out.println("");
                    break;

                case "no":
                case "n":
                    confirm = false;
                    valid = true;
                    break;

                default:
                    System.out.println(message2);
                    //console.readString("\nNot a valid entry!\n");
                    break;

            }//end of Y/N switch
        }
        return confirm;
    }

    public String checkEmptyString(String prompt, String error) {

        boolean valid = false;
        while (!valid) {

            System.out.println(prompt);
            System.out.print(">");
            string = keyboard.nextLine();
            if (string.isEmpty()) {
                System.out.println(error);

            } else {
                valid = true;
            }

        }
        return string;
    }

}
