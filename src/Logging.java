import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Logging {

    public static void log(String username, String actionType) {
        Connection con = DBUtils.establishConnection();
        String query = "INSERT INTO logs (username, actionType, timestamp) VALUES (?, ?, ?);";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, actionType);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
            DBUtils.closeConnection(con, ps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
