/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dto;

/**
 *
 * @author apprentice
 */
public class Player {
    
    private int playerId;
    private int teamId;
    private String playerFirstName;
    private String playerLastName;
    private String playerNumber;
    private String playerTeamName;
    private String playerTeamCity;
    Team team;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }


    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getPlayerTeamName() {
        return playerTeamName;
    }

    public void setPlayerTeamName(String playerTeamName) {
        this.playerTeamName = playerTeamName;
    }

    public String getPlayerTeamCity() {
        return playerTeamCity;
    }

    public void setPlayerTeamCity(String playerTeamCity) {
        this.playerTeamCity = playerTeamCity;
    }
     
    
    
}
