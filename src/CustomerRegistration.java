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
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();
        long qid;

        // Check if QID field is empty by catching this exception since we cannot use || condition on long values
        try {
            qid = Long.parseLong(qidField.getText());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "QID must be a valid numeric value.");
            return;
        }

        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
            showAlert("Registration Failed", "Please fill in all fields.");
            return;
        }

        // TODO: Implement regex validation for all customer registration fields


        String insertQuery = "INSERT INTO customers (firstname, lastname, phoneNumber, QID) VALUES (?, ?, ?, ?)";

        try (Connection con = DBUtils.establishConnection();
             PreparedStatement stmt = con.prepareStatement(insertQuery)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, phoneNumber);
            stmt.setLong(4, qid);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert("Success", "Customer registered successfully!");
                stage.close(); // Close registration window after success
            } else {
                showAlert("Failure", "Customer registration failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Error while inserting customer.");
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
