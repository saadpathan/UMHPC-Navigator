package umhpcnavigator;

public class gputitanCounter extends PartitionAnalyzer {
    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=gpu-titan");
    }

   
}
