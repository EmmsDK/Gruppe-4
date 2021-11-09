package MainPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

//    static Admin ad;
//
//    static {
//        try {
//            ad = new Admin();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    static Player addTeam;

    static {
        try {
            addTeam = new Player();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static Tournament tour = new Tournament();

    public static void main(String[] args) throws IOException {
        addTeam.readFromFile("teams.txt");

        ArrayList<ArrayList<Team>> test = tour.randomizeMatches(addTeam.getTeamList(), 2);
        tour.nextBracket(test, 2, 1);
        addTeam.inputFromPlayers();

    }
}
