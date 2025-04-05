import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManagerDashboard {
    private Stage stage;
    private VBox layout;

    public ManagerDashboard(Stage primaryStage) {
        this.stage = primaryStage;
        initializeComponents();
    }

    public void showManagerDashboardScene() {
        stage.setTitle("Manager Dashboard");
        stage.setScene(new Scene(layout, 1520, 500));
        stage.show();
    }

    private void initializeComponents() {
        layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label dashboardTitle = new Label("Manager Dashboard");
        dashboardTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button viewMaintenanceReports = new Button("View Maintenance Reports");
        viewMaintenanceReports.setOnAction(e -> loadReportsByType("Maintenance"));

        Button viewFeedbackReports = new Button("View Feedback Reports");
        viewFeedbackReports.setOnAction(e -> loadReportsByType("Feedback"));

        layout.getChildren().addAll(dashboardTitle, viewMaintenanceReports, viewFeedbackReports);
    }

    private void loadReportsByType(String type) {
        TableView<Report> reportTable = new TableView<>();

        TableColumn<Report, Integer> idCol = new TableColumn<>("Report ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("reportID"));

        TableColumn<Report, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Report, String> submittedByCol = new TableColumn<>("Submitted By");
        submittedByCol.setCellValueFactory(new PropertyValueFactory<>("submittedBy"));

        TableColumn<Report, String> dateCol = new TableColumn<>("Date & Time");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("creationDateAndTime"));

        TableColumn<Report, String> contentCol = new TableColumn<>("Content");
        contentCol.setCellValueFactory(new PropertyValueFactory<>("content"));

        TableColumn<Report, Void> deleteCol = new TableColumn<>("Action");
        deleteCol.setCellFactory(col -> new TableCell<>() {
            private final Button deleteBtn = new Button("Delete");

            {
                deleteBtn.setOnAction(e -> {
                    Report report = getTableView().getItems().get(getIndex());
                    deleteReport(report.getReportID());
                    getTableView().getItems().remove(report);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteBtn);
                }
            }
        });

        reportTable.getColumns().addAll(idCol, typeCol, submittedByCol, dateCol, contentCol, deleteCol);

        ObservableList<Report> reportList = FXCollections.observableArrayList();

        String query = "SELECT * FROM reports WHERE type = ?";
        try (Connection con = DBUtils.establishConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                reportList.add(new Report(
                        rs.getInt("reportID"),
                        rs.getString("type"),
                        rs.getString("submittedBy"),
                        rs.getString("creationDateAndTime"),
                        rs.getString("content")
                ));
            }

            reportTable.setItems(reportList);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Unable to load reports.");
        }

        VBox tableContainer = new VBox(10, new Label(type + " Reports"), reportTable);
        layout.getChildren().add(tableContainer);
    }

    private void deleteReport(int reportID) {
        String query = "DELETE FROM reports WHERE reportID = ?";
        try (Connection con = DBUtils.establishConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, reportID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Unable to delete report.");
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
