package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Scanner;
import java.lang.*;
import java.util.*;

public class Admin extends UI{
    public Admin() throws FileNotFoundException {
    }


    // Also add, so their play times (dates) are removed
    public void removeTeam(String teamName) throws IOException { // Maybe change int to something else, teamName perhaps?
        if(this.getTeamList().stream().anyMatch(element -> element.getTeamName().equals(teamName))){
            this.getTeamList().removeIf(element -> (element.getTeamName().equalsIgnoreCase(teamName)));
        }
        else{
            System.out.println("No team with this name");
            // Call the scanner again here
        }
        //this.teams.remove(_teamPlacement);
        for(int i = 0; i < getTeamList().size(); i++) {
            System.out.println(getTeamList().get(i));
        }
        //this.teams.remove(_teamPlacement);
        writeTeamToFile("teams.txt");
    }



//    //private ArrayList<String> testString;
//    //private ArrayList<Team> teams;
//    private String deadline; // Make it a Date (class) or make a Dates class to pass it to
//    private ArrayList<String> dates; // Likewise as above
//    /* Is written on the first line of the .txt file and is used to be able to read at which line
//       the written teams stops, and hence also at which line the written dates start */
//    int teamAmount;
//    int dateAmount; // Is it needed? - Only if we at some point also add points and wins
//
//    public String getDeadline() {
//        return deadline;
//    }
//    // Swap to UI
//    public void setDeadlineFirst(String deadline) {
//        this.deadline = deadline;
//    }
//
//    public void changeDeadline(String deadline) throws IOException {
//        this.deadline = deadline;
//        writeToFile("teams.txt");
//    }
//
//    public ArrayList<String> getDates() {
//        return dates;
//    }
//    // Swap to UI
//    public void setDatesFirst(ArrayList<String> dates) {
//        this.dates = dates;
//    }
//
//    public void addDates(String date) throws IOException {
//        this.dates.add(date);
//        writeToFile("teams.txt");
//    }
//
//    public void removeDates(String date) throws IOException {
//        // Make exception caught, for when using scanner - so that if it's not a valid date/team, it responds
//        this.dates.removeIf(element -> (element.equalsIgnoreCase(date)));
//        writeToFile("teams.txt");
//    }

    //public ArrayList<Team> getTeams() {
//        return teams;
//    }

    /* When the program runs, this is the first function that is called (under readFromFile), that sets the Team list
       with the teams from the teams.txt file */
    // Maybe place in UI
    //public void setTeamsList(ArrayList<Team> _teams) {
//        this.teams = _teams;
//    }

//    // Maybe also have in UI
//    void readFromFile(String file) throws FileNotFoundException {
//        Scanner fileReader = new Scanner(new File(file));
//        ArrayList<Team> tmpList = new ArrayList<>();
//        ArrayList<String[]> txtList = new ArrayList<>();
//        while (fileReader.hasNextLine()){
//            String line = fileReader.nextLine();
//            String[] element = line.split(" ");
//            txtList.add(element);
//        }
//        teamAmount = Integer.parseInt(txtList.get(0)[0]);
//        for(int i = 1; i <= teamAmount; i++) {
//            String[] element = txtList.get(i);
//            ArrayList<String> playerNames = new ArrayList<>();
//            for(int f = 1; f < element.length-1; f++){
//                playerNames.add(element[f]);
//            }
//            Team team = new Team(element[0], playerNames);
//            team.setHaveLost(Boolean.parseBoolean(element[element.length-1]));
//            tmpList.add(team);
//        }
//        setTeamsList(tmpList);
//        if(teamAmount+1 < txtList.size()) {
//            setDeadlineFirst(txtList.get(teamAmount + 1)[0]);
//        }
//        ArrayList<String> _dates = new ArrayList<>();
//        for(int i = teamAmount+2; i <txtList.size(); i++) {
//            String _date = "";
//            for(int f = 0; f < txtList.get(i).length; f++){
//                _date += txtList.get(i)[f] + " ";
//            }
//            _dates.add(_date);
//            // Add more, if team names also is included on the line
//        }
//        setDatesFirst(_dates);
//    }
//
//    @Override
//    public void writeToFile(String file) throws IOException {
//        try (FileWriter writer = new FileWriter(file)) {
//            //TestTeam tmpTeam = taw;
//            //for (TestTeam t : tmpTeam) {
//            // Changed to abstract
//            writer.write(getTeamList().size() + "\n");
//            // If TestTeam != null?
//            for (Team t : getTeamList()) {
//                //(int i = 0; i < tmpTeam.size(); i++){
//                writer.write(t.toString() + "\n");
//            }
//            // If deadline != null?
//            if (deadline.isBlank()) {
//                writer.write("01/01/2021");
//            } else {
//                writer.write(getDeadline() + "\n");
//            }
//            // If dates != null?
//            if (getDates().size() < 1) {
//                for (String s : getDates()) {
//                    writer.write(s + "\n");
//                }
//            }
//        }
//    }
////
////    public void writeToFile(String file) throws IOException {
////        // ArrayList<TestTeam> tmpTeam,
////        try (FileWriter writer = new FileWriter(file)) {
////            //TestTeam tmpTeam = taw;
////            //for (TestTeam t : tmpTeam) {
////            writer.write(getTeams().size()+"\n");
////            // If TestTeam != null?
////            for (Team t : getTeams()){
////                //(int i = 0; i < tmpTeam.size(); i++){
////                writer.write(t.toString()+"\n");
////            }
////            // If deadline != null?
////            if(deadline.isBlank()) {
////                writer.write("01/01/2021");
////            }
////            else{
////                writer.write(getDeadline() + "\n");
////            }
////            // If dates != null?
////            if(getDates().size() < 1) {
////                for (String s : getDates()) {
////                    writer.write(s + "\n");
////                }
////            }
////        }
////    }
//
//    // Adding and removing a team
////    public void addTeam(Team _teams) throws IOException {
////        this.teams.add(_teams);
////        writeToFile("teams.txt");
////    }
//
//    // Also add, so their play times (dates) are removed
//    public void removeTeam(String teamName) throws IOException { // Maybe change int to something else, teamName perhaps?
//        if(this.getTeamList().stream().anyMatch(element -> element.getTeamName().equals(teamName))){
//            this.getTeamList().removeIf(element -> (element.getTeamName().equalsIgnoreCase(teamName)));
//        }
//        else{
//            System.out.println("No team with this name");
//            // Call the scanner again here
//        }
//        //this.teams.remove(_teamPlacement);
//        for(int i = 0; i < getTeamList().size(); i++) {
//            System.out.println(getTeamList().get(i));
//        }
//        //this.teams.remove(_teamPlacement);
//        writeToFile("teams.txt");
//    }
//
//    /* Below are all the functions for making smaller changes to the individual teams, such as Team name, Team member,
//       whether they have lost and upcoming is each teams points and wins (amount) */
//    public void changeTeam(Boolean bob, int i) throws IOException {
//        ArrayList<Team> _teamList = getTeamList();
//        Team _team = _teamList.get(i-1);
//        _team.setHaveLost(bob);
//        _teamList.set(i-1, _team);
//        changeTeamsList(_teamList);
//    }
//
//    public void changeTeam(String _teamName, int i) throws IOException {
//        ArrayList<Team> _teamList = getTeamList();
//        Team _team = _teamList.get(i-1);
//        _team.setTeamName(_teamName);
//        _teamList.set(i-1, _team);
//        changeTeamsList(_teamList);
//    }
//
//    public void changeTeam(ArrayList<String> _teamMembers, int i) throws IOException {
//        // Add how team members are added (from player class)
//        ArrayList<Team> _teamList = getTeamList();
//        Team _team = _teamList.get(i-1);
//        _team.setTeamPlayers(_teamMembers);
//        _teamList.set(i-1, _team);
//        changeTeamsList(_teamList);
//    }
//
//    // Make a changeTeam for score and wins, when necessary

}

//package MainPackage;
//
//        import java.util.ArrayList;
//
//public class TestTeam {
//    private String teamName;
//    private ArrayList<String> teamMembers;
//    private Boolean hasLost = false;
//
//    public TestTeam(String teamName, ArrayList<String> teamMembers) {
//        this.teamName = teamName;
//        this.teamMembers = teamMembers;
//    }
//
//    public String getTeamName() {
//        return teamName;
//    }
//
//    public void setTeamName(String teamName) {
//        this.teamName = teamName;
//    }
//
//    public ArrayList<String> getTeamMembers() {
//        return teamMembers;
//    }
//
//    public void setTeamMembers(ArrayList<String> teamMembers) {
//        this.teamMembers = teamMembers;
//    }
//
//    public Boolean getHasLost() {
//        return hasLost;
//    }
//
//    public void setHasLost(Boolean hasLost) {
//        this.hasLost = hasLost;
//    }
//
//    @Override
//    public String toString() {
//        String members = "";
//        for (String s : teamMembers){
//            members += s + " ";
//            //System.out.println("Hej");
//        }
//        return teamName + " " + members + hasLost;
//    }
//}

