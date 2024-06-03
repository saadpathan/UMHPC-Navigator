package umhpcnavigator;

public class gpuv100sCounter extends PartitionAnalyzer{
    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=gpu-v100s");
    }
}
