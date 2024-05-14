package diccnavigator;

import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {

        /*Scanner sc = new Scanner(System.in);

        System.out.print("Enter Start Time : ");
        //Type in yyyy-MM-dd'T'HH:mm:ss format
        String startTimeStr = sc.next();

        System.out.print("Enter End Time : ");
        //Type in yyyy-MM-dd'T'HH:mm:ss format
        String endTimeStr = sc.next();

        JobAnalyzer analyzer = new JobAnalyzer();
        analyzer.jobAnalysis(startTimeStr, endTimeStr);
         */
        
        
        /*System.out.println("Errors occured by corresponding user: \n");
        String logFilename = "/DICC Navigator/resources/extracted_log.txt";
        ErrorAnalyzer logProcessor = new ErrorAnalyzer();

        logProcessor.processLogFile(logFilename);
        logProcessor.printErrorCounts();
         */
 

        /*String logFilename = "/DICC Navigator/resources/extracted_log.txt";

        GPUCounter gpuPartition = new GPUCounter();
        OpteronCounter opteronPartition = new OpteronCounter();
        EPYCCounter epycPartition = new EPYCCounter();

        gpuPartition.processLogFile(logFilename);
        opteronPartition.processLogFile(logFilename);
        epycPartition.processLogFile(logFilename);

        System.out.println("Jobs By GPU Partition : " + gpuPartition.getJobCount());
        System.out.println("Jobs By EPYC Partition : " + epycPartition.getJobCount());
        System.out.println("Jobs By Opteron Partition : " + opteronPartition.getJobCount());
        */
        
        TimeAnalyzer tanalyzer = new TimeAnalyzer();
        tanalyzer.averageExecutionTime();
        
    }

}
