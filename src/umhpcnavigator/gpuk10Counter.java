package umhpcnavigator;

public class gpuk10Counter extends PartitionAnalyzer{
    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=gpu-k10");
    }
}
