import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
