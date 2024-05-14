package diccnavigator;

import static diccnavigator.TimeController.getJobCountInTimeRange;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DICCNavigator {

    public static void main(String[] args) throws InterruptedException {

        System.out.print(repeatChar('-', 60));
        System.out.println();

        System.out.println("Welcome to DICC Navigator!");

        System.out.print(repeatChar('-', 60));
        System.out.println("\n");

        String descriptionPath = "/DICC Navigator/resources/description.txt";

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
        //pressEnter();
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
            Scanner sc = new Scanner(System.in);;
            choice = sc.nextInt();

            if (choice == 1) {
                System.out.print(repeatChar('-', 60));
                System.out.println("\n");
                TimeController tc = new TimeController();
                String filename = "/DICC Navigator/resources/extracted_log.txt";
                String startTimeStr = "2022-06-01T01:02:35.148"; // Start time in yyyy-MM-dd'T'HH:mm:ss format
                String endTimeStr = "2022-12-16T14:55:46.311"; // End time in yyyy-MM-dd'T'HH:mm:ss format

                // Call the method to get job counts within the time range
                int jobsCreated = tc.getJobCountInTimeRange(filename, startTimeStr, endTimeStr, "submit");
                int jobsEnded = tc.getJobCountInTimeRange(filename, startTimeStr, endTimeStr, "complete");

                // Print the results
                System.out.println("Number of jobs created within the specified time range: " + jobsCreated);
                System.out.println("Number of jobs ended within the specified time range: " + jobsEnded);

                System.out.println();
                System.out.print(repeatChar('-', 60));
                System.out.println();
            } else if (choice == 2) {
                //Main.runAnalysis("/DICC Navigator/resources/extracted_log.txt2"
                //+ "");
            } else if (choice == 3) {
                System.out.println("option 3");
            } else {
                System.out.println("Thank You");
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

}
