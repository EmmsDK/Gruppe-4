package MainPackage;

import java.util.ArrayList;

public interface IO {
    String[] readTeamData();
    void writeData(ArrayList<Team> teamList);
}