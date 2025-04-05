import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class DisplayReservations {

    public void showAllReservations() {
        Stage stage = new Stage();
        stage.setTitle("All Reservations");

        TableView<Reservation> reservationTable = new TableView<>();

        TableColumn<Reservation, Integer> idCol = new TableColumn<>("Reservation ID");
        idCol.setCellValueFactory(data -> data.getValue().getReservationID().asObject());

        TableColumn<Reservation, Integer> customerIdCol = new TableColumn<>("Customer ID");
        customerIdCol.setCellValueFactory(data -> data.getValue().getCustomerID().asObject());

        TableColumn<Reservation, Integer> roomNumberCol = new TableColumn<>("Room Number");
        roomNumberCol.setCellValueFactory(data -> data.getValue().getRoomNumber().asObject());

        TableColumn<Reservation, String> checkInCol = new TableColumn<>("Check-In Date");
        checkInCol.setCellValueFactory(data -> data.getValue().getCheckInDate());

        TableColumn<Reservation, String> checkOutCol = new TableColumn<>("Check-Out Date");
        checkOutCol.setCellValueFactory(data -> data.getValue().getCheckOutDate());

        TableColumn<Reservation, Float> depositCol = new TableColumn<>("Deposit");
        depositCol.setCellValueFactory(data -> data.getValue().getDeposit().asObject());

        TableColumn<Reservation, Integer> paymentCol = new TableColumn<>("Payment Required");
        paymentCol.setCellValueFactory(data -> data.getValue().getPaymentRequired().asObject());

        TableColumn<Reservation, String> createdByCol = new TableColumn<>("Created By");
        createdByCol.setCellValueFactory(data -> data.getValue().getCreatedBy());

        TableColumn<Reservation, Void> cancelCol = new TableColumn<>("Cancel");
        cancelCol.setCellFactory(col -> new TableCell<>() {
            private final Button cancelBtn = new Button("Cancel");

            {
                cancelBtn.setOnAction(e -> {
                    Reservation reservation = getTableView().getItems().get(getIndex());
                    DeleteReservation.delete(reservation.getReservationID().get());
                    getTableView().getItems().remove(reservation);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : cancelBtn);
            }
        });

        reservationTable.getColumns().addAll(idCol, customerIdCol, roomNumberCol, checkInCol, checkOutCol, depositCol, paymentCol, createdByCol, cancelCol);

        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        String query = "SELECT * FROM reservations";

        try (Connection con = DBUtils.establishConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reservationID"),
                        rs.getInt("customerID"),
                        rs.getInt("roomNumber"),
                        rs.getString("checkInDate"),
                        rs.getString("checkOutDate"),
                        rs.getFloat("deposit"),
                        rs.getInt("paymentRequired"),
                        rs.getString("createdBy")
                ));
            }

            reservationTable.setItems(reservations);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error fetching reservations").showAndWait();
        }

        VBox layout = new VBox(10, reservationTable);
        layout.setPadding(new Insets(10));

        stage.setScene(new Scene(layout, 900, 400));
        stage.show();
    }
}
