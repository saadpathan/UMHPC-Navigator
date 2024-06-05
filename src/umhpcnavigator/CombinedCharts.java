package umhpcnavigator;

/*
 * @author Nurain Fatihah Binti Azrai - 22002298
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CombinedCharts extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        // Create navigation buttons
        Button overallDataButton = new Button("Overall Data");
        Button partitionDataButton = new Button("Partition Data");
        Button userErrorDataButton = new Button("User Error Data");
        Button additionalDataButton = new Button("Additional Data");
        Button exitButton = new Button("Exit");

        // Set button actions
        overallDataButton.setOnAction(e -> displayOverallData(primaryStage));
        partitionDataButton.setOnAction(e -> displayPartitionData(primaryStage));
        userErrorDataButton.setOnAction(e -> displayUserErrorData(primaryStage));
        additionalDataButton.setOnAction(e -> displayAdditionalData(primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());

        // Create VBox layout and add buttons
        VBox vBox = new VBox(20, overallDataButton, partitionDataButton, userErrorDataButton, additionalDataButton, exitButton);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 800, 500);

        primaryStage.setTitle("Navigation Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayOverallData(Stage stage) {
        int jobStarted = 10655;
        int jobCompleted = 8479;
        int errorOccurred = 143;
        int gpuPartition = 1951;
        int epycPartition = 2756;
        int opteronPartition = 4509;

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Categories");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Count");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Overview Of Log File");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Job Started", jobStarted));
        series.getData().add(new XYChart.Data<>("Job Completed", jobCompleted));
        series.getData().add(new XYChart.Data<>("Error Occurred", errorOccurred));
        series.getData().add(new XYChart.Data<>("GPU Partition", gpuPartition));
        series.getData().add(new XYChart.Data<>("EPYC Partition", epycPartition));
        series.getData().add(new XYChart.Data<>("Opteron Partition", opteronPartition));

        barChart.getData().add(series);

        Button backButton = new Button("Back");
        backButton.setAlignment(Pos.CENTER);
        backButton.setOnAction(e -> start(stage));

        VBox vBox = new VBox(10, barChart, backButton);
        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(backButton, new javafx.geometry.Insets(10, 0, 10, 0));
        backButton.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Overall Data");
        stage.show();
    }

    private void displayPartitionData(Stage stage) {
        int gpuV100s = 588;
        int gpuK10 = 428;
        int gpuK40c = 295;
        int gpuTitan = 640;
        int cpuOpteron = 4509;
        int cpuEpyc = 2756;
        int gpuTotal = 1951;

        PieChart pieChart = new PieChart();
        pieChart.setTitle("Jobs By Partition");

        pieChart.getData().add(new PieChart.Data("GPU V100s(588)", gpuV100s));
        pieChart.getData().add(new PieChart.Data("GPU K10(428)", gpuK10));
        pieChart.getData().add(new PieChart.Data("GPU K40c(295)", gpuK40c));
        pieChart.getData().add(new PieChart.Data("GPU Titan(640)", gpuTitan));
        pieChart.getData().add(new PieChart.Data("GPU Total(1951)", gpuTotal));
        pieChart.getData().add(new PieChart.Data("CPU Opteron(4509)", cpuOpteron));
        pieChart.getData().add(new PieChart.Data("CPU EPYC(2756)", cpuEpyc));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> start(stage));

        VBox vBox = new VBox(10, pieChart, backButton);

        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(backButton, new javafx.geometry.Insets(10, 0, 10, 0));
        backButton.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Partition Data");
        stage.show();
    }

    private void displayUserErrorData(Stage stage) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("farhatabjani(3)", 3),
                new PieChart.Data("yatyuen.lim(1)", 1),
                new PieChart.Data("aah(12)", 12),
                new PieChart.Data("lin0618(4)", 4),
                new PieChart.Data("janvik(4)", 4),
                new PieChart.Data("xinpeng(1)", 1),
                new PieChart.Data("kurk(1)", 1),
                new PieChart.Data("mk_98(24)", 24),
                new PieChart.Data("hva170037(5)", 5),
                new PieChart.Data("htt_felicia(21)", 21),
                new PieChart.Data("fairus(10)", 10),
                new PieChart.Data("han(11)", 11),
                new PieChart.Data("f4ww4z(4)", 4),
                new PieChart.Data("manoj(4)", 4),
                new PieChart.Data("ongkuanhung(1)", 1),
                new PieChart.Data("shahreeza(6)", 6),
                new PieChart.Data("liew.wei.shiung(3)", 3),
                new PieChart.Data("hass(1)", 1),
                new PieChart.Data("chiuling(2)", 2),
                new PieChart.Data("hongvin(9)", 9),
                new PieChart.Data("lobbeytan(3)", 3),
                new PieChart.Data("fahmi8(1)", 1),
                new PieChart.Data("tingweijing(2)", 2),
                new PieChart.Data("aznul(2)", 2),
                new PieChart.Data("roland(4)", 4),
                new PieChart.Data("noraini(4)", 4)
        );

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Errors Per User");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> start(stage));

        VBox vBox = new VBox(10, pieChart, backButton);

        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(backButton, new javafx.geometry.Insets(10, 0, 10, 0));
        backButton.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox, 800, 500);
        stage.setScene(scene);
        stage.setTitle("User Error Data");
        stage.show();
    }

    private void displayAdditionalData(Stage stage) {
        int terminatedJobs = 4;
        int killedJobs = 2391;
        int backfilledJobs = 750;
        int cleanedResources = 125;
        int qosErrors = 14;
        int downNodes = 4;

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Categories");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Count");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Additional Useful Data");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Terminated Jobs", terminatedJobs));
        series.getData().add(new XYChart.Data<>("Killed Jobs", killedJobs));
        series.getData().add(new XYChart.Data<>("Backfilled Jobs", backfilledJobs));
        series.getData().add(new XYChart.Data<>("Cleaning Resources", cleanedResources));
        series.getData().add(new XYChart.Data<>("QoS Errors", qosErrors));
        series.getData().add(new XYChart.Data<>("Down Nodes", downNodes));

        barChart.getData().add(series);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> start(stage));

        VBox vBox = new VBox(10, barChart, backButton);

        vBox.setAlignment(Pos.CENTER);
        VBox.setMargin(backButton, new javafx.geometry.Insets(10, 0, 10, 0));
        backButton.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Additional Data");
        stage.show();
    }    

}
