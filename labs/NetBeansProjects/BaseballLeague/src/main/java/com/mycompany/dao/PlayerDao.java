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

/**
 *
 * @author apprentice
 */
public class PlayerDao {

    List<Player> playerList = new ArrayList();
    Player player = new Player();
    Team team = new Team();
    
    private TeamDao teamDao;
    private int nextId = 2000;

    public PlayerDao(TeamDao teamDao) {

        this.teamDao = teamDao;
        playerList = decode();
        for (Player myPlayer : playerList) {

            if (myPlayer.getPlayerId() >= nextId) {
                nextId = myPlayer.getPlayerId() + 1;
            }

        }

    }

    public Player create(Player player, Team team) {

        player.setPlayerId(nextId);
        player.setTeam(team);
        nextId++;
        playerList.add(player);

        encode();

        return player;

    }

    public Player get(String lastName) {

        for (Player myPlayer : playerList) {

            String getName = myPlayer.getPlayerLastName();
            if (getName.equals(lastName)) {

                return myPlayer;
            }
        }
        return null;
    }

    public Player get(int idPlayer) {

        for (Player myPlayer : playerList) {

            int getId = myPlayer.getPlayerId();
            if (getId == idPlayer) {

                return myPlayer;
            }
        }
        return null;
    }
    
 

    public void update(Player player, Team team) {

        for (Player myPlayer : playerList) {

            if (myPlayer.getPlayerId() == player.getPlayerId()) {
                player.setTeam(team);
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
                out.print(TOKEN);
                out.print(myPlayer.getTeam().getTeamCity());
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

        List<Player> players = new ArrayList();
        Scanner sc = null;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("players.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Player myPlayer = new Player();

                
                int teamId = Integer.parseInt(stringParts[0]);
                myPlayer.setTeamId(teamId);
                
                Team myTeam = teamDao.get(teamId);
                
                int id = Integer.parseInt(stringParts[1]);
                myPlayer.setPlayerId(id);
                
                myPlayer.setPlayerFirstName(stringParts[2]);
                myPlayer.setPlayerLastName(stringParts[3]);
                myPlayer.setPlayerNumber(stringParts[4]);
                myPlayer.setPlayerTeamName(stringParts[5]);
                myPlayer.setPlayerTeamCity(stringParts[6]);
                
                myPlayer.setTeam(myTeam);

                players.add(myPlayer);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return players;

    }
}
