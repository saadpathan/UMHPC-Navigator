package umhpcnavigator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class UMHPCNavigator extends Application {

    public static void main(String[] args) throws InterruptedException {

        System.out.print(repeatChar('-', 60));
        System.out.println();

        System.out.println("Welcome to UMHPC Navigator!");

        System.out.print(repeatChar('-', 60));
        System.out.println("\n");

        String descriptionPath = "/UMHPC Navigator/resources/description.txt";
        String teamdetailsPath = "/UMHPC Navigator/resources/teamdetails.txt";
        String logFilename = "/UMHPC Navigator/resources/extracted_log.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(descriptionPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.print(repeatChar('-', 60));
        System.out.println("\nPlease wait .....");
        Thread.sleep(2000);
        System.out.print(repeatChar('-', 60));
        System.out.println("\n");

        int choice = 0;

        while (choice < 7) {
            showOptions();
            System.out.println();
            System.out.print(repeatChar('-', 60));
            System.out.println();
            System.out.print("Choose Your Option: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            if (choice == 1) {

                System.out.print(repeatChar('-', 60));
                System.out.println("\n");
                Scanner sc1 = new Scanner(System.in);

                System.out.print("Enter Start Time : ");
                //Type in yyyy-MM-dd'T'HH:mm:ss format
                String startTimeStr = sc1.next();

                System.out.print("Enter End Time : ");
                //Type in yyyy-MM-dd'T'HH:mm:ss format
                String endTimeStr = sc1.next();

                JobAnalyzer analyzer = new JobAnalyzer();
                analyzer.jobAnalysis(startTimeStr, endTimeStr);
                System.out.println();
                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

            } else if (choice == 2) {

                System.out.print(repeatChar('-', 60));
                System.out.println("\n");
                GPUCounter gpuPartition = new GPUCounter();
                OpteronCounter opteronPartition = new OpteronCounter();
                EPYCCounter epycPartition = new EPYCCounter();

                gpuv100sCounter gpuv100sPartition = new gpuv100sCounter();
                gpuk40cCounter gpuk40cPartition = new gpuk40cCounter();
                gpuk10Counter gpuk10Partition = new gpuk10Counter();
                gputitanCounter gputitanPartition = new gputitanCounter();

                gpuPartition.processLogFile(logFilename);
                opteronPartition.processLogFile(logFilename);
                epycPartition.processLogFile(logFilename);

                gpuv100sPartition.processLogFile(logFilename);
                gpuk40cPartition.processLogFile(logFilename);
                gpuk10Partition.processLogFile(logFilename);
                gputitanPartition.processLogFile(logFilename);

                System.out.println("Jobs By GPU Partition : " + gpuPartition.getJobCount());
                System.out.println("Jobs By EPYC Partition : " + epycPartition.getJobCount());
                System.out.println("Jobs By Opteron Partition : " + opteronPartition.getJobCount());

                System.out.println("Jobs in gpu-v100s Partition : " + gpuv100sPartition.getJobCount());
                System.out.println("Jobs in gpu-k40c Partition : " + gpuk40cPartition.getJobCount());
                System.out.println("Jobs in gpu-k10 Partition : " + gpuk10Partition.getJobCount());
                System.out.println("Jobs in gpu-titan Partition : " + gputitanPartition.getJobCount());

                System.out.println();
                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

            } else if (choice == 3) {

                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

                System.out.println("Errors occured by corresponding user: \n");
                ErrorAnalyzer logProcessor = new ErrorAnalyzer();

                logProcessor.processLogFile(logFilename);
                logProcessor.printErrorCounts();

                System.out.println();
                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

            } else if (choice == 4) {

                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

                TimeAnalyzer tanalyzer = new TimeAnalyzer();
                tanalyzer.averageExecutionTime();

                System.out.println();
                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

            } else if (choice == 5) {

                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

                // Launch the JavaFX application
                launch(args);

            } else if (choice == 6) {

                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

                LogAnalyzer lanalyzer = new LogAnalyzer();
                lanalyzer.analyzeLogFile(logFilename);

                System.out.println();
                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

            } else {

                System.out.print(repeatChar('-', 60));
                System.out.println("\n\n");
                System.out.print(repeatChar('-', 60));

                System.out.println();
                System.out.println("Thank You for using UMHPC Navigator, Bye Bye!");
                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

                System.out.println();
                System.out.print(repeatChar('-', 60));
                System.out.println();
                System.out.println("This Application Is Developed By Following Team~");
                System.out.print(repeatChar('-', 60));
                System.out.println("\n");

                try (BufferedReader reader = new BufferedReader(new FileReader(teamdetailsPath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                    reader.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static void showOptions() {
        System.out.println("Choose Your Option From Below:");
        System.out.println("1. Display The Number Of Jobs In A Specific Time Range");
        System.out.println("2. Display The Number Of Jobs By Partitions");
        System.out.println("3. Display The Number Of Errors And Responsible Users");
        System.out.println("4. Display The Average Execution Time Of The Jobs At UMHPC");
        System.out.println("5. Display The Overall Extracted Useful Information");
        System.out.println("6. Display The Extra Statistical Data");
        System.out.println("7. Exit");
    }

    @Override
    public void start(Stage primaryStage) {
        CombinedCharts combinedCharts = new CombinedCharts();
        combinedCharts.start(primaryStage);
    }
}
