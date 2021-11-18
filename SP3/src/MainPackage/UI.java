package MainPackage;

import java.util.ArrayList;

public abstract class UI {
    abstract ArrayList<Team> getTeamList();

    abstract String getDeadline();

    abstract ArrayList<String> getDates();

    abstract void setTeamList(ArrayList<Team> teamList);

    abstract void setDeadline(String deadline);

    abstract void setDates(ArrayList<String> dates);

    abstract void inputFromUser();
}
