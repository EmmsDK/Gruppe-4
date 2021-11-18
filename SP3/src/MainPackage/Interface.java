package MainPackage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {

    private ArrayList<Team> teamList;
    private String deadline;
    private ArrayList<String> dates;
    private String user = "";
    //private boolean hasLost;

    public Interface(ArrayList<Team> teamList, String deadline, ArrayList<String> dates) {
        this.teamList = teamList;
        this.deadline = deadline;
        this.dates = dates;
        //this.hasLost = hasLost;
    }

    public String getUser() {
        return user;
    }

    boolean input = true;
    String passWord = "1234";
    Scanner sc = new Scanner(System.in);

    private UI ad;

    private UI guest;

    public void firstInteraktion() throws IOException {
        System.out.print("Hej og velkommen til turnerings programmet.\n"+
                "Først og fremmest ");
        while (input) {     // Mens input er true - skal den blive ved med at køre.
            System.out.println("vælg hvem du er / hvad du vil\n" +
                    "1: Jeg vil tilføje mit hold til turneringen\n2: Jeg er admin (låst med password)\nQ: stop programmet");
            //int userChoice = playerChoiceInt();
            if(sc.hasNextInt()) {
                int userInput = sc.nextInt();
                if (userInput == 1) {
                    //guest.readFromFile("teams.txt");
                    guest = new Player();
                    guest.setTeamList(this.teamList);
                    guest.setDates(this.dates);
                    guest.setDeadline(this.deadline);
                    user = "player";
                    guest.inputFromUser();
                    input = false;
                } else if (userInput == 2) {
                    System.out.println("Indtast password:");
                    if(sc.next().equalsIgnoreCase(passWord)) {
                        //ad.readFromFile("teams.txt");
                        ad = new Admin();
                        ad.setTeamList(this.teamList);
                        ad.setDates(this.dates);
                        ad.setDeadline(this.deadline);
                        user = "admin";
                        ad.inputFromUser();
                        input = false;
                    }
                    else{
                        System.out.println("Forkert password");
                        continue;
                    }
                }
                else{
                    System.out.println("Dette er desværre ikke en af mulighederne, angiv tallet 1 eller 2");
                    System.out.println();
                    continue;
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
    }
}
