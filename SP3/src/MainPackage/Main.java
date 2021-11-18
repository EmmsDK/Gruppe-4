package MainPackage;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static public ArrayList<Team> finalList;
    static public String finalDeadline;
    static public ArrayList<String> finalDates;
    static Interface  menu;
    static UI ui;

//    enum Datasource{
//        DATABASE,
//        CSVFILE
//    }
//    private static Datasource datapath = Datasource.CSVFILE;
    public static void main(String[] args) throws IOException {
        finalList = new ArrayList<>();
        finalDeadline = new String();
        finalDates = new ArrayList<>();
//
//        ui = new UI() {
//            @Override
//            void inputFromUser() {
//
//            }
//        };
        IO io;
        // Comment/uncomment to test the 2
        io = new ReadFile();
        //io = new DBConnector();
//        if(datapath==Datasource.CSVFILE) {
//            io = new ReadFile();
//        }else {
//            io = new DBConnector();
//        }

        menu = new Interface(io.readTeamData(), io.readDeadlineData(), io.readDatesData());
        //menu = new Interface(io.readTeamData(), io.readDeadlineData(), io.readDatesData());
        //ui = new UI(io.readTeamData(), io.readDeadlineData(), io.readDatesData());
        menu.firstInteraktion();
//        if(menu.getUser().equalsIgnoreCase("user")){
//            ui = new Player();
//        }
//        else{
//            ui = new Admin();
//        }
        io.writeTeamData(finalList);
        System.out.println("1: "+finalDeadline);
        io.writeDeadlineData(finalDeadline);
        System.out.println("2: "+io.readDeadlineData());
        io.writeDatesData(finalDates);
    }
}
