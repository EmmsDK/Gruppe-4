package MainPackage;

import java.util.ArrayList;

public class Knockout extends Tournament{
    private ArrayList<ArrayList> tournament = new ArrayList<>();
    private ArrayList<Team> match = new ArrayList<>();
    private ArrayList<Team> teams;
    private int round = 1;
    private boolean tournamentMade = false;

    public Knockout(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void makeTournament(String _deadline){
        ArrayList<Team> _teams = this.teams;
        this.tournament = randomizeMatches(_teams, 2);
        System.out.println("Turneringen ser således ud:");
        System.out.println("Deadline: "+_deadline);
        for(ArrayList<Team> a : this.tournament){
            //Make sure, if there is only 1 team in the list, it dosen't bug
            System.out.print(a.get(0).getTeamName()+" vs. "+a.get(1).getTeamName());
            System.out.println();
        }
        this.tournamentMade = true;
    }

    public String someting(boolean team1, boolean team2){
        String test = "";
        if(team1 == false && team2 == false){
            test = "Ikke afgjort";
        }
        else if (team1 == false && team2 == true){
            test = "won - lost";
        }
        else{
            test = "lost - won";
        }
        return test;
    }

    public void teamWon(int _match, int _team){
        ArrayList<Team> tmpMatch = this.tournament.get(_match-1);
        tmpMatch.get(_team-1).setHaveLost(true);
        this.tournament.set(_match-1, tmpMatch);
        for(ArrayList<Team> a : this.tournament){
            //Make sure, if there is only 1 team in the list, it dosen't bug

            System.out.print(a.get(0).getTeamName()+" vs. "+a.get(1).getTeamName()+ " | "
                    +someting(a.get(0).getHaveLost(), a.get(1).getHaveLost()));
            System.out.println();
        }
    }
    public void rounds(){
        int lostCounter = 0;
        ArrayList<ArrayList> _tournament = this.tournament;
        for(ArrayList a : _tournament){
            for(Object t : a){
                Team test = (Team) t;
                if(((Team) t).getHaveLost() == true){
                    lostCounter++;
                    System.out.println("It incrementet!: "+lostCounter);
                }
            }
        }
        if(_tournament.size() == lostCounter) {
            this.tournament = nextBracket(_tournament, 2);
            System.out.println("Efter " + this.round + ". runde, ser turneringen således ud:");
            for (ArrayList<Team> a : this.tournament) {
                //Make sure, if there is only 1 team in the list, it dosen't bug
                System.out.print(a.get(0).getTeamName() + " vs. " + a.get(1).getTeamName());
                System.out.println();
            }
            this.round++;
        }
        else{
            System.out.println("Not all matches has been concluded, finish all matches before going to next round");
        }
    }

    public void addMatchDates(String _date, int _matchNumber){
        /* Checking to see if there already is an date on this match - change it so it looks
        at objects and not the size of the match*/
        if(this.tournament.get(_matchNumber).size() < 3) {
            this.tournament.get(_matchNumber).add(_date);
        }
        else{
            // Call exception, a date already exists and send it back to the scanner - perhaps just do this in admin
        }
    }

    public void changeMatchDates(String _date, int _matchNumber){}

    public String toStringMatch(int _match) {
        String date = "Ingen dato";
        Team team1 = (Team) this.tournament.get(_match).get(0);
        Team team2 = (Team) this.tournament.get(_match).get(1);
        if(this.tournament.get(_match).size() == 3){
            date = (String) this.tournament.get(_match).get(3);
        }
        return "Hold: "+team1.getTeamName()+" vs. "+team2.getTeamName()+ " - "+date;
    }

    public ArrayList<ArrayList> getTournament() {
        return tournament;
    }
}
