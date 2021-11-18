package MainPackage;
import java.sql.*;
import java.util.ArrayList;

public class DBConnector implements IO {
    static final String DB_URL = "jdbc:mysql://localhost/HoldDatabase";
    static final String USER = "root";
    static final String PASS = "password";

    public ArrayList<Team> readTeamData() { // Skal returnere en ArrayList<Team>
        //String[] team_data = new String[16];
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
                members.add(rs.getString("player1"));
                members.add(rs.getString("player2"));
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
        //return team_data;
        return teamList;
    }

    public void writeTeamData(ArrayList<Team> teamList) {
        Connection conn = null;
        String sql = "INSERT INTO Hold(hold, player1, player2, result) "
                + "VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE hold=?, result=?";
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < teamList.size(); i++) {

                Team t = teamList.get(i);
                String player1 = t.getTeamPlayers().get(0);
                String player2 = t.getTeamPlayers().get(1);

                pstmt.setString(1, t.getTeamName());
                pstmt.setString(2, player1);
                pstmt.setString(3, player2);
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
        return _dates;
    }

    public void writeDatesData(ArrayList<String> _dates){}

    public String readDeadlineData(){
        String _deadline = new String();
        return _deadline;
    }

    public void writeDeadlineData(String _deadline){}
}





























