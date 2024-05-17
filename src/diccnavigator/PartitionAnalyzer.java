/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass5;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class PartitionAnalyzer {

    protected int jobCount = 0;

    // Method to determine if a job entry belongs to this partition
    abstract boolean isRelevantEntry(String logEntry);

    // Method to read and process the log file
    public void processLogFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (this.isRelevantEntry(line)) {
                    jobCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getJobCount() {
        return jobCount;
    }
}

