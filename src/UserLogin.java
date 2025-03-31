import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserLogin {
    private Scene loginScene;
    private TextField usernameField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Stage stage;

    public UserLogin(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public void initializeComponents() {
        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new Insets(10));
        Button loginButton = new Button("Sign In");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                authenticate();
            }
        });

        loginLayout.getChildren().addAll(
                new Label("Username:"), usernameField,
                new Label("Password:"), passwordField,
                loginButton);

        loginScene = new Scene(loginLayout, 400, 250);
        stage.setTitle("Login to Hotel Reservation System");
        stage.setScene(loginScene);
        stage.show();
    }

    private void authenticate() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Connection con = DBUtils.establishConnection();
        String query = "SELECT * FROM users WHERE username = ?;";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                String role = rs.getString("role");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");

                String[] parts = storedPassword.split(":");
                if (parts.length != 2) {
                    showAlert("Authentication Failed", "Invalid username or password");
                    return;
                }

                String saltHexString = parts[0];
                String storedHashHexString = parts[1];
                String computedHashHexString = Hashing.hashSaltedPassword(saltHexString, password);

                if (computedHashHexString.equals(storedHashHexString)) {
                    User user = new User(username, storedPassword, role, firstname, lastname, phoneNumber, email);
                    System.out.println("Login successful! Welcome " + user.getFirstName() + " (" + user.getRole() + ")");
                    // TODO: Implement role-based redirection for Admin, Manager, and Receptionist
                    if (user.getRole().equals("Admin")) {
                        System.out.println("Move to Admin dashboard");
                        UserRegistration userRegistration = new UserRegistration(stage);
                        userRegistration.showRegistrationScene();
                    }
                    if (user.getRole().equals("Receptionist")) {
                        System.out.println("Move to Receptionist dashboard");
                    }
                    if (user.getRole().equals("Manager")) {
                        System.out.println("Move to Manager dashboard");
                    }
                } else {
                    showAlert("Authentication Failed", "Invalid username or password.");
                }
            } else {
                showAlert("Authentication Failed", "Invalid username or password.");
            }

            DBUtils.closeConnection(con, statement);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Database Error", "Failed to connect to the database.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
