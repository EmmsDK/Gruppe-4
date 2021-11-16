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


    public void inputFromPlayers() throws IOException {
        while (input) {     // Mens input er true - skal den blive ved med at køre.
            //Tilføj deadline
            //Ændre deadline
            //Ændre et hold - holdnavn, hold medlem, hold har tabt
            //Fjern et hold
            //Tilføj/ændrer kamp dato
            System.out.println("Som admin hvad vil du?\n1: Tilføj/ændre deadline.\n" +
                    "2: Ændre/fjern hold.\n3: Opret turnering\n4:Tilføj/ændre kamp dato.\n5: Få vist holdene\nQ: Quit");
            //int userChoice = playerChoiceInt();
            String userInput = sc.next();
            if(Integer.parseInt(userInput) == 1){
                inputDeadline();
                continue;
            }
            else if(Integer.parseInt(userInput) == 2){}
            else if(Integer.parseInt(userInput) == 3){}
            else if(Integer.parseInt(userInput) == 4){}
            else if(Integer.parseInt(userInput) == 5){}
            else if(userInput.equalsIgnoreCase("q")){}
            else{
                System.out.println("Ikke et validt svar, angiv et tal mellem 1 og 5 eller q.");
                System.out.println();
                continue;
            }
            switch(sc.nextInt()){
                case 1:
                    if(getDeadline().equals("01/01/2021")) {
                        System.out.println("Tilføj deadline:");
                        //Make exception case, so it's xx/yy/zzzz for sure
                        setDeadlineFirst(sc.next());
                    }
                    else{
                        System.out.println("Ændre deadline:");
                        changeDeadline(sc.next());
                    }
                    break;
                case 2:
                    if(teamAmount == 0){
                        System.out.println("Der er ingen hold at kunne ændre eller fjerne, vælg noget nyt.");
                        //Kald scanner igen / break loopet her og restart det
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
                        int pickedTeam = sc.nextInt()-1;
                        if(pickedTeam < getTeamList().size() || pickedTeam >= 0){
                            //int pickedTeam = sc.nextInt()-1;
                            System.out.println("1: Fjern hold eller 2: ændre holdet");
                            // Lav exceptions

                            if(sc.nextInt() == 1){
                                removeTeam(getTeamList().get(pickedTeam).getTeamName());
                                continue;
                                //Kald scanner igen
                            }
                            if(sc.nextInt() == 2){
                                System.out.println("Vil du ændre i 1: Holdnavnet\n2: Spiller navne\n3: Hold tabt");
                                if(sc.nextInt() == 1){
                                    System.out.println("Skriv det nye navn:");
                                    changeTeam(sc.next(), pickedTeam);
                                }
                                else if(sc.nextInt() == 2){
                                    int playerAmount = 0;
                                    ArrayList<String> newNames = new ArrayList<>();
                                    while(playerAmount < getTeamList().get(pickedTeam).getTeamPlayers().size()){
                                        System.out.println("Skriv navnet på "+(playerAmount+1)+". spiller:");
                                        newNames.add(sc.next());
                                    }
                                    changeTeam(newNames, pickedTeam);
                                }
                                else if (sc.nextInt() == 3){
                                    String tabt = "";
                                    if(getTeamList().get(pickedTeam).getHaveLost() == true){
                                        System.out.println("Holdet har allerede tabt");
                                        // Kald scanner, eventuelt skal admin kunne ændre i hasLost, selv hvis du har tabt
                                    }
                                    else{
                                        System.out.println("Holdet står til ikke at have tabt endnu, har de tabt?\n" +
                                                "1: Ja\n2: Nej");
                                        if(sc.nextInt() == 1){
                                            changeTeam(true, pickedTeam);
                                            System.out.println("Holdet står nu til at have tabt");
                                            //Kald scanner
                                        }
                                        else{
                                            System.out.println("Der bliver ikke ændret på holdet.");
                                            //Kald scanner
                                        }
                                    }
                                }
                                else{
                                    System.out.println("Ikke et validt input, prøv igen:");
                                    //Kald scanner
                                }
                            }
                        }
                        else{
                            System.out.println("Der er ikke et hold med dette nummer, prøv igen:");
                            continue;
                            //Kald scanner igen
                        }
                    }
                    break;
                case 3:
                    if(teamAmount > 1){
                        System.out.println("Er det en (1) knockout- eller (2) Group stage turnering:");
                        if(sc.nextInt() == 1) {
                            Knockout turnering = new Knockout(getTeamList());
                            knockoutTournaments.add(turnering);
                            // Add so there can be more tournaments and not just the first (0) in the list
                            knockoutTournaments.get(0).makeTournament(getDeadline());
                        }
                        else if (sc.nextInt() == 2){
                            // Implementer group stage kald
                        }
                        else{
                            System.out.println("Ikke et validt kald");
                            // Kald scanner
                        }
                    }
                    break;
                case 4: // Kald turnering før hold datoer - 4:Tilføj/ændre kamp dato.
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
                            System.out.print("Kamp "+kamp+": "+team1.getTeamName()+" vs. "+team2.getTeamName()+" - "+a.get(2));
                            System.out.println();
                            kamp++;
                        }
                        if(sc.nextInt() > knockoutTournaments.get(0).getTournament().size() || sc.nextInt() < 1){ //Ændre (0)
                            System.out.println("Ikke et validt input, skriv venligst et nummer mellem 1-"+knockoutTournaments.get(0).getTournament().size());
                            // call scanner
                        }
                        else{
                            int chosenMatch = sc.nextInt()-1;
                            // Make toString override in Knockout, that returns "Hold: team1 vs. team2 - Kamp dato: xx/yy/zzzz
                            System.out.println("Kampen: "+knockoutTournaments.get(0).toStringMatch(chosenMatch));
                            System.out.println("Er valgt, hvilken dato skal denne kamp spilles:");
                            if(knockoutTournaments.get(0).getTournament().get(chosenMatch).size() > 2){
                                // Call change date instead
                            }
                            else{
                                //Make sure input is date xx/yy/zzzz - make exception for it
                                knockoutTournaments.get(0).addMatchDates(sc.next(), chosenMatch);
                            }
                        }
                    }
                    else{
                        System.out.println("Der er på nuværende tidspunkt ikke oprettet nogle turneringer, opret en" +
                                " for at kunne ændre datoerne på disse");
                        // Kald scanner
                    }
                    break;
                case 5:
                    if(knockoutTournaments.size() != 0 || groupStageTournament.size() != 0){
                        int kamp = 1;
                        //Change the (0), if there is more tournaments in the list
                        for(ArrayList a : knockoutTournaments.get(0).getTournament()){
                            //Make sure, if there is only 1 team in the list, it dosen't bug
                            Team team1 = (Team) a.get(0); // Make exceptions for if it not a Team object
                            Team team2 = (Team) a.get(1); // -||-
                            System.out.print("Kamp "+kamp+": "+team1.getTeamName()+" vs. "+team2.getTeamName()+" - "+a.get(2));
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
                        System.out.println("Hverken nogle turneringer eller hold er blevet oprettet, derved intet at vise");
                        // Call scanner
                    }
                    break;
            }
            if(sc.next().equalsIgnoreCase("q")){
                input = false;
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
            System.out.println("Nuværende deadline: "+getDeadline()+" ændre deadline:");
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
            teamInput = sc.nextInt()-1;
            int pickedTeam = teamInput;
            if(teamInput < getTeamList().size() || teamInput >= 0){
                //int pickedTeam = sc.nextInt()-1;
                System.out.println("1: Fjern hold eller 2: ændre holdet");
                // Lav exceptions
                teamInput = sc.nextInt();
                if(teamInput == 1){
                    removeTeam(getTeamList().get(pickedTeam).getTeamName());
                    //Kald scanner igen
                }
                else if(teamInput == 2){
                    System.out.println("Vil du ændre i 1: Holdnavnet\n2: Spiller navne\n3: Hold tabt");
                    if(sc.nextInt() == 1){
                        System.out.println("Skriv det nye navn:");
                        changeTeam(sc.next(), pickedTeam);
                    }
                    else if(sc.nextInt() == 2){
                        int playerAmount = 0;
                        ArrayList<String> newNames = new ArrayList<>();
                        while(playerAmount < getTeamList().get(pickedTeam).getTeamPlayers().size()){
                            System.out.println("Skriv navnet på "+(playerAmount+1)+". spiller:");
                            newNames.add(sc.next());
                        }
                        changeTeam(newNames, pickedTeam);
                    }
                    else if (sc.nextInt() == 3){
                        String tabt = "";
                        if(getTeamList().get(pickedTeam).getHaveLost() == true){
                            System.out.println("Holdet har allerede tabt");
                            // Kald scanner, eventuelt skal admin kunne ændre i hasLost, selv hvis du har tabt
                        }
                        else{
                            System.out.println("Holdet står til ikke at have tabt endnu, har de tabt?\n" +
                                    "1: Ja\n2: Nej");
                            if(sc.nextInt() == 1){
                                changeTeam(true, pickedTeam);
                                System.out.println("Holdet står nu til at have tabt");
                                //Kald scanner
                            }
                            else{
                                System.out.println("Der bliver ikke ændret på holdet.");
                                //Kald scanner
                            }
                        }
                    }
                    else{
                        System.out.println("Ikke et validt input, prøv igen:");
                        //Kald scanner
                    }
                }
                else{
                    invalidInput();
                }
            }
            else{
                System.out.println("Der er ikke et hold med dette nummer, prøv igen:");
                //Kald scanner igen
            }
        }
    }
    private void inputTournament(){

    }
    private void inputMatchDate(){

    }
    private void inputTeam(){

    }

    private void invalidInput(){
        System.out.println("Ikke et validt input, prøv igen:");
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

