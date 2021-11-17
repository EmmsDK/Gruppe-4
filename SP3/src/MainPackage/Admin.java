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
    private ArrayList<Knockout> knockoutTournaments = new ArrayList<>();
    private ArrayList<GroupStage> groupStageTournament = new ArrayList<>();
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
        writeToFile("teams.txt");
    }

    boolean input = true;
    Scanner sc = new Scanner(System.in);


    public void inputFromAdmin() throws IOException {
        System.out.println("Du er succesfuldt logget ind som admin");
        while (input) {     // Mens input er true - skal den blive ved med at køre.
            System.out.println("Vælg en af følgende:\n1: Tilføj/ændre deadline.\n" +
                    "2: Ændre/fjern hold.\n3: Opret turnering\n4:Tilføj/ændre kamp dato.\n5: Få vist holdene\nQ: Quit");
            if(sc.hasNextInt()) {
                String userInput = sc.next();
                if (Integer.parseInt(userInput) == 1) {
                    inputDeadline(); // Doesn't save it in txt file
                    System.out.println();
                    continue;
                } else if (Integer.parseInt(userInput) == 2) {
                    inputTeams();
                    continue;
                } else if (Integer.parseInt(userInput) == 3) {
                    inputTournament();
                    System.out.println();
                    continue;
                } else if (Integer.parseInt(userInput) == 4) {
                    inputMatchDate();
                    System.out.println();
                    continue;
                } else if (Integer.parseInt(userInput) == 5) {
                    inputTeam();
                    System.out.println();
                    continue;
                }
            }
            else{
                String userInput = sc.next();
                if(userInput.equalsIgnoreCase("q")){
                    break;
                }
                else {
                    System.out.println("Ikke et validt svar, angiv et tal mellem 1 og 5 eller q.");
                    System.out.println();
                    continue;
                }
            }
        }
    }

    private void inputDeadline() throws IOException {
        if(getDeadline().equals("01/01/2021")) {
            System.out.println("Tilføj deadline:");
            //Make exception case, so it's xx/yy/zzzz for sure
            setDeadlineFirst(sc.next());
        }
        else{
            System.out.println("Nuværende deadline: "+getDeadline()+", ændre deadline til:");
            changeDeadline(sc.next());
        }
    }
    private void inputTeams() throws IOException {
        int teamInput;
        if(teamAmount == 0){
            System.out.println("Der er ingen hold at kunne ændre eller fjerne, vælg noget nyt.");
            // Method stops, and loop continues
        }
        else{
            System.out.println("Hvilket hold vil du ændre eller fjerne?\n");
            int hold = 1;
            for (Team t : getTeamList()){
                System.out.print("Hold "+hold+": "+t.toString()+" | ");
                if(hold%3 == 0){
                    System.out.println();
                }
                hold++;
            }
            // Make exception for, if it's not an integer
            int pickedTeam = inputNumber(getTeamList().size(), 1)-1;
            if(pickedTeam > -1){
                System.out.println("Vil du - 1: Fjerne holdet eller 2: ændre holdet");
                // Lav exceptions
                teamInput = inputNumber(2, 1);
                if(teamInput == 1){
                    removeTeam(getTeamList().get(pickedTeam).getTeamName());
                }
                else if(teamInput == 2){
                    System.out.println("Vil du ændre i\n1: Holdnavnet\n2: Spiller navne\n3: Hold tabt");
                    teamInput = inputNumber(3, 1);
                    if(teamInput == 1){
                        System.out.println("Skriv det nye navn:");
                        changeTeam(sc.next(), pickedTeam);
                    }
                    else if(teamInput == 2){
                        System.out.println("Hvor mange medlemmer skal der være på dette hold:");
                        teamInput = inputNumber(4, 2);
                        int playerAmount = 0;
                        ArrayList<String> newNames = new ArrayList<>();
                        while(playerAmount < teamInput){
                            System.out.println("Skriv navnet på "+(playerAmount+1)+". spiller:");
                            newNames.add(sc.next()); // Get this tested, and make sure it works
                            playerAmount++;
                        }
                        changeTeam(newNames, pickedTeam);
                    }
                    else if (teamInput == 3){
                        String tabt = "";
                        if(getTeamList().get(pickedTeam).getHaveLost() == true){
                            System.out.println("Holdet har allerede tabt");
                            // Kald scanner, eventuelt skal admin kunne ændre i hasLost, selv hvis du har tabt
                        }
                        else{
                            System.out.println("Holdet står til ikke at have tabt endnu, har de tabt?\n" +
                                    "1: Ja\n2: Nej");
                            teamInput = inputNumber(2,1);
                            if(teamInput == 1){
                                changeTeam(true, pickedTeam);
                                System.out.println("Holdet står nu til at have tabt");
                                //Kald scanner
                            }
                            else if(teamInput == 2){
                                System.out.println("Der bliver ikke ændret på holdet.");
                            }
                        }
                    }
                }
            }
        }
    }
    private void inputTournament(){
        int tournamentInput;
        if(teamAmount > 1){
            System.out.println("Er det en (1) knockout- eller (2) Group stage turnering:");
            tournamentInput = inputNumber(2, 1);
            if(tournamentInput == 1) {
                Knockout turnering = new Knockout(getTeamList());
                knockoutTournaments.add(turnering);
                // Add so there can be more tournaments and not just the first (0) in the list
                knockoutTournaments.get(0).makeTournament(getDeadline());
            }
            else if (tournamentInput == 2){
                // Implementer group stage kald
            }
        }
    }
    private void inputMatchDate(){
        int dateInput;
        //Checks that there are tournaments, whose dates can be changed
        if(knockoutTournaments.size() != 0 || groupStageTournament.size() != 0){
            // Make input for whether it's a knockout or groupstage tournament the admin wants to change
            System.out.println("Hvilken kamp vil du ændre datoen på:");
            int kamp = 1;
            //Change the (0), if there is more tournaments in the list
            for(ArrayList a : knockoutTournaments.get(0).getTournament()){
                //Make sure, if there is only 1 team in the list, it dosen't bug
                Team team1 = (Team) a.get(0); // Make exceptions for if it not a Team object
                Team team2 = (Team) a.get(1); // -||-
                String date = "Ingen nuværende dato";
                if(a.size() > 2){ // Make sure it's updated for groupStage, currently only works for knockout
                    date = (String) a.get(2);
                }
                System.out.print("Kamp "+kamp+": "+team1.getTeamName()+" vs. "+team2.getTeamName()+" - "+date);
                System.out.println();
                kamp++;
            }
            dateInput = inputNumber(knockoutTournaments.get(0).getTournament().size(), 1)-1;
            if(dateInput > -1){
                // Make toString override in Knockout, that returns "Hold: team1 vs. team2 - Kamp dato: xx/yy/zzzz
                System.out.println("Kampen: "+knockoutTournaments.get(0).toStringMatch(dateInput));
                System.out.println("Er valgt, hvilken dato skal denne kamp spilles:");
                String dateString = sc.next();
                if(knockoutTournaments.get(0).getTournament().get(dateInput).size() > 2){
                    // Call change date instead
                }
                else{
                    //Make sure input is date xx/yy/zzzz - make exception for it
                    knockoutTournaments.get(0).addMatchDates(dateString, dateInput);
                }
            }
        }
        else{
            System.out.println("Der er på nuværende tidspunkt ikke oprettet nogle turneringer, opret en" +
                    " for at kunne ændre datoerne på disse");
        }
    }

    private void inputTeam(){
        if(knockoutTournaments.size() != 0 || groupStageTournament.size() != 0){
            int kamp = 1;
            //Change the (0), if there is more tournaments in the list
            for(ArrayList a : knockoutTournaments.get(0).getTournament()){
                //Make sure, if there is only 1 team in the list, it dosen't bug
                Team team1 = (Team) a.get(0); // Make exceptions for if it not a Team object
                Team team2 = (Team) a.get(1); // -||-
                String date = "Ingen nuværende dato";
                if(a.size() > 2){ // Make sure it's updated for groupStage, currently only works for knockout
                    date = (String) a.get(2);
                }
                System.out.print("Kamp "+kamp+": "+team1.getTeamName()+" vs. "+team2.getTeamName()+" - "+date);
                System.out.println();
                kamp++;
            }
        }
        else if(teamAmount > 0){
            int hold = 1;
            for (Team t : getTeamList()){
                System.out.print("Hold "+hold+": "+t.toString()+" | ");
                if(hold%3 == 0){
                    System.out.println();
                }
                hold++;
            }
        }
        else{
            System.out.println("Hverken turneringer eller hold er blevet oprettet, derved intet at vise");
        }
    }

    // Check whether the input is a number, as well as if it within the given range
    private int inputNumber(int maxValue, int minValue){
        int _number = 0;
        if(sc.hasNextInt()){
            _number = sc.nextInt();
            if(_number > maxValue || _number < minValue){
                invalidInput();
                _number = 0;
            }
        }
        else{
            invalidInput();
        }
        return _number;
    }

    private void invalidInput(){
        System.out.println("Ikke et validt input, prøv igen:");
    }
}

