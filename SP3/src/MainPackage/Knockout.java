package MainPackage;

import java.util.ArrayList;

public class Knockout extends Tournament{
    private ArrayList<ArrayList> tournament = new ArrayList<>();
    private ArrayList<Team> match = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();
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
