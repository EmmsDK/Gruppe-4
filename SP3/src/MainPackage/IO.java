package MainPackage;

import java.util.ArrayList;

public interface IO {
    ArrayList<Team> readTeamData();
    void writeTeamData(ArrayList<Team> teamList);

    ArrayList<String> readDatesData();
    void writeDatesData(ArrayList<String> teamList);

    String readDeadlineData();
    void writeDeadlineData(String teamList);

//    ArrayList<Team> teamList = new ArrayList<>();
//    String deadline = "";
//    ArrayList<String> dates = new ArrayList<>();
}