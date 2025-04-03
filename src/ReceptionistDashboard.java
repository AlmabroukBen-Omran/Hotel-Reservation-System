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
        checkRoomsButton.setOnAction(event -> checkRoomAvailability());

        Button createCustomerButton = new Button("Create Customer Profile");
        createCustomerButton.setOnAction(event -> openCustomerRegistration());

        roomTableView = new TableView<>();
        setupTableView();

        layout.getChildren().addAll(dashboardTitle, checkRoomsButton, createCustomerButton, roomTableView);
    }

    // Opens the Customer Registration Form
    private void openCustomerRegistration() {
        Stage customerStage = new Stage();
        new CustomerRegistration(customerStage);
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}