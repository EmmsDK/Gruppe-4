package MainPackage;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

public class AdminTest {
    File testFile;
    Scanner fileReader;
    Admin ad = new Admin();
    ArrayList<TestTeam> testTeam = new ArrayList<>();
    ArrayList<TestTeam> fileTeam = new ArrayList<>();
    ArrayList<String> stringList1 = new ArrayList<>();
    ArrayList<String> stringList2 = new ArrayList<>();
    ArrayList<String> stringList3 = new ArrayList<>();
    TestTeam team1;
    TestTeam team2;
    TestTeam team3;

    public AdminTest() throws FileNotFoundException {
    }

    @Before
    public void setUp() throws Exception {
        testFile = new File("test.txt");
        fileReader = new Scanner(testFile);
        stringList1.add("Claes");
        stringList1.add("Niklas");
        stringList1.add("Mark");
        team1 = new TestTeam("HolgerDanske", stringList1);
        stringList2.add("Tobi");
        stringList2.add("Gustav");
        stringList2.add("Emilio");
        team2 = new TestTeam("HölgerSvenske", stringList2);
        stringList3.add("Pia");
        stringList3.add("Tine");
        stringList3.add("Bob");
        team3 = new TestTeam("HälgerNorske", stringList3);
        testTeam.add(team1);
        testTeam.add(team2);
        testTeam.add(team3);
    }

    @Test
    public void readFromFile() throws FileNotFoundException {
        ad.readFromFile("test.txt");
        assertEquals(ad.getTeams().size(), testTeam.size());
        for(int i = 0; i < testTeam.size(); i++){
            assertEquals(ad.getTeams().get(i).getTeamName(), testTeam.get(i).getTeamName());
            assertEquals(ad.getTeams().get(i).getTeamMembers(), testTeam.get(i).getTeamMembers());
            assertEquals(ad.getTeams().get(i).getHasLost(), testTeam.get(i).getHasLost());
        }
    }

    @Test
    public void writeToFile() throws IOException {
        ad.readFromFile("test.txt");
        ArrayList<String> testTeamNames = new ArrayList<>();
        testTeamNames.add("Gorm");
        testTeamNames.add("Hanne");
        TestTeam addedTeam = new TestTeam("Bears", testTeamNames);
        ad.addTeam(addedTeam);
        testTeam.add(addedTeam);
        ad.writeToFile("test.txt");
        ad.readFromFile("test.txt");
        for(int i = 0; i < testTeam.size(); i++){
            assertEquals(ad.getTeams().get(i).getTeamName(), testTeam.get(i).getTeamName());
            assertEquals(ad.getTeams().get(i).getTeamMembers(), testTeam.get(i).getTeamMembers());
            assertEquals(ad.getTeams().get(i).getHasLost(), testTeam.get(i).getHasLost());
        }
    }

    @Test
    public void setHaveLost() {
}