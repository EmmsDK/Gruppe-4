package MainPackage;
import java.sql.*;
import java.util.ArrayList;

public class DBConnector implements IO {
    static final String DB_URL = "jdbc:mysql://localhost/HoldDatabase";
    static final String USER = "root";
    static final String PASS = "1qaz2wsx";

    public ArrayList<Team> readTeamData() {
        ArrayList<Team> teamList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        int count = 0;

        try {
            System.out.println("***Forbinder til database***");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("**Opretter queries**");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Hold";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ArrayList<String> members = new ArrayList<>();
                String hold = rs.getString("hold");
                members.add(rs.getString("spiller1"));
                members.add(rs.getString("spiller2"));
                boolean result = rs.getBoolean("result");

                Team team = new Team(hold, members);
                team.setHaveLost(result);
                teamList.add(team);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // Behandler errors for JDBC.
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Brugt til at close alle resources.
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se3) {
                se3.printStackTrace();
            }   // afslutter finally try.
        }       // afslutter selve try.
        return teamList;
    }

    public void writeTeamData(ArrayList<Team> teamList) {
        Connection conn = null;
        String sql = "INSERT INTO Hold(hold, spiller1, spiller2, result) "
                + "VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE hold=?, result=?";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < teamList.size(); i++) {

                Team t = teamList.get(i);
                String spiller1 = t.getTeamPlayers().get(0);
                String spiller2 = t.getTeamPlayers().get(1);

                pstmt.setString(1, t.getTeamName());
                pstmt.setString(2, spiller1);
                pstmt.setString(3, spiller2);
                pstmt.setBoolean(4, t.getHaveLost());

                pstmt.addBatch();

            }
            pstmt.executeBatch();
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
    public ArrayList<String> readDatesData(){
        ArrayList<String> _dates = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        int count = 0;

        try {
            System.out.println("***Forbinder til database***");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("**Opretter queries**");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Date";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String _date = rs.getString("date");
                _dates.add(_date);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            // Behandler errors for JDBC.
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Brugt til at close alle resources.
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se3) {
                se3.printStackTrace();
            }   // afslutter finally try.
        }       // afslutter selve try.
        return _dates;
    }

    public void writeDatesData(ArrayList<String> _dates){
        Connection conn = null;
        String sql = "INSERT INTO Date(date) "
                + "VALUES (?) ON DUPLICATE KEY UPDATE date=?";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < _dates.size(); i++) {
                String date = _dates.get(i);
                pstmt.setString(1,_dates.get(i));
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public String readDeadlineData(){
        String _deadline = new String();
        ArrayList<Team> teamList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        int count = 0;

        try {
            System.out.println("***Forbinder til database***");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("**Opretter queries**");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM Deadline";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                _deadline = rs.getString("deadline");
            }
            rs.close();
            stmt.close();
            conn.close();


        } catch (SQLException se) {
            // Behandler errors for JDBC.
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Brugt til at close alle resources.
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se3) {
                se3.printStackTrace();
            }   // afslutter finally try.
        }       // afslutter selve try.
        return _deadline;
    }

    public void writeDeadlineData(String _deadline){
        Connection conn = null;
        String sql = "INSERT INTO Deadline(deadline) "
                + "VALUES (?) ON DUPLICATE KEY UPDATE deadline=?";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for(int i =0 ; i<10; i++ ) {
                pstmt.setString(1, _deadline);
                String deadline = _deadline;
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}





























