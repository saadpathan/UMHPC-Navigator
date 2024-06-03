package umhpcnavigator;

/*
 * @author Bushra Binti Ismail - 22002586
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

abstract class PartitionAnalyzer {

    protected int jobCount = 0;
    abstract boolean isRelevantEntry(String logEntry);

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
