package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    private ArrayList<String[]> txtList;

    // step 1) Read every line of the txt file
    private void readLines() throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File("teams.txt"));
        ArrayList<String[]> txtList = new ArrayList<>();
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            String[] element = line.split(" ");
            txtList.add(element);
        }
        this.txtList = txtList;
    }

    /* step 2) reads only teams from the txtList, so first line is the amount of teams (a int) and the next
    x (amount of teams) lines, is the teams which has to be instantiated as a Team object */
    ArrayList<Team> readTeams(){
        int teamAmount;
        ArrayList<Team> tmpList = new ArrayList<>();
        teamAmount = Integer.parseInt(this.txtList.get(0)[0]); // Muligvis tilføjes noget
        this.txtList.remove(0);
        for(int i = 1; i <= teamAmount; i++) {
            String[] element = txtList.get(0);
            ArrayList<String> playerNames = new ArrayList<>();
            for(int f = 1; f < element.length-1; f++){
                playerNames.add(element[f]);
            }
            Team team = new Team(element[0], playerNames);
            team.setHaveLost(Boolean.parseBoolean(element[element.length-1]));
            tmpList.add(team);
            this.txtList.remove(0);
        }
        return tmpList;
    }

    // step 3) reads the deadline from the txtList
    public String readDeadline(){
        String _deadline = this.txtList.get(0)[0];
        this.txtList.remove(0);
        return _deadline;
    }

    // step 4) reads all the match dates from the txtList, which is just the last entries of the list
    public ArrayList<String> readMatchDates(){
        ArrayList<String> _dates = new ArrayList<>();
        int listSize = this.txtList.size();
        for(int i = 0; i < listSize; i++) {
            String _date = "";
            for(int f = 0; f < this.txtList.get(0).length; f++){
                _date += this.txtList.get(0)[f] + " ";
                this.txtList.remove(0);
            }
            _dates.add(_date);
            // Add more, if team names also is included on the line
        }
        return _dates;
    }

    // For admin and user (But overwritten in Player)
    public void writeToFile(ArrayList<Team> teamList, String deadline, ArrayList<String> dates) throws IOException {
        try (FileWriter writer = new FileWriter("teams.txt")) {
            // Changed to abstract
            writer.write(teamList.size()+"\n");
            for (Team t : teamList){
                writer.write(t.toString()+"\n");
            }
            if(deadline == null || deadline.isBlank()) {
                writer.write("01/01/2021");
            }
            else{
                writer.write(deadline + "\n");
            }
            if(dates.size() < 1) {
                for (String s : dates) {
                    writer.write(s + "\n");
                }
            }
        }
    }
}
