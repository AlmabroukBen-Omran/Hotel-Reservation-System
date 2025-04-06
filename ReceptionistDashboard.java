import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceptionistDashboard {
    private Stage stage;
    private TableView<Room> roomTableView;
    private VBox layout;

    public ReceptionistDashboard(Stage primaryStage) {
        this.stage = primaryStage;
        initializeComponents();
    }

    public void showReceptionistDashboardScene() {
        stage.setTitle("Receptionist Dashboard");
        stage.setScene(new Scene(layout, 600, 400)); // Use the initialized layout
        stage.show();
    }

    private void initializeComponents() {
        layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label dashboardTitle = new Label("Receptionist Dashboard");
        dashboardTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button checkRoomsButton = new Button("Check Room Availability");
        checkRoomsButton.setOnAction(event -> {
            checkRoomAvailability();
            Logging.log(Session.getCurrentUser().getUsername(), "Checked room availability.");
        });

        Button createCustomerButton = new Button("Create Customer Profile");
        createCustomerButton.setOnAction(event -> {
            openCustomerRegistration();
            Logging.log(Session.getCurrentUser().getUsername(), "Opened customer registration form.");
        });

        Button showCustomersButton = new Button("Show Customers");
        showCustomersButton.setOnAction(event -> {
            showCustomers();
            Logging.log(Session.getCurrentUser().getUsername(), "Viewed all customer profiles.");
        });

        Button createReservationButton = new Button("Create Reservation");
        createReservationButton.setOnAction(event -> {
            openCreateReservation();
            Logging.log(Session.getCurrentUser().getUsername(), "Opened reservation creation form.");
        });

        Button showReservationsButton = new Button("Show Reservations");
        showReservationsButton.setOnAction(event -> {
            DisplayReservations display = new DisplayReservations();
            display.showAllReservations();
            Logging.log(Session.getCurrentUser().getUsername(), "Viewed all reservations.");
        });

        Button signOutButton = new Button("Sign Out");
        signOutButton.setOnAction(event -> {
            returnToLogin();
            Logging.log(Session.getCurrentUser().getUsername(), "Signed out.");
        });

        roomTableView = new TableView<>();
        setupTableView();

        layout.getChildren().addAll(dashboardTitle, checkRoomsButton, createCustomerButton, showCustomersButton, createReservationButton, showReservationsButton,
                signOutButton, roomTableView);
    }

    private void returnToLogin() {
        UserLogin userLogin = new UserLogin(stage);
        userLogin.initializeComponents();
    }

    // Opens the Customer Registration Form
    private void openCustomerRegistration() {
        Stage customerStage = new Stage();
        new CustomerRegistration(customerStage);
    }

    private void openCreateReservation() {
        Stage reservationStage = new Stage();
        new CreateReservation(reservationStage);
    }

    private void setupTableView() {
        TableColumn<Room, Integer> roomNumberCol = new TableColumn<>("Room Number");
        roomNumberCol.setCellValueFactory(data -> data.getValue().getRoomNumber().asObject());

        TableColumn<Room, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(data -> data.getValue().getType());

        TableColumn<Room, Float> rateCol = new TableColumn<>("Rate");
        rateCol.setCellValueFactory(data -> data.getValue().getRate().asObject());

        TableColumn<Room, String> isReservedCol = new TableColumn<>("Reserved");
        isReservedCol.setCellValueFactory(data -> data.getValue().getIsReserved());

        roomTableView.getColumns().addAll(roomNumberCol, typeCol, rateCol, isReservedCol);
    }

    private void checkRoomAvailability() {
        ObservableList<Room> roomList = FXCollections.observableArrayList();
        String query = "SELECT roomNumber, type, rate, isReserved FROM rooms;";

        try (Connection con = DBUtils.establishConnection();
             PreparedStatement statement = con.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                int roomNumber = rs.getInt("roomNumber");
                String type = rs.getString("type");
                float rate = rs.getFloat("rate");
                boolean isReserved = rs.getBoolean("isReserved");

                roomList.add(new Room(roomNumber, type, rate, isReserved));
            }

            roomTableView.setItems(roomList);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Failed to retrieve room data.");
        }
    }

    private void showCustomers() {
        Stage customerStage = new Stage();
        customerStage.setTitle("Customer List");

        TableView<Customer> customerTable = new TableView<>();

        TableColumn<Customer, Number> idCol = new TableColumn<>("Customer ID");
        idCol.setCellValueFactory(data -> data.getValue().getCustomerID());

        TableColumn<Customer, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(data -> data.getValue().getFirstName());

        TableColumn<Customer, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(data -> data.getValue().getLastName());

        TableColumn<Customer, String> phoneCol = new TableColumn<>("Phone Number");
        phoneCol.setCellValueFactory(data -> data.getValue().getPhoneNumber());

        TableColumn<Customer, String> qidCol = new TableColumn<>("QID");
        qidCol.setCellValueFactory(data -> data.getValue().getQid());

        customerTable.getColumns().addAll(idCol, firstNameCol, lastNameCol, phoneCol, qidCol);

        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String query = "SELECT customerID, firstname, lastname, phoneNumber, QID FROM customers";

        try (Connection con = DBUtils.establishConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int customerID = rs.getInt("customerID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String phoneNumber = rs.getString("phoneNumber");
                String qid = Encryption.decrypt(rs.getString("QID"));

                customerList.add(new Customer(customerID, firstname, lastname, phoneNumber, qid));
            }

            customerTable.setItems(customerList);

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Failed to retrieve customer data.");
        }

        VBox customerLayout = new VBox(10);
        customerLayout.setPadding(new Insets(10));
        customerLayout.getChildren().addAll(new Label("Customers"), customerTable);

        Scene scene = new Scene(customerLayout, 600, 400);
        customerStage.setScene(scene);
        customerStage.show();
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}