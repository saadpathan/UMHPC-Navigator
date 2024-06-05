/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ErrorAnalyzer;

/**
 *
 * @author Nur Balqis Syuhada binti Nor Azman-22001887
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeController {

    public int[] getJobCountInTimeRange(String filename, String startTimeStr, String endTimeStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //parse the date string
        int[] jobCounts = new int[2]; // Index 0: Completed jobs, Index 1: Ended jobs

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Extract the timestamp string from the log line. 
                //The timestamp is assumed to be enclosed in square brackets [].
                String timestampStr = line.substring(line.indexOf('[') + 1, line.indexOf(']'));

                // Parse timestamp string to Date object
                Date timestamp = dateFormat.parse(timestampStr);

                // Parse start and end time strings to Date objects
                Date startTime = dateFormat.parse(startTimeStr);
                Date endTime = dateFormat.parse(endTimeStr);

                // Check if the timestamp falls within the specified time range
                if (timestamp.after(startTime) && timestamp.before(endTime)) {
                    if (line.contains("_slurm_rpc_submit_batch_job")) {
                        jobCounts[1]++; // Counting ended jobs
                    } else if (line.contains("_job_complete")) {
                        jobCounts[0]++; // Counting completed jobs
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jobCounts; //return array containing the counts of job completed and ended
        
    }
}