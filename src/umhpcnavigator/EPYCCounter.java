package umhpcnavigator;
/*
 *@author Bushra Binti Ismail - 22002586
 */

public class EPYCCounter extends PartitionAnalyzer {

    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=cpu-epyc");
    }
}
