package diccnavigator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ErrorPerUser extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the data for the pie chart
        Map<String, Integer> userData = new HashMap<>();
        userData.put("farhatabjani(3)", 3);
        userData.put("yatyuen.lim(1)", 1);
        userData.put("aah(12)", 12);
        userData.put("lin0618(4)", 4);
        userData.put("janvik(4)", 4);
        userData.put("xinpeng(1)", 1);
        userData.put("kurk(1)", 1);
        userData.put("mk_98(24)", 24);
        userData.put("hva170037(5)", 5);
        userData.put("htt_felicia(21)", 21);
        userData.put("fairus(10)", 10);
        userData.put("han(11)", 11);
        userData.put("f4ww4z(4)", 4);
        userData.put("manoj(4)", 4);
        userData.put("ongkuanhung(1)", 1);
        userData.put("shahreeza(6)", 6);
        userData.put("liew.wei.shiung(3)", 3);
        userData.put("hass(1)", 1);
        userData.put("chiuling(2)", 2);
        userData.put("hongvin(9)", 9);
        userData.put("lobbeytan(3)", 3);
        userData.put("fahmi8(1)", 1);
        userData.put("tingweijing(2)", 2);
        userData.put("aznul(2)", 2);
        userData.put("roland(4)", 4);
        userData.put("noraini(4)", 4);
        // Add more users and their error counts...

        // Create a list of PieChart.Data objects
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        userData.forEach((user, errorCount) -> {
            pieChartData.add(new PieChart.Data(user, errorCount));
        });

        // Create the pie chart
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Errors per User");

        // Create the scene and add the pie chart
        Scene scene = new Scene(pieChart, 800, 600);

        primaryStage.setTitle("Pie Chart Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
