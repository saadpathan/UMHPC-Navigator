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

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LogAnalyzerApp extends Application {

    private final String logFilename = "C:\\Users\\ASUS\\OneDrive\\Documents\\DEGREE UM\\SEM 2\\JAVA\\JAVA TUTO & LAB - Netbeans\\ASSIGNMENTpart1\\extracted_log"; // Update with your log file path

    @Override
    public void start(Stage stage) {
        // Extract data from log file
        Map<String, Integer> jobCountsByPartitions = extractJobCountsByPartitions(logFilename);
        Map<String, Integer> errorsByUsers = extractErrorsByUsers(logFilename);
        double averageExecutionTime = calculateAverageExecutionTime(logFilename);

        // Create TableView to display job counts by partitions
        TableView<JobData> tableView = createTableView(jobCountsByPartitions);

        // Create BarChart to display job counts by partitions
        BarChart<String, Number> barChart = createBarChart(jobCountsByPartitions);

        // Create VBox to hold TableView and BarChart
        VBox vbox = new VBox(tableView, barChart);

        // Create a Label to display average execution time
        Label label = new Label("Average Execution Time: " + averageExecutionTime);

        // Create the scene
        Scene scene = new Scene(new VBox(vbox, label), 800, 600);

        stage.setTitle("Log Analyzer");
        stage.setScene(scene);
        stage.show();
    }

    // Method to extract job counts by partitions
    private Map<String, Integer> extractJobCountsByPartitions(String filename) {
        Map<String, Integer> jobCountsByPartitions = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Partition=")) {
                    String partition = line.split("Partition=")[1].split(" ")[0];
                    jobCountsByPartitions.put(partition, jobCountsByPartitions.getOrDefault(partition, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobCountsByPartitions;
    }

    // Method to extract errors by users
    private Map<String, Integer> extractErrorsByUsers(String filename) {
        Map<String, Integer> errorsByUsers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("error:")) {
                    String user = line.substring(line.indexOf("user='") + 6, line.indexOf("'", line.indexOf("user='") + 6));
                    errorsByUsers.put(user, errorsByUsers.getOrDefault(user, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorsByUsers;
    }
    
    
    
    

    // Method to calculate average execution time
    private double calculateAverageExecutionTime(String filename) {
        double totalExecutionTime = 0;
        int jobCount = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("_job_complete")) {
                    String timestampStr = line.substring(0, line.indexOf(" "));
                    Date timestamp = dateFormat.parse(timestampStr);
                    totalExecutionTime += timestamp.getTime();
                    jobCount++;
                }
            }
        } catch (IOException | java.text.ParseException e) {
            e.printStackTrace();
        }
        return jobCount == 0 ? 0 : totalExecutionTime / jobCount;
    }

    // Method to create TableView
    private TableView<JobData> createTableView(Map<String, Integer> data) {
        TableView<JobData> tableView = new TableView<>();
        ObservableList<JobData> jobData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            jobData.add(new JobData(entry.getKey(), entry.getValue()));
        }
        TableColumn<JobData, String> partitionCol = new TableColumn<>("Partition");
        partitionCol.setCellValueFactory(cellData -> cellData.getValue().partitionProperty());

        TableColumn<JobData, Integer> countCol = new TableColumn<>("Count");
        countCol.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());

        tableView.getColumns().addAll(partitionCol, countCol);
        tableView.setItems(jobData);
        return tableView;
    }

    // Method to create BarChart
    private BarChart<String, Number> createBarChart(Map<String, Integer> data) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Job Counts by Partitions");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Partitions");
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        barChart.getData().add(series);
        return barChart;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//class JobData {
//    private final String partition;
//    private final int count;
//
//    public JobData(String partition, int count) {
//        this.partition = partition;
//        this.count = count;
//    }
//
//    public String getPartition() {
//        return partition;
//    }
//
//    public int getCount() {
//        return count;
//    }
//}


 class JobData {
    private final SimpleStringProperty partition;
    private final SimpleIntegerProperty count;

    public JobData(String partition, int count) {
        this.partition = new SimpleStringProperty(partition);
        this.count = new SimpleIntegerProperty(count);
    }

    public String getPartition() {
        return partition.get();
    }

    public void setPartition(String partition) {
        this.partition.set(partition);
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public SimpleStringProperty partitionProperty() {
        return partition;
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }
}
