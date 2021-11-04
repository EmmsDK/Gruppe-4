package MainPackage;

import java.util.*;

public class Team {
    private String teamName;
    private Boolean haveLost = false;
    private ArrayList<String> teamPlayers;
    private int gameWon = 0;
    private int teamPoints;

    public Team(String teamName, Boolean haveLost, int gameWon, int teamPoints) {
        this.teamName = teamName;
        this.haveLost = haveLost;
        this.gameWon = gameWon;
        this.teamPoints = teamPoints;
    }

    public String getTeamName() {
        return teamName;
    }

    public Boolean getHaveLost() {
        return haveLost;
    }

    public ArrayList<String> getTeamPlayers() {
        return teamPlayers;
    }

    public int getGameWon() {
        return gameWon;
    }

    public int getTeamPoints() {
        return teamPoints;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setHaveLost(Boolean haveLost) {
        this.haveLost = haveLost;
    }

    public void setTeamPlayers(ArrayList<String> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public void setGameWon(int gameWon) {
        this.gameWon = gameWon;
    }

    public void setTeamPoints(int teamPoints) {
        this.teamPoints = teamPoints;
    }
}
