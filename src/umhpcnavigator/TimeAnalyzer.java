package umhpcnavigator;

/*
 * @author Saad Ahmed Pathan - 22114077
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TimeAnalyzer {

    public boolean isNumeric(String str) {
        return str != null && str.matches("[0-9.]+");
    }

    public double findDifference(String start_In_Day,
            String start_In_Hour, String start_In_Minute, String start_In_Second, String end_In_Day, String end_In_Hour, String end_In_Minute, String end_In_Second) {

        double start_s = Double.parseDouble(start_In_Second);
        double start_m = Double.parseDouble(start_In_Minute);
        double start_h = Double.parseDouble(start_In_Hour);
        double start_d = Double.parseDouble(start_In_Day);
        double end_s = Double.parseDouble(end_In_Second);
        double end_m = Double.parseDouble(end_In_Minute);
        double end_h = Double.parseDouble(end_In_Hour);
        double end_d = Double.parseDouble(end_In_Day);

        double difference_In_Second;

        double difference_In_Minute;
        double difference_In_Hour;
        double difference_In_Day;

        if (end_s > start_s) {
            difference_In_Second = end_s - start_s;
        } else {
            difference_In_Second = start_s - end_s;
        }

        if (end_m > start_m) {
            difference_In_Minute = end_m - start_m;
        } else {
            difference_In_Minute = start_m - end_m;
        }

        if (end_h > start_h) {
            difference_In_Hour = end_h - start_h;
        } else {
            difference_In_Hour = start_h - end_h;
        }

        if (end_d > start_d) {
            difference_In_Day = end_d - start_d;
        } else {
            difference_In_Day = start_d - end_d;
        }

        double execution_time = (difference_In_Day * 24 * 60 * 60)
                + (difference_In_Hour * 60 * 60)
                + (difference_In_Minute * 60)
                + (difference_In_Second);
        return execution_time;
    }

    public void averageExecutionTime() {
        try {
            String filePath = "/DICC Navigator/resources/extracted_log.txt";
            Scanner inputStream = new Scanner(new FileInputStream(filePath));
            String s_jobId;
            String d_jobId;
            int jobIdDone = 0;
            String endTime = null;
            HashMap<Integer, String> jobIdMap = new HashMap<Integer, String>();
            String start_In_Day = null;
            String start_In_Hour = null;

            String start_In_Minute = null;
            String start_In_Second = null;
            String end_In_Day = null;
            String end_In_Hour = null;
            String end_In_Minute = null;
            String end_In_Second = null;
            double executionTime;
            double total = 0;
            int count = 0;

            for (int i = 42802; i <= 54090; i++) {
                for (int j = 0; j < 2; j++) {
                    while (inputStream.hasNextLine()) {
                        String line = inputStream.nextLine();
                        String b = "_slurm_rpc_submit_batch_job";
                        String c = "done";
                        boolean check1 = line.contains(b);
                        boolean check2 = line.contains(c);

                        if (line.length() >= 66) {
                            s_jobId = line.substring(61, 66);
                            if (isNumeric(s_jobId)) {
                                int jobIdSubmitted = Integer.parseInt(s_jobId);
                                if (check1) {
                                    String submissionTime = line.substring(1, 24);
                                    jobIdMap.put(jobIdSubmitted, submissionTime);
                                }
                            }
                        }

                        if (check2) {
                            d_jobId = line.substring(47, 52);
                            if (isNumeric(d_jobId)) {
                                jobIdDone = Integer.parseInt(d_jobId);
                            }

                            if (jobIdMap.containsKey(jobIdDone)) {
                                endTime = line.substring(1, 24);

                                start_In_Day = jobIdMap.get(jobIdDone).substring(8, 10);
                                start_In_Hour = jobIdMap.get(jobIdDone).substring(11, 13);
                                start_In_Minute = jobIdMap.get(jobIdDone).substring(14, 16);
                                start_In_Second = jobIdMap.get(jobIdDone).substring(17, 23);

                                end_In_Day = endTime.substring(8, 10);
                                end_In_Hour = endTime.substring(11, 13);
                                end_In_Minute = endTime.substring(14, 16);
                                end_In_Second = endTime.substring(17, 23);

                                executionTime = findDifference(start_In_Day, start_In_Hour, start_In_Minute, start_In_Second, end_In_Day, end_In_Hour, end_In_Minute, end_In_Second);

                                total += executionTime;
                                count++;
                            }
                        }
                    }
                }
            }
            
            double average = total / count;
            System.out.printf("Average Execution Time : %.2f seconds\n", average);

            int hour, minute, second;
            hour = (int) average / (1 * 60 * 60);
            minute = (int) average % (1 * 60 * 60) / 60;
            second = (int) average % (1 * 60 * 60) % 60;
            System.out.println("Average Execution Time : " + hour + " hours " + minute + " minutes " + second + " seconds");

            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.out.println();
        }
    }
}
