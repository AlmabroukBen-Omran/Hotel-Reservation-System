import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteReservation {

    public static void delete(int reservationID) {
        String username = Session.getCurrentUser().getUsername();
        String query = "DELETE FROM reservations WHERE reservationID = ?";

        try (Connection con = DBUtils.establishConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, reservationID);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                Logging.log(username, "Successfully deleted reservation with ID: " + reservationID);
            } else {
                new Alert(Alert.AlertType.WARNING, "No reservation found with the provided ID.").showAndWait();
                Logging.log(username, "No reservation found with ID: " + reservationID + " to delete.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to delete reservation.").showAndWait();
            Logging.log(username, "Error occurred while deleting reservation ID " + reservationID + ": " + e.getMessage());
        }
    }

    public static void updateRoomStatus(int resID) {
        String username = Session.getCurrentUser().getUsername();
        Connection con = DBUtils.establishConnection();
        String selectRoomNumber = "SELECT roomNumber FROM reservations WHERE reservationID = ?";
        String updateRoom = "UPDATE rooms SET isReserved = 0 WHERE roomNumber = ?";

        PreparedStatement selectStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs;

        try {
            // Step 1: Retrieve the room number
            selectStmt = con.prepareStatement(selectRoomNumber);
            selectStmt.setInt(1, resID);
            rs = selectStmt.executeQuery();

            if (rs.next()) {
                int roomNumber = rs.getInt("roomNumber");

                // Step 2: Update the room status
                updateStmt = con.prepareStatement(updateRoom);
                updateStmt.setInt(1, roomNumber);
                updateStmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update room status.").showAndWait();
            Logging.log(username, "Error occurred while updating room status: " + e.getMessage());
        } finally {
            DBUtils.closeConnection(con, selectStmt);
            DBUtils.closeConnection(con, updateStmt);
        }
    }

}
