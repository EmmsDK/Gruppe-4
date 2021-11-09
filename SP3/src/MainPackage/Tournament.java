package MainPackage;

import java.util.ArrayList;
import java.util.Collections;

import static java.util.Collections.shuffle;

public class Tournament {
    private ArrayList<ArrayList> tournament = new ArrayList<>();
    private ArrayList<Team> match = new ArrayList<>();
    private ArrayList<Team> teams = new ArrayList<>();
    // In non-knock-out make a

    public ArrayList<ArrayList<Team>> randomizeMatches(ArrayList<Team> teams, int bracketSize) {
        //ArrayList<ArrayList>
        ArrayList<ArrayList<Team>> _tournament = new ArrayList<>();
        Collections.shuffle(teams);
        // Keeps tracks of amount of games that is to be played
        for (int f = 0; f < Math.ceil(teams.size() / bracketSize); f++) {
            ArrayList<Team> innerMatch = new ArrayList<>();
            // Adds 2 teams to a match against each other
            for (int i = 0; i < bracketSize; i++) {
                innerMatch.add(teams.get(f*bracketSize+i));
            }
            // Adds the match (ArrayList) to the tournament
            _tournament.add(innerMatch);
        }
        System.out.println(_tournament);
        return _tournament;
    }

    public void nextBracket(ArrayList<ArrayList<Team>> _tournament, int bracketSize, int round){
        // ArrayList<ArrayList>
        ArrayList<ArrayList> newBracket = new ArrayList<>();
        //System.out.println(_tournament.size() / bracketSize);
        for(int i = 0; i < _tournament.size(); i++){
            //System.out.println("I: "+i);
            ArrayList<Team> oldInnerMatch = _tournament.get(i);
            ArrayList<Team> newInnerMatch = new ArrayList<>();
            for(int f = 0; f < _tournament.size() / bracketSize; f++){
                //System.out.println((i*(bracketSize/2)*round)+f);
                if(_tournament.get(i*bracketSize+f).get(f).getHaveLost() == false){
                    System.out.println(i*bracketSize+f);
                }
            }
        }
        //return _tournament;
    }
}
