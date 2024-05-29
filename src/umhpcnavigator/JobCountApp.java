
package ass5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JobCountApp extends Application {

    @Override
    public void start(Stage stage) {
        // Define the parameters for the time range and event type
        String filename = "C:\\Users\\admin\\Documents\\NetBeansProjects\\Project\\build\\classes\\project\\extracted_log"; // Update with your log file path
        String startTimeStr = "2022-06-01T01:02:35.148"; // Update with your start time
        String endTimeStr = "2022-12-16T14:55:46.311"; // Update with your end time
        String eventType = "complete"; // Update with your event type ("submit" or "complete")

        // Create TimeController instance
        TimeController timeController = new TimeController();

        // Get job count and status in the specified time range and event type
        int[] jobCounts = timeController.getJobCountInTimeRange(filename, startTimeStr, endTimeStr);

        // Create TableView to display the job count and status
        TableView<JobCountData> tableView = createTableView(jobCounts);

        // Create the scene
        Scene scene = new Scene(new VBox(tableView), 400, 300);

        stage.setTitle("Job Count App");
        stage.setScene(scene);
        stage.show();
    }

    // Method to create TableView
    private TableView<JobCountData> createTableView(int[] jobCounts) {
        TableView<JobCountData> tableView = new TableView<>();
        TableColumn<JobCountData, String> jobCountCol = new TableColumn<>("Job Count");
        jobCountCol.setCellValueFactory(cellData -> cellData.getValue().jobCountProperty());

        TableColumn<JobCountData, String> jobStatusCol = new TableColumn<>("Job Status");
        jobStatusCol.setCellValueFactory(cellData -> cellData.getValue().jobStatusProperty());

        tableView.getColumns().addAll(jobCountCol, jobStatusCol);

        // Add data to the table for completed jobs
        tableView.getItems().add(new JobCountData(Integer.toString(jobCounts[0]), "Completed"));
        // Add data to the table for ended jobs
        tableView.getItems().add(new JobCountData(Integer.toString(jobCounts[1]), "Ended"));

        return tableView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
