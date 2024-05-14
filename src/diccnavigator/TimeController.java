package diccnavigator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeController {

    public static void main(String[] args) {
        // Example usage
        String filename = "/DICC Navigator/resources/extracted_log.txt";
        String startTimeStr = "2022-06-01T01:02:35.148"; // Start time in yyyy-MM-dd'T'HH:mm:ss format
        String endTimeStr = "2022-06-01T09:16:23.309"; // End time in yyyy-MM-dd'T'HH:mm:ss format

        // Call the method to get job counts within the time range
        int jobsCreated = getJobCountInTimeRange(filename, startTimeStr, endTimeStr, "submit");
        int jobsEnded = getJobCountInTimeRange(filename, startTimeStr, endTimeStr, "complete");

        // Print the results
        System.out.println("Number of jobs created within the specified time range: " + jobsCreated);
        System.out.println("Number of jobs ended within the specified time range: " + jobsEnded);
    }

    public static int getJobCountInTimeRange(String filename, String startTimeStr, String endTimeStr, String eventType) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        int jobCount = 0;

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
                    if (eventType.equals(" Allocate") && line.contains("sched: Allocate")) {
                        jobCount++;
                    } else if (eventType.equals("complete") && line.contains("_job_complete")) {
                        jobCount++;
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jobCount;
    }
}
