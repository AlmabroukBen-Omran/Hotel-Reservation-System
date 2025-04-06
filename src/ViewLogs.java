import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewLogs {

    public static void displayLogs() {
        // Create a new Stage for the logs window
        Stage logsStage = new Stage();

        TableView<LogEntry> table = new TableView<>();
        ObservableList<LogEntry> logs = FXCollections.observableArrayList();

        // Define columns for the table
        TableColumn<LogEntry, Integer> idCol = new TableColumn<>("Log ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("logID"));

        TableColumn<LogEntry, String> userCol = new TableColumn<>("Username");
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<LogEntry, String> actionCol = new TableColumn<>("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("actionType"));

        TableColumn<LogEntry, String> timeCol = new TableColumn<>("Timestamp");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        table.getColumns().addAll(idCol, userCol, actionCol, timeCol);

        // Database connection to fetch logs
        Connection con = DBUtils.establishConnection();
        String query = "SELECT * FROM logs ORDER BY timestamp DESC;";

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                logs.add(new LogEntry(
                        rs.getInt("logID"),
                        rs.getString("username"),
                        rs.getString("actionType"),
                        rs.getTimestamp("timestamp").toString()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection(con, stmt); // Close connection and statement properly
        }

        table.setItems(logs);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(table);

        // Set up the new window (Stage)
        Scene scene = new Scene(layout, 720, 400);
        logsStage.setScene(scene);
        logsStage.setTitle("System Logs");
        logsStage.show();
    }
}
