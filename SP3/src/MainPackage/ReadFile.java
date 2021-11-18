package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile implements IO {
    /* step 2) reads only teams from the txtList, so first line is the amount of teams (a int) and the next
    x (amount of teams) lines, is the teams which has to be instantiated as a Team object */
    public ArrayList<Team> readTeamData(){
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("teams.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> txtList = new ArrayList<>();
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            String[] element = line.split(" ");
            txtList.add(element);
        }
        ArrayList<Team> tmpList = new ArrayList<>();
        int counter = txtList.size();
        for(int i = 0; i < counter; i++) {
            String[] element = txtList.get(0);
            ArrayList<String> playerNames = new ArrayList<>();
            for(int f = 1; f < element.length-1; f++){
                playerNames.add(element[f]);
            }
            Team team = new Team(element[0], playerNames);
            team.setHaveLost(Boolean.parseBoolean(element[element.length-1]));
            tmpList.add(team);
            txtList.remove(0);
        }
        return tmpList;
    }

    // reads the deadline from the deadline.txt file
    public String readDeadlineData(){
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("deadline.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String deadline = "";
        if(fileReader.hasNextLine()) {
            deadline = fileReader.nextLine();
        }
        return deadline;
    }

    // step 4) reads all the match dates from the txtList, which is just the last entries of the list
    public ArrayList<String> readDatesData(){
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("dates.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String[]> txtList = new ArrayList<>();
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            String[] element = line.split(" ");
            txtList.add(element);
        }
        ArrayList<String> _dates = new ArrayList<>();
        int listSize = txtList.size();
        for(int i = 0; i < listSize; i++) {
            String _date = "";
            for(int f = 0; f < txtList.get(0).length; f++){
                _date += txtList.get(0)[f] + " ";
                txtList.remove(0);
            }
            _dates.add(_date);
            // Add more, if team names also is included on the line
        }
        return _dates;
    }

    // For admin and user (But overwritten in Player)
    public void writeTeamData(ArrayList<Team> teamList){
        try (FileWriter writer = new FileWriter("teams.txt")) {
            for (Team t : teamList){
                writer.write(t.toString()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDatesData(ArrayList<String> dates){
        try (FileWriter writer = new FileWriter("dates.txt")) {
            for (String s : dates) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDeadlineData(String deadline){
        try (FileWriter writer = new FileWriter("deadline.txt")) {
            if (deadline == null || deadline.isBlank()) {
                writer.write("01/01/2021");
            } else {
                writer.write(deadline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
