package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class UI {
    abstract ArrayList<Team> getTeamList();

    abstract String getDeadline();

    abstract ArrayList<String> getDates();

    abstract void setTeamList(ArrayList<Team> teamList);

    abstract void setDeadline(String deadline);

    abstract void setDates(ArrayList<String> dates);

    abstract void inputFromUser();
}


//    abstract ArrayList<Team> teamList = new ArrayList<>();
//    abstract String deadline; // Make it a Date (class) or make a Dates class to pass it to
//    private ArrayList<String> dates; // Likewise as above
//    private boolean hasLost;
//    boolean input = true;
//    String passWord = "1234";
//    Scanner sc = new Scanner(System.in);
//
//    Player player = new Player(this.teamList, this.deadline, this.dates);
//
//    Admin admin = new Admin(this.teamList, this.deadline, this.dates);
//
//    private ArrayList<Team> teamList = new ArrayList<>();
//    private String deadline; // Make it a Date (class) or make a Dates class to pass it to
//    private ArrayList<String> dates; // Likewise as above
//    private boolean hasLost;
//
//    public UI(ArrayList<Team> teamList, String deadline, ArrayList<String> dates) {
//        this.teamList = teamList;
//        this.deadline = deadline;
//        this.dates = dates;
//    }
//
//    // For admin and user
//    public ArrayList<String> getDates() {
//        return dates;
//    }
//
//    // For admin and user
//    public ArrayList<Team> getTeamList() {
//        return teamList;
//    }
//
//    // For admin and user
//    public String getDeadline() {
//        return deadline;
//    }
//
//    //only for admin
//    public void setTeamList(ArrayList<Team> teamList) {
//        this.teamList = teamList;
//    }
//
//    // only for admin
//    public void setDeadline(String deadline) {
//        this.deadline = deadline;
//    }
//
//    // only for admin
//    public void setDates(ArrayList<String> dates) {
//        this.dates = dates;
//    }
//
//    // only for user
//    public void addTeam(Team _teams) throws IOException {
//        this.teamList.add(_teams);
//    }
//
//    public void inputFromUser() throws IOException {
//        System.out.print("Hej og velkommen til turnerings programmet.\n" +
//                "Først og fremmest ");
//        while (input) {     // Mens input er true - skal den blive ved med at køre.
//            System.out.println("vælg hvem du er / hvad du vil\n" +
//                    "1: Jeg vil tilføje mit hold til turneringen\n2: Jeg er admin (låst med password)\nQ: stop programmet");
//            //int userChoice = playerChoiceInt();
//            if (sc.hasNextInt()) {
//                String userInput = sc.next();
//                if (Integer.parseInt(userInput) == 1) {
//                    //guest.readFromFile("teams.txt");
//                    player.inputFromPlayers();
//                    input = false;
//                } else if (Integer.parseInt(userInput) == 2) {
//                    System.out.println("Indtast password:");
//                    if (sc.next().equalsIgnoreCase(passWord)) {
//                        //ad.readFromFile("teams.txt");
//                        admin.inputFromAdmin();
//                        input = false;
//                    } else {
//                        System.out.println("Forkert password");
//                        continue;
//                    }
//                } else {
//                    System.out.println("Dette er desværre ikke en af mulighederne, angiv tallet 1 eller 2");
//                    System.out.println();
//                    continue;
//                }
//            } else {
//                String userInput = sc.next();
//                if (userInput.equalsIgnoreCase("q")) {
//                    break;
//                } else {
//                    System.out.println("Ikke et validt svar, angiv et tal mellem 1 og 5 eller q.");
//                    System.out.println();
//                    continue;
//                }
//            }
//        }
//    }

//    // Only for admin
//    public void setDeadlineFirst(String deadline) {
//        this.deadline = deadline;
//    }
//
//    // Only for admin
//    public void changeDeadline(String deadline) throws IOException {
//        this.deadline = deadline;
//        writeToFile("teams.txt");
//    }


//    // Not for anyone, just called when the program starts
//    public void setDatesFirst(ArrayList<String> dates) {
//        this.dates = dates;
//    }

//    // Only for admin / system (program) perhaps
//    public void addDates(String date) throws IOException {
//        this.dates.add(date);
//        writeToFile("teams.txt");
//    }

//    // Only for admin
//    public void removeDates(String date) throws IOException {
//        // Make exception caught, for when using scanner - so that if it's not a valid date/team, it responds
//        this.dates.removeIf(element -> (element.equalsIgnoreCase(date)));
//        writeToFile("teams.txt");
//    }



//    public void setTeamsList(ArrayList<Team> _teams) {
//        this.teamList = _teams;
//    }

//    // This function is called, if a smaller change is made to one of the already existing teams in the list
//    public void changeTeamsList(ArrayList<Team> _teams) throws IOException {
//        this.teamList = _teams;
//        writeToFile("teams.txt");
//    }


//    /* Below are all the functions for making smaller changes to the individual teams, such as Team name, Team member,
//       whether they have lost and upcoming is each teams points and wins (amount) */
//    public void changeTeam(Boolean bob, int i) throws IOException {
//        ArrayList<Team> _teamList = getTeamList();
//        Team _team = _teamList.get(i);
//        _team.setHaveLost(bob);
//        _teamList.set(i, _team);
//        changeTeamsList(_teamList);
//    }
//
//    public void changeTeam(String _teamName, int i) throws IOException {
//        ArrayList<Team> _teamList = getTeamList();
//        Team _team = _teamList.get(i);
//        _team.setTeamName(_teamName);
//        _teamList.set(i, _team);
//        changeTeamsList(_teamList);
//    }
//
//    public void changeTeam(ArrayList<String> _teamMembers, int i) throws IOException {
//        // Add how team members are added (from player class)
//        ArrayList<Team> _teamList = getTeamList();
//        Team _team = _teamList.get(i);
//        _team.setTeamPlayers(_teamMembers);
//        _teamList.set(i, _team);
//        changeTeamsList(_teamList);
//    }

    // Make a changeTeam for score and wins, when necessary
