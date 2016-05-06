/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.Team;
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
import javafx.collections.ListChangeListener.Change;

/**
 *
 * @author apprentice
 */
public class TeamDao {

    List<Team> teamList = new ArrayList();
    Team team = new Team();

    private int nextId = 1000;

    public TeamDao() {

        teamList = decode();
        for (Team myTeam : teamList) {

            if (myTeam.getTeamId() >= nextId) {
                nextId = myTeam.getTeamId() + 1;
            }

        }

    }

    public Team create(Team team) {

        team.setTeamId(nextId);
        nextId++;
        teamList.add(team);

        encode();

        return team;

    }

    public Team get(int id) {

        for (Team myTeam : teamList) {

            int getId = myTeam.getTeamId();
            if (getId == id) {

                return myTeam;
            }
        }
        return null;
    }

    public void update(Team team) {

        teamList = decode();
        for (Team myItem : teamList) {

            if (myItem.getTeamId() == team.getTeamId()) {
                teamList.remove(myItem);
                teamList.add(team);
                break;
            }
        }
        encode();

    }

    public void delete(Team team) {

        Team found = null;

        for (Team myItem : teamList) {

            if (myItem.getTeamId() == team.getTeamId()) {

                found = myItem;
                break;
            }

        }
        teamList.remove(found);
        encode();

    }
    
    public void encode() {

        final String TOKEN = "::";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("team.txt"));

            for (Team myTeam : teamList) {

                out.print(myTeam.getTeamId());
                out.print(TOKEN);
                out.print(myTeam.getTeamName());
                out.print(TOKEN);
                out.print(myTeam.getTeamCity());
                out.print(TOKEN);
                out.print(myTeam.getTeamManager());
                out.print(TOKEN);
                out.print(myTeam.getTeamOwner());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List decode() {

        List<Change> transList = new ArrayList();
        Scanner sc = null;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("team.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Team myTeam = new Team();

                int id = Integer.parseInt(stringParts[0]);
                myTeam.setTeamId(id);
                myTeam.setTeamName(stringParts[2]);
                myTeam.setTeamCity(stringParts[3]);
                myTeam.setTeamManager(stringParts[4]);
                myTeam.setTeamOwner(stringParts[5]);

                teamList.add(myTeam);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return transList;

    }
}
