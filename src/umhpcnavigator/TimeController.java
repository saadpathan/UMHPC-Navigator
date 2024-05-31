/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication2;

/**
 *
 * @author Nazmanmd
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeController {

    public int getJobCountInTimeRange(String filename, String startTimeStr, String endTimeStr, String eventType) {
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
                    if (eventType.equals("submit") && line.contains("_slurm_rpc_submit_batch_job")) {
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

