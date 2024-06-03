package umhpcnavigator;

public class OpteronCounter extends PartitionAnalyzer {

    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=cpu-opteron");
    }
}
