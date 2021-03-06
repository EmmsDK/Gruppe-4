package MainPackage;

import java.util.Scanner;
import java.lang.*;
import java.util.*;

public class Admin extends UI{
    private ArrayList<Knockout> knockoutTournaments = new ArrayList<>();
    private ArrayList<GroupStage> groupStageTournament = new ArrayList<>();

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

    /* Below are all the functions for making smaller changes to the individual teams, such as Team name, Team member,
   whether they have lost and upcoming is each teams points and wins (amount) */
    public void changeTeam(Boolean bob, int i) {
        ArrayList<Team> _teamList = getTeamList();
        Team _team = _teamList.get(i);
        _team.setHaveLost(bob);
        _teamList.set(i, _team);
        setTeamList(_teamList);
    }

    public void changeTeam(String _teamName, int i) {
        ArrayList<Team> _teamList = getTeamList();
        Team _team = _teamList.get(i);
        _team.setTeamName(_teamName);
        _teamList.set(i, _team);
        setTeamList(_teamList);
    }

    public void changeTeam(ArrayList<String> _teamMembers, int i) {
        // Add how team members are added (from player class)
        ArrayList<Team> _teamList = getTeamList();
        Team _team = _teamList.get(i);
        _team.setTeamPlayers(_teamMembers);
        _teamList.set(i, _team);
        setTeamList(_teamList);
    }

    // Also add, so their play times (dates) are removed
    public void removeTeam(String teamName) {
        ArrayList<Team> _teamList = getTeamList();
        if(_teamList.stream().anyMatch(element -> element.getTeamName().equals(teamName))){
            _teamList.removeIf(element -> (element.getTeamName().equalsIgnoreCase(teamName)));
        }
        else{
            System.out.println("No team with this name");
            // Call the scanner again here
        }
        for(int i = 0; i < _teamList.size(); i++) {
            System.out.println(getTeamList().get(i));
        }
        setTeamList(_teamList);
    }

    boolean input = true;
    Scanner sc = new Scanner(System.in);

    public void inputFromUser() {
        System.out.println("Du er succesfuldt logget ind som admin");
        while (input) {     // Mens input er true - skal den blive ved med at k??re.
            System.out.println("V??lg en af f??lgende:\n1: Tilf??j/??ndre deadline. | " +
                    "2: ??ndre/fjern hold.\n3: Opret turnering | 4:Tilf??j/??ndre kamp dato.\n5: F?? vist holdene | " +
                    "6: S??t et team i turneringen til at have vundet/tabt\n7: Lav en ny bracket med de vundne teams" +
                    " (kan kun g??res hvis alle kampe er afgjort) | Q: Quit\n8: Hard reset p?? de forskellige teams haveLost" +
                    " (kun midlertidigt, indtil det er fastsat, hvordan dette vil l??ses)");
            if(sc.hasNextInt()) {
                String userInput = sc.next();
                if (Integer.parseInt(userInput) == 1) {
                    inputDeadline();
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
                } else if(Integer.parseInt(userInput) == 6){
                    inputWon();
                    continue;

                }
                else if(Integer.parseInt(userInput) == 7){
                    if(knockoutTournaments.size() > 0){
                        knockoutTournaments.get(0).rounds();
                    }
                }
                else if(Integer.parseInt(userInput) == 8){
                    for(Team t : getTeamList()){
                        t.setHaveLost(false);
                    }
                    break;
                }
                else{
                    invalidInput();
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
        Main.finalList = teamList;
        Main.finalDeadline = deadline;
        Main.finalDates = dates;
        System.out.println("Team: "+getTeamList().size() + ", date: "+getDates().size() + ", deadline: "+getDeadline());
    }

    private void inputDeadline() {
        if(getDeadline().equals("01/01/2021")) {
            System.out.println("Tilf??j deadline:");
            //Make exception case, so it's xx/yy/zzzz for sure
            setDeadline(sc.next());
        }
        else{
            System.out.println("Nuv??rende deadline: "+getDeadline()+", ??ndre deadline til:");
            setDeadline(sc.next());
        }
    }
    private void inputTeams() {
        int teamInput;
        if(getTeamList().size() == 0){
            System.out.println("Der er ingen hold at kunne ??ndre eller fjerne, v??lg noget nyt.");
            // Method stops, and loop continues
        }
        else{
            System.out.println("Hvilket hold vil du ??ndre eller fjerne?\n");
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
                System.out.println("Vil du - 1: Fjerne holdet eller 2: ??ndre holdet");
                // Lav exceptions
                teamInput = inputNumber(2, 1);
                if(teamInput == 1){
                    removeTeam(getTeamList().get(pickedTeam).getTeamName());
                }
                else if(teamInput == 2){
                    System.out.println("Vil du ??ndre i\n1: Holdnavnet\n2: Spiller navne\n3: Hold tabt");
                    teamInput = inputNumber(3, 1);
                    if(teamInput == 1){
                        System.out.println("Skriv det nye navn:");
                        changeTeam(sc.next(), pickedTeam);
                    }
                    else if(teamInput == 2){
                        System.out.println("Hvor mange medlemmer skal der v??re p?? dette hold:");
                        teamInput = inputNumber(4, 2);
                        int playerAmount = 0;
                        ArrayList<String> newNames = new ArrayList<>();
                        while(playerAmount < teamInput){
                            System.out.println("Skriv navnet p?? "+(playerAmount+1)+". spiller:");
                            newNames.add(sc.next()); // Get this tested, and make sure it works
                            playerAmount++;
                        }
                        changeTeam(newNames, pickedTeam);
                    }
                    else if (teamInput == 3){
                        String tabt = "";
                        if(getTeamList().get(pickedTeam).getHaveLost() == true){
                            System.out.println("Holdet har allerede tabt");
                            // Kald scanner, eventuelt skal admin kunne ??ndre i hasLost, selv hvis du har tabt
                        }
                        else{
                            System.out.println("Holdet st??r til ikke at have tabt endnu, har de tabt?\n" +
                                    "1: Ja\n2: Nej");
                            teamInput = inputNumber(2,1);
                            if(teamInput == 1){
                                changeTeam(true, pickedTeam);
                                System.out.println("Holdet st??r nu til at have tabt");
                                //Kald scanner
                            }
                            else if(teamInput == 2){
                                System.out.println("Der bliver ikke ??ndret p?? holdet.");
                            }
                        }
                    }
                }
            }
        }
    }
    private void inputTournament(){
        int tournamentInput;
        if(getTeamList().size() > 1){
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
            System.out.println("Hvilken kamp vil du ??ndre datoen p??:");
            int kamp = 1;
            //Change the (0), if there is more tournaments in the list
            for(ArrayList a : knockoutTournaments.get(0).getTournament()){
                //Make sure, if there is only 1 team in the list, it dosen't bug
                Team team1 = (Team) a.get(0); // Make exceptions for if it not a Team object
                Team team2 = (Team) a.get(1); // -||-
                String date = "Ingen nuv??rende dato";
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
            System.out.println("Der er p?? nuv??rende tidspunkt ikke oprettet nogle turneringer, opret en" +
                    " for at kunne ??ndre datoerne p?? disse");
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
                String date = "Ingen nuv??rende dato";
                if(a.size() > 2){ // Make sure it's updated for groupStage, currently only works for knockout
                    date = (String) a.get(2);
                }
                System.out.print("Kamp "+kamp+": "+team1.getTeamName()+" vs. "+team2.getTeamName()+" - "+date);
                System.out.println();
                kamp++;
            }
        }
        else if(getTeamList().size() > 0){
            int hold = 1;
            System.out.println(getTeamList().size());
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

    private void inputWon(){
        int wonInput;
        //Checks that there are tournaments, whose haveLost can be changed
        if(knockoutTournaments.size() != 0 || groupStageTournament.size() != 0){
            // Make input for whether it's a knockout or groupstage tournament the admin wants to change
            System.out.println("Hvilken kamp er afgjort:");
            int kamp = 1;
            //Change the (0), if there is more tournaments in the list
            for(ArrayList a : knockoutTournaments.get(0).getTournament()){
                //Make sure, if there is only 1 team in the list, it dosen't bug
                Team team1 = (Team) a.get(0); // Make exceptions for if it not a Team object
                Team team2 = (Team) a.get(1); // -||-
                //String date = "Ingen nuv??rende dato";

                System.out.print("Kamp "+kamp+": "+team1.getTeamName()+" vs. "+team2.getTeamName()+" | "+
                        knockoutTournaments.get(0).someting(((Team) a.get(0)).getHaveLost(), ((Team) a.get(1)).getHaveLost())); //Midlertidigt
                System.out.println();
                kamp++;
            }
            int matchSelected = inputNumber(knockoutTournaments.get(0).getTournament().size(), 1)-1;
            if(matchSelected > -1){
                // Make toString override in Knockout, that returns "Hold: team1 vs. team2 - Kamp dato: xx/yy/zzzz
                System.out.println("Kampen: "+knockoutTournaments.get(0).toStringMatch(matchSelected));
                System.out.println("Er valgt, hvilket hold har tabt (angiv venligst 1 for det f??rste hold eller 2 for det andet)");
                int teamSelected = inputNumber(2, 1);
                Team team1 = (Team) knockoutTournaments.get(0).getTournament().get(matchSelected).get(0);
                Team team2 = (Team) knockoutTournaments.get(0).getTournament().get(matchSelected).get(1);
                if(team1.getHaveLost() == true || team2.getHaveLost() == true){
                    System.out.println("Et af disse hold har allerede tabt");
                }
                else {
                    knockoutTournaments.get(0).teamWon(matchSelected+1, teamSelected);
                }
            }
            else{
                invalidInput();
            }
        }
        else{
            System.out.println("Der er p?? nuv??rende tidspunkt ikke oprettet nogle turneringer, opret en" +
                    " for at kunne ??ndre datoerne p?? disse");
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
        System.out.println("Ikke et validt input, pr??v igen:");
    }
}

