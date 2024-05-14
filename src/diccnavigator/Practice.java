package diccnavigator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Practice {

    public static void main(String[] args) throws IOException {
        // Example usage
        String filename = "/DICC Navigator/resources/extracted_log.txt";
        String startTimeStr = "2022-06-01T01:02:35.148"; // Start time in yyyy-MM-dd'T'HH:mm:ss format
        String endTimeStr = "2022-12-16T14:55:46.311"; // End time in yyyy-MM-dd'T'HH:mm:ss format

        // Call the method to get job counts within the time range
        int jobStarted = getJobCountInTimeRange(filename, startTimeStr, endTimeStr, "start");
        int jobDone = getJobCountInTimeRange(filename, startTimeStr, endTimeStr, "done");
        int errorFound = getJobCountInTimeRange(filename, startTimeStr, endTimeStr, "error");

        // Print the results
        System.out.println("Number of jobs started within the specified time range: " + jobStarted);
        System.out.println("Number of jobs done within the specified time range: " + jobDone);
        System.out.println("Number of errors found within the specified time range: " + errorFound);
    }

    public static int getJobCountInTimeRange(String filename, String startTimeStr, String endTimeStr, String eventType) throws FileNotFoundException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        int jobStarted = 0;
        int jobDone = 0;
        int errorFound = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Extract timestamp from the line
                String timestampStr = line.substring(line.indexOf('[') + 1, line.indexOf(']'));

                // Parse timestamp string to Date object
                Date timestamp = dateFormat.parse(timestampStr);

                // Parse start and end time strings to Date objects
                Date startTime = dateFormat.parse(startTimeStr);
                Date endTime = dateFormat.parse(endTimeStr);

                // Check if the timestamp falls within the specified time range and matches the event type
                if (timestamp.after(startTime) && timestamp.before(endTime)) {
                    if (line.contains("Allocate") || line.contains("_start_job:") || line.contains("_slurm_rpc_allocate_resources")) {
                        jobStarted++;
                    }
                    if (line.contains("done")) {
                        jobDone++;
                    }
                    if (line.contains("error: This association")) {
                        errorFound++;
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Depending on the eventType, return the corresponding count
        if (eventType.equals("submit")) {
            return jobStarted;
        } else if (eventType.equals("complete")) {
            return jobDone;
        } else {
            return errorFound;
        }
    }

}
