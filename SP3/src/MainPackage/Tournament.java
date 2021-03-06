package MainPackage;

import java.util.ArrayList;
import java.util.Collections;

public class Tournament {
    private ArrayList<ArrayList> tournament = new ArrayList<>();
    private ArrayList<Team> match = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();
    // In non-knock-out make a

    public ArrayList<ArrayList> randomizeMatches(ArrayList<Team> teams, int bracketSize) {
        //ArrayList<ArrayList>
        ArrayList<ArrayList> _tournament = new ArrayList<>();
        Collections.shuffle(teams);
        // Keeps tracks of amount of games that is to be played
        for (int i = 0; i < Math.ceil(teams.size() / bracketSize); i++) {
            ArrayList<Team> innerMatch = new ArrayList<>();
            // Adds 2 teams to a match against each other
            for (int f = 0; f < bracketSize; f++) {
                innerMatch.add(teams.get(i*bracketSize+f));
            }
            // Adds the match (ArrayList) to the tournament
            _tournament.add(innerMatch);
        }
        System.out.println(_tournament);
        return _tournament;
    }

    public ArrayList<ArrayList> nextBracket(ArrayList<ArrayList> _tournament, int bracketSize){
        ArrayList<ArrayList> newBracket = new ArrayList<>();
        ArrayList<Team> winningTeams = new ArrayList<>();
        for(ArrayList<Team> a : _tournament){
            for(Team t : a){
                if(t.getHaveLost() == false) {
                    winningTeams.add(t);
                }
            }
        }
        for (int i = 0; i < winningTeams.size() / bracketSize; i++){
            ArrayList<Team> innerMatch = new ArrayList<>();
            for (int f = 0; f < bracketSize; f++){
                innerMatch.add(winningTeams.get(i*bracketSize+f));
            }
            newBracket.add(innerMatch);
        }
        System.out.println(newBracket);

        return newBracket;
    }
}
