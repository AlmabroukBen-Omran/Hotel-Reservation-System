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
                boolean locked = rs.getBoolean("isLocked");
                Logging.log("Security", "Checked lock status for user '" + username + "': " + (locked ? "LOCKED" : "UNLOCKED"));
                return locked;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.log("Security", "Failed to check lock status for user '" + username + "': " + e.getMessage());
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

            int attempts = getFailedAttempts(username);
            Logging.log("Security", "Increased failed attempts for user '" + username + "' to " + attempts);

            if (attempts >= MAX_ATTEMPTS) {
                lockAccount(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.log("Security", "Failed to increase failed attempts for user '" + username + "': " + e.getMessage());
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
            Logging.log("Security", "Reset failed attempts for user '" + username + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.log("Security", "Failed to reset failed attempts for user '" + username + "': " + e.getMessage());
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
                int attempts = rs.getInt("failedAttempts");
                Logging.log("Security", "Retrieved failed attempts for user '" + username + "': " + attempts);
                return attempts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.log("Security", "Failed to retrieve failed attempts for user '" + username + "': " + e.getMessage());
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
            Logging.log("Security", "Locked account for user '" + username + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.log("Security", "Failed to lock account for user '" + username + "': " + e.getMessage());
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
            Logging.log("Security", "Unlocked account and reset failed attempts for user '" + username + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            Logging.log("Security", "Failed to unlock account for user '" + username + "': " + e.getMessage());
        } finally {
            DBUtils.closeConnection(con, statement);
        }
    }
}
