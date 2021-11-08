package MainPackage;

import java.util.*;

public class Team {
    private String teamName;
    private boolean haveLost = false;
    private ArrayList<String> teamPlayers;
    private int gameWon = 0;
    private int teamPoints;


    public Team(String _teamName, boolean _haveLost, int _gameWon, int _teamPoints) {
        this.teamName = _teamName;
        this.haveLost = _haveLost;
        this.gameWon = _gameWon;
        this.teamPoints = _teamPoints;
    }

    public String getTeamName() {
        return teamName;
    }

    public boolean getHaveLost() {
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

    public void setHaveLost(boolean haveLost) {
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

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                '}';
    }
}

