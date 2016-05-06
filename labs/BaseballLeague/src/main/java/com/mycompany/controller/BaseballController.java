/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.PlayerDao;
import com.mycompany.dao.TeamDao;
import com.mycompany.dto.Player;
import com.mycompany.dto.Team;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class BaseballController {

    ConsoleIO console = new ConsoleIO();
    TeamDao teamDao = new TeamDao();
    PlayerDao playerDao = new PlayerDao(teamDao);

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
                    addPlayer();
                    break;
                case "3":
                    //view team list
                    viewAllTeams();
                    break;
                case "4":
                    //view player list
                    viewPlayersByTeam();
                    break;
                case "5":
                    //trade player
                    tradePlayer();
                    break;
                case "6":
                    //remove player
                    removePlayer();
                    break;
                case "7":
                    //remove team
                    removeTeam();
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
        System.out.println(" 7) Remove Team");
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

                    Team newTeam = new Team();

                    newTeam.setTeamName(teamName);
                    newTeam.setTeamCity(teamCity);
                    newTeam.setTeamManager(teamManager);
                    newTeam.setTeamOwner(teamOwner);
                    teamDao.create(newTeam);
                    showTeamInfo(newTeam);

                    confirm = console.yesCheck("\nIs this correct? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                    if (confirm == true) {
                        System.out.println("" + newTeam.getTeamCity().toUpperCase() + " " + newTeam.getTeamName().toUpperCase() + " CREATED!");

                    } else {
                        System.out.println("\nADDITION CANCELED!");
                        teamDao.delete(newTeam);
                    }

                    addAgain = console.yesCheck("\nWould you like to add a another team? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                    if (addAgain == false) {
                        run = false;
                    }
                }
            }
        }
    }

    public void addPlayer() {

        boolean run;
        boolean addAgain = true;
        boolean confirm = true;
        int playerTeamId;
        String playerFirstName;
        String playerLastName;
        String playerNumber;
        Team newTeam = new Team();
        List<Team> teamList = teamDao.decode();

        run = checkTeamsExist("\nThere are currently no teams to add players to!");

        while (run == true) {

            while (addAgain == true) {
                playerFirstName = console.checkEmptyString("\nEnter the player's first name. Enter '0' to cancel.", "\nYou cannot leave this field blank!");

                if (playerFirstName.equals("0")) {
                    run = false;
                    addAgain = false;
                } else {

                    boolean found = false;
                    playerLastName = console.checkEmptyString("\nEnter the player's last name.", "\nYou cannot leave this field blank!");
                    playerNumber = console.checkEmptyString("\nEnter the player's number.", "\nYou cannot leave this field blank!");

                    while (!found) {
                        viewAllTeams();
                        playerTeamId = console.getInteger("\nEnter the ID No. of the team you wish to add this"
                                + " player to:\n>", "That is an invalid entry!");

                        for (Team teamOnFile : teamList) {
                            if (playerTeamId == teamOnFile.getTeamId()) {
                                found = true;
                                showTeamInfo(teamOnFile);
                                confirm = console.yesCheck("\n" + playerFirstName + " " + playerLastName + " will be added to the "
                                        + teamOnFile.getTeamCity().toUpperCase() + " " + teamOnFile.getTeamName().toUpperCase() + "\n\nIs this team correct? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                                if (!confirm) {
                                    found = false;
                                    break;
                                } else {
                                    newTeam = teamOnFile;
                                    break;
                                }
                            }
                        }
                        if (!found && (confirm == true)) {
                            System.out.println("\nNo teams were found by that ID No.");
                        }
                    }

                    Player newPlayer = new Player();
                    newPlayer.setPlayerFirstName(playerFirstName);
                    newPlayer.setPlayerLastName(playerLastName);
                    newPlayer.setPlayerNumber(playerNumber);
                    newPlayer.setTeamId(newTeam.getTeamId());
                    newPlayer.setPlayerTeamName(newTeam.getTeamName());
                    newPlayer.setPlayerTeamCity(newTeam.getTeamCity());

                    newPlayer.setTeam(newTeam);

                    playerDao.create(newPlayer, newTeam);

                    showPlayerInfo(newPlayer);

                    confirm = console.yesCheck("\nIs this correct? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                    if (confirm == true) {
                        System.out.println("" + newPlayer.getPlayerFirstName().toUpperCase() + " " + newPlayer.getPlayerLastName().toUpperCase() + " ADDED TO THE " + newTeam.getTeamCity().toUpperCase() + " " + newTeam.getTeamName().toUpperCase() + "!");

                    } else {
                        System.out.println("\nADDITION CANCELED!");
                        playerDao.delete(newPlayer);
                    }

                    addAgain = console.yesCheck("\nWould you like to create another player? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                    if (addAgain == false) {
                        run = false;
                    }
                }
            }
        }
    }

    public void viewAllTeams() {

        boolean exists;

        exists = checkTeamsExist("\nThere are currently no teams to view!");

        if (exists == true) {
            List<Team> teamList = teamDao.decode();

            for (Team teamOnFile : teamList) {

                showTeamInfo(teamOnFile);

            }
        }

    }

    public void removePlayer() {

        boolean run;
        boolean removeAgain = true;
        boolean confirm = true;
        boolean found;
        int delPlayerId;
        String playerLastName;
        Player delPlayer = new Player();
        List<Player> playerList = playerDao.decode();

        
        run = checkTeamsExist("\nThere are currently no teams to remove players from!");

        
        while (run == true) {

            while (removeAgain == true) {
                playerLastName = console.checkEmptyString("\nEnter last name of the player you'd like to remove. Enter '0' to cancel.", "\nYou cannot leave this field blank!");

                if (playerLastName.equals("0")) {
                    run = false;
                    removeAgain = false;

                } else {

                    found = searchPlayer(playerLastName);

                    if (found == true) {

                        delPlayerId = console.getInteger("\nEnter the ID No. of the player you wish to remove.\n>", "That is not a valid ID entry!");

                        for (Player delPlayerList : playerList) {

                            int foundId = delPlayerList.getPlayerId();

                            if (delPlayerId == foundId) {

                                confirm = console.yesCheck("\n" + delPlayerList.getPlayerFirstName().toUpperCase() + " " + delPlayerList.getPlayerLastName().toUpperCase() + " will be removed."
                                        + " Is this correct? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                                delPlayer = delPlayerList;
                                break;
                            }

                        }
                    }

                    if (confirm == true && found == true) {

                        playerDao.delete(delPlayer);
                        System.out.println("\n" + delPlayer.getPlayerFirstName()
                                + " " + delPlayer.getPlayerLastName() + " REMOVED FROM LEAGUE!");
                        removeAgain = console.yesCheck("\nWould you like to remove another player? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                        if (removeAgain == false) {
                            run = false;
                        } else if (found == true) {
                            System.out.println("\nREMOVAL CANCELLED!");
                        }

                    }
                }
            }
        }
    }

    public void viewPlayersByTeam() {

        boolean run;
        boolean viewAgain = true;
        int viewTeamId;

        run = checkTeamsExist("\nThere are currently no teams to view players from!");

        while (run == true) {

            while (viewAgain == true) {

                viewAllTeams();

                viewTeamId = console.getInteger("\nEnter the team ID No. to view their roster. Enter '0' to cancel.\n>", "\nThat is an invalid entry!");

                if (viewTeamId == 0) {
                    run = false;
                    viewAgain = false;

                } else {

                    searchRoster(viewTeamId);

                    viewAgain = console.yesCheck("\nWould you like to search another roster? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                    if (viewAgain == false) {
                        run = false;
                    }
                }
            }
        }
    }

    public void tradePlayer() {

        boolean run;
        boolean tradeAgain = true;
        boolean confirm = false;
        boolean found;
        int recTeamId;
        int tradePlayerId;
        String tradeFirstName;
        String tradeLastName;
        String recTeamCity;
        String recTeamName;
        Player playerFound = new Player();
        Team teamFound = new Team();

        
        run = checkPlayersExist("\nThere are currently no players to trade!");

        
        while (run == true) {

            while (tradeAgain == true) {

                tradeLastName = console.checkEmptyString("\nEnter last name of the player you'd like to trade. Enter '0' to cancel.", "\nYou cannot leave this field blank!");

                if (tradeLastName.equals("0")) {
                    run = false;
                    tradeAgain = false;

                } else {

                    found = searchPlayer(tradeLastName);

                    if (found == true) {

                        tradePlayerId = console.getInteger("\nEnter the ID of the player you wish to trade.\n>", "That is an invalid ID No. entry");

                        if (found == true) {

                            viewAllTeams();

                            recTeamId = console.getInteger("\nEnter the ID No. of the team this player will be traded to.\n>", "\nThat is an invalid ID No.!");

                            if (found == true) {

                                try {
                                    List<Player> playerList = playerDao.decode();

                                    for (Player playerOnFile : playerList) {

                                        playerOnFile = playerDao.get(tradePlayerId);
                                        playerFound = playerOnFile;

                                    }

                                    List<Team> teamList = teamDao.decode();

                                    for (Team teamOnFile : teamList) {

                                        teamOnFile = teamDao.get(recTeamId);
                                        teamFound = teamOnFile;

                                    }

                                    tradeFirstName = playerFound.getPlayerFirstName();
                                    tradeLastName = playerFound.getPlayerLastName();
                                    recTeamCity = teamFound.getTeamCity();
                                    recTeamName = teamFound.getTeamName();
                                    recTeamId = teamFound.getTeamId();
                                    playerFound.setPlayerTeamCity(recTeamCity);
                                    playerFound.setPlayerTeamName(recTeamName);
                                    playerFound.setTeamId(recTeamId);

                                    if (playerFound.getTeamId() == teamFound.getTeamId()) {

                                        System.out.println("\n" + tradeFirstName + " " + tradeLastName + " already belongs to the " + recTeamCity.toUpperCase() + " " + recTeamName.toUpperCase());

                                    } else {

                                        System.out.println("\n" + tradeFirstName.toUpperCase() + " " + tradeLastName.toUpperCase() + " WILL BE TRADED TO " + recTeamCity.toUpperCase() + " " + recTeamName.toUpperCase());

                                        confirm = console.yesCheck("\nIs this correct? [yes/no]\n>", "Enter [yes/no] to continue.");

                                    }

                                    if (confirm == true) {

                                        playerDao.update(playerFound, teamFound);

                                        System.out.println(tradeFirstName.toUpperCase() + " " + tradeLastName.toUpperCase() + " HAS BEEN TRADED TO THE " + recTeamCity.toUpperCase() + " " + recTeamName.toUpperCase());

                                    } else {

                                        System.out.println("\nTRADE CANCELLED!");

                                    }
                                } catch (Exception ex) {
                                    System.out.println("\nInvalid player/team Id No.");
                                }
                            }
                        }

                        tradeAgain = console.yesCheck("\nWould you like to trade another player? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                        if (tradeAgain == false) {
                            run = false;

                        }
                    }
                }
            }
        }
    }

    public void removeTeam() {

        boolean run;
        boolean removeAgain = true;
        boolean confirm = true;
        boolean found;
        int delTeamId;
        Team delTeam;
        
        
        run = checkTeamsExist("\nThere are currently no teams to remove!");


        while (run == true) {

            while (removeAgain == true) {

                viewAllTeams();

                delTeamId = console.getInteger("\nEnter the ID No. of the team you wish to remove. \n>", "\nThat is an invalid ID No.!");

                if (delTeamId == 0) {
                    run = false;
                    removeAgain = false;

                } else {

                    found = searchTeam(delTeamId);

                    if (found == true) {

                        delTeam = teamDao.get(delTeamId);

                        System.out.println("\n<><>WARNING!<><>\n"
                                + "\n<><>WARNING!<><>\n"
                                + "\n<><>WARNING!<><>\n\nBy deleting the " + delTeam.getTeamCity().toUpperCase() + " " + delTeam.getTeamName().toUpperCase() + ""
                                + " you will also remove the following players from the league! ");

                        searchRoster(delTeamId);

                        confirm = console.yesCheck("\nAre you sure you want to continue? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");

                        if (confirm == true) {

                            List<Player> players = playerDao.decode();

                            for (Player delPlayer : players) {

                                if (delPlayer.getTeamId() == delTeamId) {

                                    playerDao.delete(delPlayer);

                                    teamDao.delete(delTeam);

                                    System.out.println("\n" + delTeam.getTeamCity().toUpperCase() + " " + delTeam.getTeamName().toUpperCase()
                                            + " REMOVED FROM LEAGUE!");
                                    removeAgain = console.yesCheck("\nWould you like to remove another team? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                                    if (removeAgain == false) {
                                        run = false;
                                    }

                                }
                            }
                        } else {
                            System.out.println("\nREMOVAL CANCELLED!");
                        }
                    }
                }

            }
        }
    }

    public void showTeamInfo(Team teamShow) {

        System.out.println("\n==========<TEAM INFO>==========");
        System.out.println(" ID No.:" + teamShow.getTeamId());
        System.out.print("\n " + teamShow.getTeamCity().toUpperCase() + " " + teamShow.getTeamName().toUpperCase());
        System.out.println("\n Manager: " + teamShow.getTeamManager());
        System.out.println(" Owner: " + teamShow.getTeamOwner());
        System.out.println("===============================");

    }

    public void showPlayerInfo(Player playerShow) {

        System.out.println("\n=========<PLAYER INFO>=========");
        System.out.println(" ID No.:" + playerShow.getPlayerId());
        System.out.println(" " + playerShow.getPlayerFirstName()
                + " " + playerShow.getPlayerLastName() + " - #" + playerShow.getPlayerNumber());
        System.out.println(" " + playerShow.getPlayerTeamCity().toUpperCase() + " " + playerShow.getPlayerTeamName().toUpperCase());
        System.out.println("===============================");

    }

    public boolean searchRoster(int teamIdQuery) {

        List<Player> viewPlayers = playerDao.decode();
        List<Player> playerWithMatch = new ArrayList();
        boolean found = false;

        for (Player playerWithId : viewPlayers) {

            int foundId = playerWithId.getTeamId();
            if (foundId == teamIdQuery) {
                playerWithMatch.add(playerWithId);
                found = true;
            }

        }

        if (playerWithMatch.isEmpty()) {
            if (!found) {
                System.out.println("\nThere are no players currently on this team!");
            } else if (found == true) {
                System.out.println("\nNo teams were found by that ID!");
            }
        } else {
            for (Player playerInList : playerWithMatch) {

                showPlayerInfo(playerInList);

            }
        }
        return found;
    }

    public boolean searchPlayer(String lastNameQuery) {

        boolean found = false;

        List<Player> viewPlayers = playerDao.decode();

        if (!viewPlayers.isEmpty()) {
            for (Player playerInList : viewPlayers) {

                if (playerInList.getPlayerLastName().toLowerCase().equals(lastNameQuery.toLowerCase())) {
                    showPlayerInfo(playerInList);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("\nNo players were found by that last name!");
                found = false;

            }

        }
        return found;
    }

    public boolean searchTeam(int teamIdQuery) {

        boolean found = false;

        List<Team> viewTeam = teamDao.decode();

        if (!viewTeam.isEmpty()) {
            for (Team teamInList : viewTeam) {

                if (teamInList.getTeamId() == teamIdQuery) {
                    found = true;
                }
            }
            if (!found) {
                System.out.println("\nNo teams were found with that ID No.!");
                found = false;

            }

        }
        return found;
    }

    public boolean checkTeamsExist(String message) {

        boolean exists = true;
        List<Team> teams = teamDao.decode();

        if (teams.isEmpty()) {

            System.out.println(message);
            exists = false;

        }
        return exists;
    }

    public boolean checkPlayersExist(String message) {

        boolean exists = true;

        List<Player> players = playerDao.decode();

        if (players.isEmpty()) {

            System.out.println(message);
            exists = false;
        }
        return exists;
    }
}
