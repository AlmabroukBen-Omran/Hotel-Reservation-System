import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerRegistration {
    private Stage stage;
    private TextField firstNameField, lastNameField, phoneNumberField, qidField;

    public CustomerRegistration(Stage primaryStage) {
        this.stage = primaryStage;
        initializeComponents();
    }

    private void initializeComponents() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label titleLabel = new Label("Register New Customer");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        firstNameField = new TextField();
        lastNameField = new TextField();
        phoneNumberField = new TextField();
        qidField = new TextField();

        firstNameField.setPromptText("First Name");
        lastNameField.setPromptText("Last Name");
        phoneNumberField.setPromptText("Phone Number");
        qidField.setPromptText("QID");

        Button registerButton = new Button("Register Customer");
        registerButton.setOnAction(event -> registerCustomer());

        layout.getChildren().addAll(
                titleLabel,
                new Label("First Name:"), firstNameField,
                new Label("Last Name:"), lastNameField,
                new Label("Phone Number:"), phoneNumberField,
                new Label("QID:"), qidField,
                registerButton
        );

        Scene scene = new Scene(layout, 400, 350);
        stage.setTitle("Customer Registration");
        stage.setScene(scene);
        stage.show();
    }

    private void registerCustomer() {
        String firstname = firstNameField.getText();
        String lastname = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        String qid = qidField.getText();


        if (firstname.isEmpty() || lastname.isEmpty() || phoneNumber.isEmpty() || qid.isEmpty()) {
            showAlert("Registration Failed", "Please fill in all fields.");
            Logging.log(Session.getCurrentUser().getUsername(), "Customer registration failed: One or more fields were empty.");
            return;
        }

        if (!ValidateCustomerRegistration.isValidFirstName(firstname)) {
            showAlert("Invalid First Name", "First name must contain only letters and be 2 to 50 characters long.");
            Logging.log(Session.getCurrentUser().getUsername(), "Invalid first name entered during registration: " + firstname);
            return;
        }

        if (!ValidateCustomerRegistration.isValidLastName(lastname)) {
            showAlert("Invalid Last Name", "Last name must contain only letters and be 2 to 50 characters long.");
            Logging.log(Session.getCurrentUser().getUsername(), "Invalid last name entered during registration: " + lastname);
            return;
        }

        if (!ValidateCustomerRegistration.isValidPhoneNumber(phoneNumber)) {
            showAlert("Invalid Phone Number", "Phone number must conform to the Qatari format (e.g., +974 XXXXXXXX)");
            Logging.log(Session.getCurrentUser().getUsername(), "Invalid phone number entered during registration: " + phoneNumber);
            return;
        }

        if (!ValidateCustomerRegistration.isValidQid(qid)) {
            showAlert("Invalid QID", "QID must only consist of 11 digits.");
            Logging.log(Session.getCurrentUser().getUsername(), "Invalid QID entered during registration: " + qid);
            return;
        }

        String insertQuery = "INSERT INTO customers (firstname, lastname, phoneNumber, QID) VALUES (?, ?, ?, ?)";

        try (Connection con = DBUtils.establishConnection();
             PreparedStatement statement = con.prepareStatement(insertQuery)) {

            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, phoneNumber);
            statement.setString(4, Encryption.encrypt(qid));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Success", "Customer registered successfully!");
                Logging.log(Session.getCurrentUser().getUsername(), "Successfully registered customer: " + firstname + " " + lastname);
                stage.close(); // Close registration window after success
            } else {
                showAlert("Failure", "Customer registration failed.");
                Logging.log(Session.getCurrentUser().getUsername(), "Customer registration failed: No rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Error while inserting customer.");
            Logging.log(Session.getCurrentUser().getUsername(), "SQL Exception during customer registration: " + e.getMessage());
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
