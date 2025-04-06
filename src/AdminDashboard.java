import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class AdminDashboard {
    private Stage stage;

    public AdminDashboard(Stage stage) {
        this.stage = stage;
    }

    public void showAdminDashboardScene() {
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

        // Sign Out button
        Button signOutButton = new Button("Sign Out");
        signOutButton.setOnAction(e -> {
            Logging.log(Session.getCurrentUser().getUsername(), "Signed out");
            UserLogin userLogin = new UserLogin(stage);
            userLogin.initializeComponents();
            Session.clearSession();
        });

        Button unlockUserAccountButton = new Button("Unlock User Account");
        unlockUserAccountButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Unlock User Account");
            dialog.setHeaderText("Admin Unlock");
            dialog.setContentText("Enter username to unlock account:");

            dialog.showAndWait().ifPresent(username -> {
                Logging.log(Session.getCurrentUser().getUsername(), "Unlocked user account: " + username);
                RateLimit.unlockAccount(username);
                showAlert("Success", "User " + username + " has been unlocked.");
            });
        });

        Button viewLogsButton = new Button("View Logs");
        viewLogsButton.setOnAction(e -> {
            ViewLogs.displayLogs(); // Opens the logs window in a new stage
        });

        layout.getChildren().addAll(
                titleLabel, firstnameField, lastnameField, usernameField,
                passwordField, roleBox, emailField, phoneNumberField, registerButton, signOutButton,
                unlockUserAccountButton, viewLogsButton
        );

        Scene adminDashboardScene = new Scene(layout, 450, 450);
        stage.setTitle("Admin Dashboard");
        stage.setScene(adminDashboardScene);
        stage.show();
    }

    private void registerUser(String firstname, String lastname, String username, String password, String role, String email, String phoneNumber) {
        Connection con = DBUtils.establishConnection();
        String insertQuery = "INSERT INTO users (firstname, lastname, username, password, role, email, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?);";
        String adminUsername = Session.getCurrentUser().getUsername();

        try {

            PreparedStatement statement = con.prepareStatement(insertQuery);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, username);
            statement.setString(4, Hashing.saltAndHashPassword(password));
            statement.setString(5, role);
            statement.setString(6, email);
            statement.setString(7, phoneNumber);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                Logging.log(adminUsername, "Registered new user: " + username + " (Role: " + role + ")");
                showAlert("Success", "User registered successfully!");
            } else {
                Logging.log(adminUsername, "Failed to register user: " + username);
                showAlert("Registration Failed", "Could not register user.");
            }

            DBUtils.closeConnection(con, statement);
        } catch (Exception e) {
            Logging.log(adminUsername, "Database error while registering user: " + username);
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
