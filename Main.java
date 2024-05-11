package diccnavigator;
/*
Saad Ahmed Pathan- 22114077
    */
public class Main {

    public static void main(String[] args) {
        String logFilename = "D:\\Semester 02\\WIX 1002\\DICC Navigator\\DICC Navigator\\src\\diccnavigator\\extracted_log.txt";

        GPUCounter gpuPartition = new GPUCounter();
        OpteronCounter opteronPartition = new OpteronCounter();
        EPYCCounter epycPartition = new EPYCCounter();

        gpuPartition.processLogFile(logFilename);
        opteronPartition.processLogFile(logFilename);
        epycPartition.processLogFile(logFilename);

        System.out.println("Jobs in GPU V100s Partition: " + gpuPartition.getJobCount());
        System.out.println("Jobs in CPU Opteron Partition: " + opteronPartition.getJobCount());
        System.out.println("Jobs in CPU EPYC Partition: " + epycPartition.getJobCount());

        System.out.println("\n\n");
        String testFilename = "D:\\Semester 02\\WIX 1002\\DICC Navigator\\DICC Navigator\\src\\diccnavigator\\test.txt";
        ErrorAnalyzer logProcessor = new ErrorAnalyzer();

        logProcessor.processLogFile(logFilename);
        logProcessor.printErrorCounts();
    }

}
