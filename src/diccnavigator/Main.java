/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass5;


public class Main {

    public static void main(String[] args) {
        String logFilename = "C:\\Users\\ASUS\\OneDrive\\Documents\\DEGREE UM\\SEM 2\\JAVA\\JAVA TUTO & LAB - Netbeans\\ASSIGNMENTpart1\\extracted_log";

        // Part 1: TimeController
        TimeController timeController = new TimeController();
        String startTimeStr = "2022-06-01T01:02:35.148";
        String endTimeStr = "2022-06-01T09:16:23.309";

        int jobsCreated = timeController.getJobCountInTimeRange(logFilename, startTimeStr, endTimeStr, "submit");
        int jobsEnded = timeController.getJobCountInTimeRange(logFilename, startTimeStr, endTimeStr, "complete");

        System.out.println("Number of jobs created within the specified time range: " + jobsCreated);
        System.out.println("Number of jobs ended within the specified time range: " + jobsEnded);

        // Part 2: PartitionAnalyzer
        GPUCounter gpuPartition = new GPUCounter();
        OpteronCounter opteronPartition = new OpteronCounter();
        EPYCCounter epycPartition = new EPYCCounter();

        gpuPartition.processLogFile(logFilename);
        opteronPartition.processLogFile(logFilename);
        epycPartition.processLogFile(logFilename);

        System.out.println("Jobs in GPU Partition: " + gpuPartition.getJobCount());
        System.out.println("Jobs in CPU Opteron Partition: " + opteronPartition.getJobCount());
        System.out.println("Jobs in CPU EPYC Partition: " + epycPartition.getJobCount());

        // Part 3: ErrorAnalyzer
        ErrorAnalyzer errorAnalyzer = new ErrorAnalyzer();
        errorAnalyzer.processLogFile(logFilename);
        System.out.println("Errors occurred by corresponding user:");
        errorAnalyzer.printErrorCounts();


        //Part 4: Average Execution Time 

        TimeAnalyzer tanalyzer = new TimeAnalyzer();
        tanalyzer.averageExecutionTime();
    }
}
