package MainPackage;

import java.util.*;

public class Team {
    private String teamName;
    private boolean haveLost = false;
    private ArrayList<String> teamPlayers;
    private int gameWon = 0;
    private int teamPoints;


    public Team(String _teamName, ArrayList<String> teamPlayers) {
        this.teamName = _teamName;
        this.teamPlayers = teamPlayers;

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

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setHaveLost(boolean haveLost) {
        this.haveLost = haveLost;
    }

    public void setTeamPlayers(ArrayList<String> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

        @Override
    public String toString() {
        String members = "";
        for (String s : teamPlayers){
            members += s + " ";
            //System.out.println("Hej");
        }
        return teamName + " " + members + haveLost;
    }
}

