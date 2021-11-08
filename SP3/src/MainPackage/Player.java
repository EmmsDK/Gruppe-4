package MainPackage;

import MainPackage.Admin;
import MainPackage.Team;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player extends UI{
    //Admin admin = new Admin();
    ArrayList<String> playerNamesArray = new ArrayList<>();
    String teamNames = "";
    boolean input = true;
    Scanner sc = new Scanner(System.in);

    public Player() throws FileNotFoundException {      // Hvis der ikke er en fil, så skal der kastes en exception.

    }

    @Override
    // For admin and user (But overwritten in Player)
    public void writeToFile(String file) throws IOException {
        // ArrayList<TestTeam> tmpTeam,
        try (FileWriter writer = new FileWriter(file)) {
            //TestTeam tmpTeam = taw;
            //for (TestTeam t : tmpTeam) {
            // Changed to abstract
            writer.write(getTeamList().size()+"\n");
            // If TestTeam != null?
            for (Team t : getTeamList()){
                //(int i = 0; i < tmpTeam.size(); i++){
                writer.write(t.toString()+"\n");
            }
            // If deadline != null?
//            if(deadline.isBlank()) {
//                writer.write("01/01/2021");
//            }
//            else{
//                writer.write(getDeadline() + "\n");
//            }
//            // If dates != null?
//            if(getDates().size() < 1) {
//                for (String s : getDates()) {
//                    writer.write(s + "\n");
//                }
//            }
        }
    }

    public void inputFromPlayers() throws IOException {
        System.out.println(" *VELKOMMEN TIL ÅRETS SKOLE BORDFODBOLDTURNERING!* ");

        while (input) {     // Mens input er true - skal den blive ved med at køre.
            System.out.println("Indtast første spillers navn:");
            playerNamesArray.add(sc.next());
            System.out.println("Indtast andens spillers navn:");
            playerNamesArray.add(sc.next());

            int userChoice = playerChoiceInt();
            if (userChoice == 1) {
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

// Ingen space mellem input fx Team Fire = TeamFire.

