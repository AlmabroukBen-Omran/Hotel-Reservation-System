import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteReservation {

    public static void delete(int reservationID) {
        String query = "DELETE FROM reservations WHERE reservationID = ?";

        try (Connection con = DBUtils.establishConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, reservationID);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            new javafx.scene.control.Alert(Alert.AlertType.ERROR, "Failed to delete reservation.").showAndWait();
        }
    }
}