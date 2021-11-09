package MainPackage;

import java.io.FileNotFoundException;
import java.io.IOException;

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

    public static void main(String[] args) throws IOException {
        addTeam.readFromFile("teams.txt");

        addTeam.inputFromPlayers();

    }
}
