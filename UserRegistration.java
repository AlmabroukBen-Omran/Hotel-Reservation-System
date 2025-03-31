import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class UserRegistration {
    private Stage stage;

    public UserRegistration(Stage stage) {
        this.stage = stage;
    }

    public void showRegistrationScene() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label titleLabel = new Label("User Registration");

        TextField firstnameField = new TextField();
        firstnameField.setPromptText("Enter First Name");

        TextField lastnameField = new TextField();
        lastnameField.setPromptText("Enter Last Name");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Manager", "Receptionist");
        roleBox.setPromptText("Select Role");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter Email");

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Enter Phone Number");

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            String firstname = firstnameField.getText();
            String lastname = lastnameField.getText();
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleBox.getValue();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();

            if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || password.isEmpty() ||
                    role == null || email.isEmpty() || phoneNumber.isEmpty()) {
                showAlert("Registration Failed", "Please fill in all fields.");
                return;
            }

            if (!ValidateUserRegistration.isValidFirstName(firstname)) {
                showAlert("Invalid First Name", "First name must contain only letters and be 2 to 50 characters long.");
                return;
            }

            if (!ValidateUserRegistration.isValidLastName(lastname)) {
                showAlert("Invalid Last Name", "Last name must contain only letters and be 2 to 50 characters long.");
                return;
            }

            if (!ValidateUserRegistration.isValidUsername(username)) {
                showAlert("Invalid Username", "Username must be 3-20 characters long and contain only letters, numbers, and underscores.");
                return;
            }

            if (!ValidateUserRegistration.isValidPassword(password)) {
                showAlert("Invalid Password", "Password must be at least 8 characters long, including an uppercase letter, a lowercase letter, a number, and a special character.");
                return;
            }

            if (!ValidateUserRegistration.isValidEmail(email)) {
                showAlert("Invalid Email", "Please enter a valid email address.");
                return;
            }

            if (!ValidateUserRegistration.isValidPhoneNumber(phoneNumber)) {
                showAlert("Invalid Phone Number", "Phone number must conform to the Qatari format (e.g., +974 XXXXXXXX)");
                return;
            }

            registerUser(firstname, lastname, username, password, role, email, phoneNumber);
        });

        layout.getChildren().addAll(
                titleLabel, firstnameField, lastnameField, usernameField,
                passwordField, roleBox, emailField, phoneNumberField, registerButton
        );

        Scene registrationScene = new Scene(layout, 400, 350);
        stage.setTitle("User Registration");
        stage.setScene(registrationScene);
        stage.show();
    }

    private void registerUser(String firstname, String lastname, String username, String password, String role, String email, String phoneNumber) {
        Connection con = DBUtils.establishConnection();
        String insertQuery = "INSERT INTO users (firstname, lastname, username, password, role, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {

            PreparedStatement stmt = con.prepareStatement(insertQuery);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, username);
            stmt.setString(4, Hashing.saltAndHashPassword(password));
            stmt.setString(5, role);
            stmt.setString(6, email);
            stmt.setString(7, phoneNumber);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                showAlert("Success", "User registered successfully!");
            } else {
                showAlert("Registration Failed", "Could not register user.");
            }

            DBUtils.closeConnection(con, stmt);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not register user.");
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
