package umhpcnavigator;
/*
 *@author Bushra Binti Ismail - 22002586
 */

public class OpteronCounter extends PartitionAnalyzer {

    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=cpu-opteron");
    }
}
