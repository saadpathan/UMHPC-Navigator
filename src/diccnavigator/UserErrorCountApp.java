/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass5;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserErrorCountApp extends Application {

    private final String logFilename = "C:\\Users\\ASUS\\OneDrive\\Documents\\DEGREE UM\\SEM 2\\JAVA\\JAVA TUTO & LAB - Netbeans\\ASSIGNMENTpart1\\extracted_log"; // Update with your log file path

    @Override
    public void start(Stage stage) {
        // Extract data from log file
        List<ErrorData> errorsData = extractErrorsByUsers(logFilename);

        // Create TableView to display error counts by users
        TableView<ErrorData> tableView = createTableView(errorsData);

        // Create VBox to hold TableView
        VBox vbox = new VBox(tableView);

        // Create the scene
        Scene scene = new Scene(new VBox(vbox), 800, 600);

        stage.setTitle("User Error Count ");
        stage.setScene(scene);
        stage.show();
    }

    // Method to extract error counts by users
    private List<ErrorData> extractErrorsByUsers(String filename) {
        List<ErrorData> errorsData = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("error:")) {
                    String user = extractUserFromLine(line);
                    if (user != null) {
                        boolean userExists = false;
                        for (ErrorData errorData : errorsData) {
                            if (errorData.getUser().equals(user)) {
                                errorData.incrementErrorCount();
                                userExists = true;
                                break;
                            }
                        }
                        if (!userExists) {
                            errorsData.add(new ErrorData(user, 1));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorsData;
    }

    private String extractUserFromLine(String line) {
        int userStartIndex = line.indexOf("user='");
        if (userStartIndex != -1) {
            int userEndIndex = line.indexOf("'", userStartIndex + 6);
            if (userEndIndex != -1) {
                return line.substring(userStartIndex + 6, userEndIndex);
            }
        }
        return null;
    }

    // Method to create TableView
private TableView<ErrorData> createTableView(List<ErrorData> errorsData) {
    TableView<ErrorData> tableView = new TableView<>();
    ObservableList<ErrorData> errorDataList = FXCollections.observableArrayList(errorsData);
    TableColumn<ErrorData, String> userCol = new TableColumn<>("User");
    userCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser()));

    TableColumn<ErrorData, Integer> errorCountCol = new TableColumn<>("Error Count");
    errorCountCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getErrorCount()).asObject());

    tableView.getColumns().addAll(userCol, errorCountCol);
    tableView.setItems(errorDataList);
    return tableView;
}
// Method to create BarChart
    private BarChart<String, Number> createBarChart(List<ErrorData> errorsData) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Error Counts by Users");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (ErrorData errorData : errorsData) {
            series.getData().add(new XYChart.Data<>(errorData.getUser(), errorData.getErrorCount()));
        }
        barChart.getData().add(series);

        return barChart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}






class ErrorData {
    private final String user;
    private int errorCount;

    public ErrorData(String user, int errorCount) {
        this.user = user;
        this.errorCount = errorCount;
    }

    public String getUser() {
        return user;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void incrementErrorCount() {
        errorCount++;
    }
}

