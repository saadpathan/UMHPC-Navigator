package diccnavigator;

public class GPUCounter extends PartitionAnalyzer {

    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=gpu");
    }
}
