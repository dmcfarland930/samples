/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.PlayerDao;
import com.mycompany.dao.TeamDao;
import com.mycompany.dto.Player;
import com.mycompany.dto.Team;

/**
 *
 * @author apprentice
 */
public class BaseballController {

    ConsoleIO console = new ConsoleIO();
    TeamDao teamDao = new TeamDao();
    PlayerDao playerDao = new PlayerDao();
    Team team = new Team();
    Player player = new Player();

    public void runApp() {

        selectFeature();

        System.out.println("Good bye!");

    }

    public void selectFeature() {

        boolean run = true;

        while (run == true) {

            menuDisplay();
            String selection = console.getString(">");

            switch (selection) {

                case "1":
                    //add team
                    addTeam();
                    break;

                case "2":
                    //add player
                    break;
                case "3":
                    //view team list
                    break;
                case "4":
                    //view player list
                    break;
                case "5":
                    //trade player
                    break;
                case "6":
                    //remove player
                    break;
                case "q":
                case "quit":
                    //quit
                    run = false;
                    break;
                default:
                    System.out.println("\nThat is an invalid entry!\n");
                    break;

            }
        }
    }

    public void menuDisplay() {

        System.out.println("\n=======<BASEBALL LEAGUE>=======");
        System.out.println(" 1) Create Team");
        System.out.println(" 2) Create Player");
        System.out.println(" 3) View Team List");
        System.out.println(" 4) View Player List");
        System.out.println(" 5) Trade Players");
        System.out.println(" 6) Remove Player");
        System.out.println(" ----------------------------- ");
        System.out.println(" [quit]");
        System.out.println("===============================");

    }

    public void addTeam() {

        boolean run = true;
        boolean addAgain = true;
        boolean confirm;
        String teamName;
        String teamCity;
        String teamManager;
        String teamOwner;
        Team newTeam = new Team();

        while (run == true) {

            while (addAgain == true) {
                teamCity = console.checkEmptyString("\nEnter the team's city. Enter '0' to cancel.", "\nYou cannot leave this field blank!");

                if (teamCity.equals("0")) {
                    run = false;
                    addAgain = false;
                } else {

                    teamName = console.checkEmptyString("\nEnter the team name.", "\nYou cannot leave this field blank!");
                    teamManager = console.checkEmptyString("\nEnter the team manager.", "\nYou cannot leave this field blank!");
                    teamOwner = console.checkEmptyString("\nEnter the team owner.", "You cannot leave this field blank!");

                    newTeam.setTeamName(teamName);
                    newTeam.setTeamCity(teamCity);
                    newTeam.setTeamManager(teamManager);
                    newTeam.setTeamOwner(teamOwner);

                    showTeamInfo(newTeam);

                    confirm = console.yesCheck("Is this correct? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                    if (confirm == true) {
                        System.out.println(""+newTeam.getTeamCity().toUpperCase() + newTeam.getTeamName().toUpperCase()+" CREATED!");
                        teamDao.create(newTeam);
                    }else{
                        System.out.println("ADDITION CANCELED!");
                    }

                    addAgain = console.yesCheck("\nWould you like to add a another team? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                    if (addAgain == false) {
                        run = false;
                    }
                }
            }
        }
    }

    public void showTeamInfo(Team teamShow) {

        System.out.println("==========<TEAM INFO>==========");
        System.out.print(" " + teamShow.getTeamCity().toUpperCase());
        System.out.println(" " + teamShow.getTeamName().toUpperCase());
        System.out.println("\n Manager: " + teamShow.getTeamManager());
        System.out.println(" Owner: " + teamShow.getTeamOwner());
        System.out.println("===============================");

    }
}
