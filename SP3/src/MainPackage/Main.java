package MainPackage;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static public ArrayList<Team> finalList;
    static public String finalDeadline;
    static public ArrayList<String> finalDates;
    static Interface  menu;

    enum Datasource{
        DATABASE,
        CSVFILE
    }
    private static Datasource datapath = Datasource.CSVFILE;
    public static void main(String[] args) throws IOException {
        finalList = new ArrayList<>();
        finalDeadline = new String();
        finalDates = new ArrayList<>();

        IO io;
        io = new DBConnector();

        /*if(datapath==Datasource.CSVFILE) {
            io = new ReadFile();
        }else {
            io = new DBConnector();
        }*/

        menu = new Interface(io.readTeamData(), io.readDeadlineData(), io.readDatesData());
        menu.firstInteraktion();

        io.writeTeamData(finalList);
        io.writeDeadlineData(finalDeadline);
        io.writeDatesData(finalDates);
    }


}
