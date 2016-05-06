/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.Player;
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
import javafx.collections.ListChangeListener;

/**
 *
 * @author apprentice
 */
public class PlayerDao {
   
    List<Player> playerList = new ArrayList();
    Player player = new Player();
    Team team = new Team();

    private int nextId = 2000;

    public PlayerDao() {

        playerList = decode();
        for (Player myPlayer : playerList) {

            if (myPlayer.getPlayerId() >= nextId) {
                nextId = myPlayer.getPlayerId() + 1;
            }

        }

    }

    public Player create(Player player, Team team) {

        player.setPlayerId(nextId);
        team.setTeamId(nextId);
        nextId++;
        playerList.add(player);

        encode();

        return player;

    }

    public Player get(int id) {

        for (Player myPlayer : playerList) {

            int getId = myPlayer.getPlayerId();
            if (getId == id) {

                return myPlayer;
            }
        }
        return null;
    }

    public void update(Player player) {

        playerList = decode();
        for (Player myPlayer : playerList) {

            if (myPlayer.getPlayerId() == player.getPlayerId()) {
                playerList.remove(myPlayer);
                playerList.add(player);
                break;
            }
        }
        encode();

    }

    public void delete(Player player) {

        Player found = null;

        for (Player myItem : playerList) {

            if (myItem.getPlayerId() == player.getPlayerId()) {

                found = myItem;
                break;
            }

        }
        playerList.remove(found);
        encode();

    }
    
    public void encode() {

        final String TOKEN = "::";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("players.txt"));

            for (Player myPlayer : playerList) {

                out.print(myPlayer.getTeam().getTeamId());
                out.print(TOKEN);
                out.print(myPlayer.getPlayerId());
                out.print(TOKEN);
                out.print(myPlayer.getPlayerFirstName());
                out.print(TOKEN);
                out.print(myPlayer.getPlayerLastName());
                out.print(TOKEN);
                out.print(myPlayer.getPlayerNumber());
                out.print(TOKEN);
                out.print(myPlayer.getTeam().getTeamName());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List decode() {

        List<ListChangeListener.Change> transList = new ArrayList();
        Scanner sc = null;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("players.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Player myPlayer = new Player();

                int id = Integer.parseInt(stringParts[0]);
                myPlayer.setPlayerId(id);
                int teamId = Integer.parseInt(stringParts[1]);
                myPlayer.getTeam().setTeamId(teamId);
                myPlayer.setPlayerFirstName(stringParts[2]);
                myPlayer.setPlayerLastName(stringParts[3]);
                myPlayer.setPlayerNumber(stringParts[4]);
                myPlayer.getTeam().setTeamName(stringParts[5]);

                playerList.add(myPlayer);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return transList;

    }
}
