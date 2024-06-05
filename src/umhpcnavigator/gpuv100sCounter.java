package umhpcnavigator;
/*
 *@author Bushra Binti Ismail - 22002586
 */

public class gpuv100sCounter extends PartitionAnalyzer{
    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=gpu-v100s");
    }
}
