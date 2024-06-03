package umhpcnavigator;

public class EPYCCounter extends PartitionAnalyzer {

    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=cpu-epyc");
    }
}
