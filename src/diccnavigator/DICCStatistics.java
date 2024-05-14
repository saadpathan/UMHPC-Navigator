package diccnavigator;

import java.util.Arrays;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.BarChart;

public class DICCStatistics extends Application {

    //Main menu
    @Override

    public void start(Stage stage) {

        Label message = new Label("Choose data to be presented");
        message.setFont(new Font(24));

        Button jobsbymonth = new Button("Job Analysis");
        Button jobsbypartition = new Button("Jobs by partition");
        Button averagejobtime = new Button("Average job excecution time");
        Button pieusererror = new Button("Users who did error");
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(evt -> Platform.exit());

        VBox buttonBar = new VBox(20, jobsbymonth, jobsbypartition, averagejobtime, pieusererror, quitButton);
        buttonBar.setAlignment(Pos.CENTER);
        BorderPane root = new BorderPane();
        root.setTop(message);
        root.setCenter(buttonBar);

        Scene scene = new Scene(root, 640, 640);
        stage.setScene(scene);
        stage.setTitle("JavaFX");
        stage.show();

        //Job analysis
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent f) {

                Label message = new Label(" ");
                message.setFont(new Font(24));

                Button barchart = new Button("Bar Chart");
                barchart.setOnAction(eventbarchart);

                Button quitButton = new Button("Back");
                quitButton.setOnAction(evt -> {
                    start(stage);

                });

                VBox buttonBar = new VBox(20, barchart, quitButton);
                buttonBar.setAlignment(Pos.CENTER);
                BorderPane root = new BorderPane();
                root.setTop(message);
                root.setCenter(buttonBar);

                Scene scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                stage.setTitle("Extracted_log");
                stage.show();
            }

            EventHandler<ActionEvent> eventbarchart = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent g) {

                    CategoryAxis xAxis = new CategoryAxis();
                    xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("June", "July", "August",
                            "September", "October", "November", "December")));
                    xAxis.setLabel(" ");

                    NumberAxis yAxis = new NumberAxis();
                    yAxis.setLabel(" ");

                    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
                    barChart.setTitle("Job Analysis");

                    XYChart.Series<String, Number> series1 = new XYChart.Series<>();
                    series1.setName("Job Started");
                    series1.getData().add(new XYChart.Data<>("June", 2382.0));
                    series1.getData().add(new XYChart.Data<>("July", 1367.0));
                    series1.getData().add(new XYChart.Data<>("August", 1601.0));
                    series1.getData().add(new XYChart.Data<>("September", 1399.0));
                    series1.getData().add(new XYChart.Data<>("October", 1876.0));
                    series1.getData().add(new XYChart.Data<>("November", 1389.0));
                    series1.getData().add(new XYChart.Data<>("December", 641.0));

                    XYChart.Series<String, Number> series2 = new XYChart.Series<>();

                    series2.setName("Job Done");
                    series2.getData().add(new XYChart.Data<>("June", 1893.0));
                    series2.getData().add(new XYChart.Data<>("July", 1139.0));
                    series2.getData().add(new XYChart.Data<>("August", 1186.0));
                    series2.getData().add(new XYChart.Data<>("September", 1153.0));
                    series2.getData().add(new XYChart.Data<>("October", 1564.0));
                    series2.getData().add(new XYChart.Data<>("November", 1092.0));
                    series2.getData().add(new XYChart.Data<>("December", 452.0));

                    XYChart.Series<String, Number> series3 = new XYChart.Series<>();
                    series3.setName("Error");
                    series3.getData().add(new XYChart.Data<>("June", 38.0));
                    series3.getData().add(new XYChart.Data<>("July", 0.0));
                    series3.getData().add(new XYChart.Data<>("August", 56.0));
                    series3.getData().add(new XYChart.Data<>("September", 9.0));
                    series3.getData().add(new XYChart.Data<>("October", 5.0));
                    series3.getData().add(new XYChart.Data<>("November", 35.0));
                    series3.getData().add(new XYChart.Data<>("December", 0.0));

                    XYChart.Series<String, Number> series4 = new XYChart.Series<>();
                    series4.setName("Job Cleaned Up");
                    series4.getData().add(new XYChart.Data<>("June", 17.0));
                    series4.getData().add(new XYChart.Data<>("July", 0.0));
                    series4.getData().add(new XYChart.Data<>("August", 15.0));
                    series4.getData().add(new XYChart.Data<>("September", 14.0));
                    series4.getData().add(new XYChart.Data<>("October", 12.0));
                    series4.getData().add(new XYChart.Data<>("November", 53.0));
                    series4.getData().add(new XYChart.Data<>("December", 14.0));

                    XYChart.Series<String, Number> series5 = new XYChart.Series<>();
                    series5.setName("Job Kill Requests");
                    series5.getData().add(new XYChart.Data<>("June", 599.0));
                    series5.getData().add(new XYChart.Data<>("July", 314.0));
                    series5.getData().add(new XYChart.Data<>("August", 414.0));

                    series5.getData().add(new XYChart.Data<>("September", 230.0));
                    series5.getData().add(new XYChart.Data<>("October", 307.0));
                    series5.getData().add(new XYChart.Data<>("November", 309.0));
                    series5.getData().add(new XYChart.Data<>("December", 218.0));

//Setting the data to bar chart
                    barChart.getData().addAll(series1, series2, series3, series4, series5);

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });
                    VBox buttonBar = new VBox(20, back);
                    buttonBar.setAlignment(Pos.BOTTOM_CENTER);

                    Group root = new Group(barChart, buttonBar);
                    Scene scene = new Scene(root, 600, 600);
                    stage.setTitle("Bar Chart");
                    stage.setScene(scene);
                    stage.show();
                }

            };

        };

        //Choose month
        EventHandler<ActionEvent> pieerrormenu = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                Label message = new Label("Choose month");
                message.setFont(new Font(24));

                Button june = new Button("June");
                june.setOnAction(pieerrorjune);

                Button july = new Button("July");
                july.setOnAction(pieerrorjuly);

                Button august = new Button("August");
                august.setOnAction(pieerroraugust);

                Button september = new Button("September");
                september.setOnAction(pieerrorseptember);

                Button october = new Button("October");
                october.setOnAction(pieerroroctober);

                Button november = new Button("November");
                november.setOnAction(pieerrornovember);

                Button december = new Button("December");
                december.setOnAction(pieerrordecember);

                Button overall = new Button("Overall");
                overall.setOnAction(pieerroroverall);

                Button quitButton = new Button("Back");
                quitButton.setOnAction(evt -> {
                    start(stage);

                });

                VBox buttonBar = new VBox(20, june, july, august, september, october, november, december, overall, quitButton);
                buttonBar.setAlignment(Pos.CENTER);
                BorderPane root = new BorderPane();
                root.setTop(message);
                root.setCenter(buttonBar);

                Scene scene = new Scene(root, 600, 400);
                stage.setScene(scene);
                stage.setTitle(" ");
                stage.show();
            }

//pie chart users who did error,month by month
            EventHandler<ActionEvent> pieerrorjune = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    Scene scene = new Scene(new Group());
                    stage.setTitle("Users who did error");
                    stage.setWidth(640);
                    stage.setHeight(640);

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("lobbeytan (3)", 3),
                            new PieChart.Data("tingweijing (2)", 2),
                            new PieChart.Data("f4ww4z (4)", 4),
                            new PieChart.Data("xinpeng (1)", 1),
                            new PieChart.Data("aznul (2)", 2), new PieChart.Data("hass (1)", 1),
                            new PieChart.Data("liew.wei.shiung (3)", 3), new PieChart.Data("roland (4)", 4),
                            new PieChart.Data("shahreeza (6)", 6),
                            new PieChart.Data("janvik (4)", 4),
                            new PieChart.Data("lin0618 (4)", 4),
                            new PieChart.Data("fahmi8 (1)", 1),
                            new PieChart.Data("farhatabjani (3)", 3));

                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Users who did error");

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });

                    VBox buttonBar = new VBox(20, back);

                    ((Group) scene.getRoot()).getChildren().addAll(chart, back);
                    buttonBar.setAlignment(Pos.BOTTOM_CENTER);

                    stage.setScene(scene);
                    stage.show();

                }
            };

            EventHandler<ActionEvent> pieerrorjuly = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    stage.setTitle("July - no erros!");
                    stage.setWidth(640);
                    stage.setHeight(640);

                    Label julynoerror = new Label("July - no errors!");
                    julynoerror.setFont(new Font(24));

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });

                    VBox buttonBar = new VBox(20, back);

                    BorderPane root = new BorderPane();
                    root.setTop(julynoerror);
                    root.setCenter(buttonBar);

                    Scene scene = new Scene(root, 600, 400);
                    stage.setScene(scene);
                    stage.setTitle("JavaFX");
                    stage.show();
                }
            };

            EventHandler<ActionEvent> pieerroraugust = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    Scene scene = new Scene(new Group());
                    stage.setTitle("Users who did error");
                    stage.setWidth(640);
                    stage.setHeight(640);

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("kurk (1)", 1),
                            new PieChart.Data("fairus (7)", 7),
                            new PieChart.Data("manoj (4)", 4),
                            new PieChart.Data("han (8)", 8), new PieChart.Data("aah (12)", 12),
                            new PieChart.Data("mk_98 (24)", 24));

                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Users who did error");

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });

                    VBox buttonBar = new VBox(20, back);

                    ((Group) scene.getRoot()).getChildren().addAll(chart, back);
                    buttonBar.setAlignment(Pos.BOTTOM_CENTER);

                    stage.setScene(scene);
                    stage.show();

                }
            };

            EventHandler<ActionEvent> pieerrorseptember = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    Scene scene = new Scene(new Group());
                    stage.setTitle("Users who did error");
                    stage.setWidth(600);
                    stage.setHeight(400);

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("hva170037 (5)", 5),
                            new PieChart.Data("han (3)", 3),
                            new PieChart.Data("yatyuen.lim (1)", 1));
                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Users who did error");

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });
                    VBox buttonBar = new VBox(20, back);

                    ((Group) scene.getRoot()).getChildren().addAll(chart, back);
                    buttonBar.setAlignment(Pos.BOTTOM_CENTER);

                    stage.setScene(scene);
                    stage.show();

                }
            };

            EventHandler<ActionEvent> pieerroroctober = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    Scene scene = new Scene(new Group());
                    stage.setTitle("Users who did error");
                    stage.setWidth(600);
                    stage.setHeight(400);

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("ongkuanhung (1)", 1),
                            new PieChart.Data("chiuling (1)", 1),
                            new PieChart.Data("fairus (3)", 3));

                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Users who did error");

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });

                    VBox buttonBar = new VBox(20, back);

                    ((Group) scene.getRoot()).getChildren().addAll(chart, back);
                    buttonBar.setAlignment(Pos.BOTTOM_CENTER);

                    stage.setScene(scene);
                    stage.show();

                }
            };

            EventHandler<ActionEvent> pieerrornovember = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    Scene scene = new Scene(new Group());
                    stage.setTitle("Users who did error");
                    stage.setWidth(600);
                    stage.setHeight(400);

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("noraini (4)", 4),
                            new PieChart.Data("hongvin (9)", 9),
                            new PieChart.Data("htt_felicia (21)", 21),
                            new PieChart.Data("chiuling (1)", 1));

                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Users who did error");

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });

                    VBox buttonBar = new VBox(20, back);

                    ((Group) scene.getRoot()).getChildren().addAll(chart, back);
                    buttonBar.setAlignment(Pos.BOTTOM_CENTER);

                    stage.setScene(scene);
                    stage.show();

                }

            };

            EventHandler<ActionEvent> pieerrordecember = new EventHandler<ActionEvent>() {

                public void handle(ActionEvent e) {

                    stage.setTitle("December - no errors!");
                    stage.setWidth(600);
                    stage.setHeight(400);

                    Label decnoerror = new Label("December - no errors!");
                    decnoerror.setFont(new Font(24));

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });

                    VBox buttonBar = new VBox(20, back);

                    BorderPane root = new BorderPane();
                    root.setTop(decnoerror);
                    root.setCenter(buttonBar);

                    Scene scene = new Scene(root, 600, 400);
                    stage.setScene(scene);
                    stage.setTitle("JavaFX");
                    stage.show();

                }

            };

            EventHandler<ActionEvent> pieerroroverall = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {

                    Scene scene = new Scene(new Group());
                    stage.setTitle("Users who did error (Overall)");
                    stage.setWidth(600);
                    stage.setHeight(400);

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("lobbeytan (3)", 3),
                            new PieChart.Data("tingweijing (3)", 2),
                            new PieChart.Data("f4ww4z (4)", 4),
                            new PieChart.Data("xinpeng (1)", 1),
                            new PieChart.Data("aznul (2)", 2), new PieChart.Data("hass (1)", 1),
                            new PieChart.Data("liew.wei.shiung (3)", 3), new PieChart.Data("roland (4)", 4),
                            new PieChart.Data("shahreeza (6)", 6),
                            new PieChart.Data("janvik (4)", 4),
                            new PieChart.Data("lin0618 (4)", 4),
                            new PieChart.Data("fahmi8 (1)", 1),
                            new PieChart.Data("farhatabjani (3)", 3),
                            new PieChart.Data("kurk (1)", 1),
                            new PieChart.Data("fairus (10)", 10),
                            new PieChart.Data("manoj (4)", 4),
                            new PieChart.Data("han (11)", 11), new PieChart.Data("aah (12)", 12), new PieChart.Data("mk_98 (24)", 24),
                            new PieChart.Data("hva170037 (5)", 5),
                            new PieChart.Data("yatyuen.lim (1)", 1),
                            new PieChart.Data("ongkuanhung (1)", 1),
                            new PieChart.Data("noraini (4)", 4),
                            new PieChart.Data("hongvin (9)", 9),
                            new PieChart.Data("htt_felicia (21)", 21),
                            new PieChart.Data("chiuling (2)", 2));

                    final PieChart chart = new PieChart(pieChartData);
                    chart.setTitle("Users who did error (Overall)");

                    Button back = new Button("Back");
                    back.setOnAction(evt -> {
                        start(stage);

                    });

                    VBox buttonBar = new VBox(20, back);

                    ((Group) scene.getRoot()).getChildren().addAll(chart, back);
                    buttonBar.setAlignment(Pos.BOTTOM_CENTER);

                    stage.setScene(scene);
                    stage.show();

                }

            };

        };

        EventHandler<ActionEvent> partition = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Scene scene = new Scene(new Group());
                stage.setTitle("Jobs by partition");
                stage.setWidth(550);
                stage.setHeight(550);

                ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                        new PieChart.Data("cpu-opteron (4920)", 4920),
                        new PieChart.Data("gpu-k10 (439)", 439),
                        new PieChart.Data("cpu-epyc (3015)", 3015),
                        new PieChart.Data("gpu-titan (673)", 673),
                        new PieChart.Data("gpu-k40c (301)", 301),
                        new PieChart.Data("gpu-v100s (618)", 618));
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle("Jobs by partition");

                Button back = new Button("Back");
                back.setOnAction(evt -> {
                    start(stage);

                });

                VBox buttonBar = new VBox(20, back);

                ((Group) scene.getRoot()).getChildren().addAll(chart, back);
                buttonBar.setAlignment(Pos.BOTTOM_CENTER);

                stage.setScene(scene);
                stage.show();

            }

        };

        EventHandler<ActionEvent> average = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                Label avg = new Label("Average excecution time is 97333.80 seconds = 27 hour(s) 2 minute(s) 13 second(s).");
                avg.setFont(new Font(24));

                Button back = new Button("Back");
                back.setOnAction(evt -> {
                    start(stage);

                });

                BorderPane root = new BorderPane();
                root.setTop(avg);
                root.setCenter(back);

                Scene scene = new Scene(root, 1024, 400);
                stage.setScene(scene);
                stage.setTitle("Average job execution time");
                stage.show();

            }

        };

        jobsbymonth.setOnAction(event);
        jobsbypartition.setOnAction(partition);
        pieusererror.setOnAction(pieerrormenu);
        averagejobtime.setOnAction(average);

    }

    public static void main(String[] args) {
        launch();
    }
}
