package MainPackage;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player extends UI{
    ArrayList<String> playerNamesArray = new ArrayList<>();
    String teamNames = "";
    boolean input = true;
    Scanner sc = new Scanner(System.in);

    ArrayList<Team> teamList = new ArrayList<>();
    String deadline;
    ArrayList<String> dates = new ArrayList<>();

    @Override
    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    @Override
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    @Override
    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    @Override
    public String getDeadline() {
        return deadline;
    }

    @Override
    public ArrayList<String> getDates() {
        return dates;
    }

    public void inputFromUser() {
        System.out.println(" *VELKOMMEN TIL ÅRETS SKOLE BORDFODBOLDTURNERING!* ");

        while (input) {     // Mens input er true - skal den blive ved med at køre.
            System.out.println("Indtast første spillers navn:");
            playerNamesArray.add(sc.next());
            System.out.println("Indtast andens spillers navn:");
            playerNamesArray.add(sc.next());

            int userChoice = playerChoiceInt();
            if (userChoice == 1) { // Med kun mulighed for at have 2 spillere, er denne del redundant
                System.out.println("Indtast næste spillers navn:");
                playerNamesArray.add(sc.next());

            }
            System.out.println("Indtast et holdnavn:");
            teamNames = sc.next();
            System.out.println("Jeres hold er nu oprettet. ");
            input = false;
            displayList(playerNamesArray, teamNames);

            Team teams = new Team(teamNames, playerNamesArray);     // Vi instantierer her fra Team class.
            addTeam(teams);       // Sender teams input videre til Admin class.
        }
        Main.finalList = teamList;
        Main.finalDeadline = deadline;
        Main.finalDates = dates;
    }

    // only for user
    public void addTeam(Team _teams){
        ArrayList<Team> newList = getTeamList();
        newList.add(_teams);
        setTeamList(newList);
    }

    private void displayList(ArrayList teamsArray, String teamDesc) {     // Metode der viser de tilføjede holdnavne.
        System.out.print(teamDesc + ": ");
        for (int i = 0; i < teamsArray.size() - 1; i++) {   // -1 for at stoppe ved den anden siste ','.
            System.out.print(teamsArray.get(i) + ", ");
        }
        System.out.println(teamsArray.get(teamsArray.size()-1));    // for at få det sidste element i arrayet.
    }

    private int playerChoiceInt() {        // Exception metode
        int choice = 0;
        System.out.println("Er i flere spillere? Indtast '1' for ja eller '2' for nej.");  // Spørger her
        try {
            String inStr = sc.next();
            int inInt = Integer.parseInt(inStr);
            if (inInt < 1 || inInt > 2) {       // Hvis der bliver indtastet alt andet end 1 eller 2, så skal der throwes en exception
                throw new InputMismatchException(); // Hvis inputtet/typen ikke passer til metoden. ('throw' bruges til at throw en specifik exception for en metode)
                // "InputMismatchException" opstår når man tager input fra brugeren, som ikke matcher metoden.
                // Fx hvis man læser en integer data med nextInt metoden, og værdien der bliver skrevet er af String, så sker der en fejl.
            } else {
                choice = inInt;     // Sætter choice til inInt, for at kunne køre videre hvis brugere skriver 1 eller 2.
            }
        } catch (InputMismatchException e) {     // Exception specifikt for scanneren, så prøv igen.
            System.out.println(" *FORKERT TAL INPUT! Du kan kun skrive '1' eller '2'!* ");
            playerChoiceInt();
        } catch (NumberFormatException e) {     // Exception for overall, hvis brugeren skriver en string i stedet for et tal, så prøv igen.
            // Kastes når man prøver at convert en string til et tal, heri fx int, float osv.
            System.out.println(" *FORKERT INPUT, DET KAN IKKE VÆRE BOGSTAVER! Du kan kun skrive '1' eller '2'!* ");
            playerChoiceInt();
        }
        return choice;
    }
}


