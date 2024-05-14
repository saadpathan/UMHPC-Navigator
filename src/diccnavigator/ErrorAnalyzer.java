package diccnavigator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ErrorAnalyzer {

    private Map<String, Integer> userErrorCount;
    private int totalErrors;

    public ErrorAnalyzer() {
        userErrorCount = new HashMap<>();
        totalErrors = 0;
    }

    public void processLogFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("error: This association")) {
                    updateUserErrorCount(line);
                    totalErrors++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateUserErrorCount(String logEntry) {
        String prefix = "user='";
        int start = logEntry.indexOf(prefix);

        if (start != -1) {
            start += prefix.length();
            int end = logEntry.indexOf("'", start);

            if (end != -1) {
                String user = logEntry.substring(start, end);
                userErrorCount.put(user, userErrorCount.getOrDefault(user, 0) + 1);
            } else {
                //System.out.println("End index not found for user in log entry: " + logEntry);
            }
        } else {
            //System.out.println("User prefix not found in log entry: " + logEntry);
        }
    }

    public void printErrorCounts() {
        for (String user : userErrorCount.keySet()) {
            System.out.println("[user-" + user + ", error-occurred " + userErrorCount.get(user) + "]");
        }

        System.out.println("\nTotal Jobs Causing Errors : " + totalErrors);
        System.out.println("Total Users With Errors : " + userErrorCount.size());
    }
}
