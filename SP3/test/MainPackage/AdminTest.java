//package MainPackage;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import static org.junit.Assert.*;
//
//public class AdminTest {
//    File testFile;
//    Scanner fileReader;
//    Admin ad = new Admin();
//    ArrayList<Team> Team = new ArrayList<>();
//    ArrayList<Team> fileTeam = new ArrayList<>();
//    ArrayList<String> stringList1 = new ArrayList<>();
//    ArrayList<String> stringList2 = new ArrayList<>();
//    ArrayList<String> stringList3 = new ArrayList<>();
//    Team team1;
//    Team team2;
//    Team team3;
//
//    public AdminTest() throws FileNotFoundException {
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        testFile = new File("test.txt");
//        fileReader = new Scanner(testFile);
//        stringList1.add("Claes");
//        stringList1.add("Niklas");
//        stringList1.add("Mark");
//        team1 = new Team("HolgerDanske", stringList1);
//        stringList2.add("Tobi");
//        stringList2.add("Gustav");
//        stringList2.add("Emilio");
//        team2 = new Team("HölgerSvenske", stringList2);
//        stringList3.add("Pia");
//        stringList3.add("Tine");
//        stringList3.add("Bob");
//        team3 = new Team("HälgerNorske", stringList3);
//        Team.add(team1);
//        Team.add(team2);
//        Team.add(team3);
//    }
//
//    @Test
//    public void readFromFile() throws FileNotFoundException {
//        ad.readFromFile("test.txt");
//        assertEquals(ad.getTeams().size(), Team.size());
//        for(int i = 0; i < Team.size(); i++){
//            assertEquals(ad.getTeams().get(i).getTeamName(), Team.get(i).getTeamName());
//            assertEquals(ad.getTeams().get(i).getTeamMembers(), Team.get(i).getTeamMembers());
//            assertEquals(ad.getTeams().get(i).getHasLost(), Team.get(i).getHasLost());
//        }
//    }
//
//    @Test
//    public void writeToFile() throws IOException {
//        ad.readFromFile("test.txt");
//        ArrayList<String> TeamNames = new ArrayList<>();
//        TeamNames.add("Gorm");
//        TeamNames.add("Hanne");
//        Team addedTeam = new Team("Bears", TeamNames);
//        ad.addTeam(addedTeam);
//        Team.add(addedTeam);
//        ad.writeToFile("test.txt");
//        ad.readFromFile("test.txt");
//        for(int i = 0; i < Team.size(); i++){
//            assertEquals(ad.getTeams().get(i).getTeamName(), Team.get(i).getTeamName());
//            assertEquals(ad.getTeams().get(i).getTeamMembers(), Team.get(i).getTeamMembers());
//            assertEquals(ad.getTeams().get(i).getHasLost(), Team.get(i).getHasLost());
//        }
//    }
//
//    @Test
//    public void setHaveLost() {
//}