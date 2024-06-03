package umhpcnavigator;

public class gpuk40cCounter extends PartitionAnalyzer{
    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=gpu-k40c");
    }
}
