package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class UI {
    private ArrayList<Team> teamList = new ArrayList<>();
    private String deadline; // Make it a Date (class) or make a Dates class to pass it to
    private ArrayList<String> dates; // Likewise as above
    /* Is written on the first line of the .txt file and is used to be able to read at which line
       the written teams stops, and hence also at which line the written dates start */
    int teamAmount;
    int dateAmount; // Is it needed? - Only if we at some point also add points and wins

    // For admin and user
    public String getDeadline() {
        return deadline;
    }

    // Only for admin
    public void setDeadlineFirst(String deadline) {
        this.deadline = deadline;
    }

    // Only for admin
    public void changeDeadline(String deadline) throws IOException {
        this.deadline = deadline;
        writeToFile("teams.txt");
    }

    // For admin and user
    public ArrayList<String> getDates() {
        return dates;
    }

    // Not for anyone, just called when the program starts
    public void setDatesFirst(ArrayList<String> dates) {
        this.dates = dates;
    }

    // Only for admin / system (program) perhaps
    public void addDates(String date) throws IOException {
        this.dates.add(date);
        writeToFile("teams.txt");
    }

    // Only for admin
    public void removeDates(String date) throws IOException {
        // Make exception caught, for when using scanner - so that if it's not a valid date/team, it responds
        this.dates.removeIf(element -> (element.equalsIgnoreCase(date)));
        writeToFile("teams.txt");
    }

    // For admin and user
    public ArrayList<Team> getTeamList() {return teamList;}


    public void setTeamsList(ArrayList<Team> _teams) {
        this.teamList = _teams;
    }

    public void addTeam(Team _teams) throws IOException {
        this.teamList.add(_teams);
        writeToFile("teams.txt");
    }

    // This function is called, if a smaller change is made to one of the already existing teams in the list
    public void changeTeamsList(ArrayList<Team> _teams) throws IOException {
        this.teamList = _teams;
        writeToFile("teams.txt");
    }


    /* Below are all the functions for making smaller changes to the individual teams, such as Team name, Team member,
       whether they have lost and upcoming is each teams points and wins (amount) */
    public void changeTeam(Boolean bob, int i) throws IOException {
        ArrayList<Team> _teamList = getTeamList();
        Team _team = _teamList.get(i);
        _team.setHaveLost(bob);
        _teamList.set(i, _team);
        changeTeamsList(_teamList);
    }

    public void changeTeam(String _teamName, int i) throws IOException {
        ArrayList<Team> _teamList = getTeamList();
        Team _team = _teamList.get(i);
        _team.setTeamName(_teamName);
        _teamList.set(i, _team);
        changeTeamsList(_teamList);
    }

    public void changeTeam(ArrayList<String> _teamMembers, int i) throws IOException {
        // Add how team members are added (from player class)
        ArrayList<Team> _teamList = getTeamList();
        Team _team = _teamList.get(i);
        _team.setTeamPlayers(_teamMembers);
        _teamList.set(i, _team);
        changeTeamsList(_teamList);
    }

    // Make a changeTeam for score and wins, when necessary
}
