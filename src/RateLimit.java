import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RateLimit {
    private static final int MAX_ATTEMPTS = 5; // Lock after 5 failed attempts

    public static boolean isAccountLocked(String username) {
        Connection con = DBUtils.establishConnection();
        String query = "SELECT isLocked FROM users WHERE username = ?;";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("isLocked");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(con, statement);
        }
        return false;
    }

    public static void increaseFailedAttempts(String username) {
        Connection con = DBUtils.establishConnection();
        String query = "UPDATE users SET failedAttempts = failedAttempts + 1 WHERE username = ?;";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();

            if (getFailedAttempts(username) >= MAX_ATTEMPTS) {
                lockAccount(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(con, statement);
        }
    }

    public static void resetFailedAttempts(String username) {
        Connection con = DBUtils.establishConnection();
        String query = "UPDATE users SET failedAttempts = 0 WHERE username = ?;";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(con, statement);
        }
    }

    public static int getFailedAttempts(String username) {
        Connection con = DBUtils.establishConnection();
        String query = "SELECT failedAttempts FROM users WHERE username = ?;";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("failedAttempts");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(con, statement);
        }
        return 0;
    }

    private static void lockAccount(String username) {
        Connection con = DBUtils.establishConnection();
        String query = "UPDATE users SET isLocked = TRUE WHERE username = ?;";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(con, statement);
        }
    }

    public static void unlockAccount(String username) {
        Connection con = DBUtils.establishConnection();
        String query = "UPDATE users SET isLocked = FALSE, failedAttempts = 0 WHERE username = ?;";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(con, statement);
        }
    }
}
