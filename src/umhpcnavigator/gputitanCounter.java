package umhpcnavigator;
/*
 *@author Bushra Binti Ismail - 22002586
 */

public class gputitanCounter extends PartitionAnalyzer {
    @Override
    boolean isRelevantEntry(String logEntry) {
        return logEntry.contains("Partition=gpu-titan");
    }

   
}
