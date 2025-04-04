import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class CreateReservation {
    private Stage stage;
    private TextField customerIDField, roomNumberField, depositField, createdByField;
    private DatePicker checkInDatePicker, checkOutDatePicker;

    public CreateReservation(Stage stage) {
        this.stage = stage;
        initializeForm();
    }

    private void initializeForm() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label formTitle = new Label("Create Room Reservation");
        formTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        customerIDField = new TextField();
        roomNumberField = new TextField();
        checkInDatePicker = new DatePicker();
        checkOutDatePicker = new DatePicker();
        depositField = new TextField();
        createdByField = new TextField();

        customerIDField.setPromptText("Customer ID");
        roomNumberField.setPromptText("Room Number");
        checkInDatePicker.setPromptText("Check In Date");
        checkOutDatePicker.setPromptText("Check Out Date");
        depositField.setPromptText("Deposit Amount");
        createdByField.setPromptText("Created By");

        Button confirmButton = new Button("Confirm Reservation");
        confirmButton.setOnAction(e -> createReservation());

        layout.getChildren().addAll(
                formTitle,
                new Label("Customer ID:"), customerIDField,
                new Label("Room Number:"), roomNumberField,
                new Label("Check In Date:"), checkInDatePicker,
                new Label("Check Out Date:"), checkOutDatePicker,
                new Label("Deposit:"), depositField,
                new Label("Created By:"), createdByField,
                confirmButton
        );

        Scene scene = new Scene(layout, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Create Reservation");
        stage.show();
    }

    private boolean usernameExists(Connection con, String username) throws SQLException {
        String query = "SELECT role FROM users WHERE username = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userRole = rs.getString("role"); // Ensure 'role' exists
                return "Receptionist".equalsIgnoreCase(userRole);
            }
        }
        return false;
    }

    private boolean customerExists(Connection con, int customerID) throws SQLException {
        String query = "SELECT 1 FROM customers WHERE customerID = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if customer exists, false otherwise
        }
    }


    private void createReservation() {
        // Step 1: Validate inputs
        if (customerIDField.getText().trim().isEmpty() ||
                roomNumberField.getText().trim().isEmpty() ||
                checkInDatePicker.getValue() == null ||
                checkOutDatePicker.getValue() == null ||
                depositField.getText().trim().isEmpty() ||
                createdByField.getText().trim().isEmpty()) {

            showAlert("Reservation Failed", "All fields must be filled before proceeding.");
            return;
        }

        int customerID, roomNumber;
        float deposit;
        try {
            customerID = Integer.parseInt(customerIDField.getText().trim());
            roomNumber = Integer.parseInt(roomNumberField.getText().trim());
            deposit = Float.parseFloat(depositField.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Customer ID, Room Number, and Deposit must be valid numbers.");
            return;
        }

        // TODO: Add regex validation for reservation fields
        if (!ValidateReservation.isValidCustomerID(customerID)) {
            showAlert("Invalid Customer ID", "Customer ID must contain between 1 and 3 digits.");
            return;
        }

        if (!ValidateReservation.isValidRoomNumber(roomNumber)) {
            showAlert("Invalid Room Number", "Room number must contain 3 digits only.");
            return;
        }

        if (!ValidateReservation.isValidDeposit(deposit)) {
            showAlert("Invalid Deposit", "Deposit must be a positive decimal amount with 1 or 2 decimal places.");
            return;
        }

        String createdBy = createdByField.getText().trim();

        if (!ValidateReservation.isValidCreatedBy(createdBy)) {
            showAlert("Invalid Username", "Username must be 3-20 characters long and contain only letters, numbers, and underscores.");
            return;
        }

        try (Connection con = DBUtils.establishConnection()) {
            // Step 2: Validate if "createdBy" exists in the users table and is a Receptionist
            if (!usernameExists(con, createdBy)) {
                showAlert("Invalid User", "The 'Created By' field must contain a valid Receptionist's username.");
                return;
            }

            // Step 3: Validate if Customer ID exists in the customers table
            if (!customerExists(con, customerID)) {
                showAlert("Invalid Customer", "Customer ID does not exist in the system.");
                return;
            }

            // Step 4: Check if the room exists and whether it's reserved
            String roomQuery = "SELECT type, isReserved FROM rooms WHERE roomNumber = ?";
            String roomType = null;
            boolean isReserved;

            try (PreparedStatement roomStmt = con.prepareStatement(roomQuery)) {
                roomStmt.setInt(1, roomNumber);
                ResultSet rs = roomStmt.executeQuery();

                if (rs.next()) {
                    isReserved = rs.getBoolean("isReserved");
                    roomType = rs.getString("type");

                    if (isReserved) {
                        showAlert("Room Unavailable", "This room is already reserved.");
                        return;
                    }
                } else {
                    showAlert("Room Not Found", "Room number does not exist.");
                    return;
                }
            }

            // Step 5: Determine paymentRequired based on room type
            int paymentRequired;
            if ("Single Room".equals(roomType)) {
                paymentRequired = 800;
            } else if ("Suite".equals(roomType)) {
                paymentRequired = 1500;
            } else {
                showAlert("Room Type Error", "Unknown room type: " + roomType);
                return;
            }

            // Step 6: Insert reservation
            String insertQuery = "INSERT INTO reservations (customerID, roomNumber, checkInDate, checkOutDate, deposit, paymentRequired, createdBy) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, customerID);
                insertStmt.setInt(2, roomNumber);
                insertStmt.setString(3, checkInDatePicker.getValue().toString());
                insertStmt.setString(4, checkOutDatePicker.getValue().toString());
                insertStmt.setFloat(5, deposit);
                insertStmt.setInt(6, paymentRequired);
                insertStmt.setString(7, createdBy);

                int result = insertStmt.executeUpdate();

                if (result > 0) {
                    // Step 7: Update room's isReserved to true
                    String updateRoom = "UPDATE rooms SET isReserved = 1 WHERE roomNumber = ?";
                    try (PreparedStatement updateStmt = con.prepareStatement(updateRoom)) {
                        updateStmt.setInt(1, roomNumber);
                        updateStmt.executeUpdate();
                    }

                    showAlert("Success", "Reservation created and room marked as reserved.");
                    stage.close();
                } else {
                    showAlert("Failure", "Failed to create reservation.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Something went wrong: " + e.getMessage());
        }
    }




    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}