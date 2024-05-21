package umhpcnavigator;

/*
 * @author Saad Ahmed Pathan - 22114077
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogAnalyzer {

    public void analyzeLogFile(String logFilePath) {
        int terminatedJob = 0;
        int killedJob = 0;
        int backfilledJob = 0;
        int cleanedResources = 0;
        int qosError = 0;
        int downnodeCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Terminate signal")) {
                    terminatedJob++;
                } else if (line.contains("REQUEST_KILL_JOB")) {
                    killedJob++;
                } else if (line.contains("sched/backfill:")) {
                    backfilledJob++;
                } else if (line.contains("cleanup_completing:")) {
                    cleanedResources++;
                } else if (line.contains("error: Invalid qos")) {
                    qosError++;
                } else if (line.contains("Down nodes: umhpc")) {
                    downnodeCount++;
                }
            }

            // Print summary
            System.out.println("Additional Statistical Data~\n");
            System.out.println("Number Of Terminated Jobs : " + terminatedJob);
            System.out.println("Number Of Killed Jobs : " + killedJob);
            System.out.println("Number Of Backfilled Jobs : " + backfilledJob);
            System.out.println("Cleaning Resources Completed : " + cleanedResources);
            System.out.println("Errors By Quality Of Service : " + qosError);
            System.out.println("UMHPC Node Went Down : " + downnodeCount);
        } catch (IOException e) {
            System.err.println("Error reading the log file : " + e.getMessage());
        }
    }
}
