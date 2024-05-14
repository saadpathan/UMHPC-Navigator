package diccnavigator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class JobAnalyzer {

    public void jobAnalysis(String startTimeStr, String endTimeStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            String filePath = "/DICC Navigator/resources/extracted_log.txt";
            Scanner inputStream = new Scanner(new FileInputStream(filePath));
            int count1 = 0, count2 = 0, count3 = 0;
            Date startTime = dateFormat.parse(startTimeStr);
            Date endTime = dateFormat.parse(endTimeStr);

            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                String timestampStr = line.substring(1, 24);
                Date timestamp = dateFormat.parse(timestampStr);

                if (timestamp.after(startTime) && timestamp.before(endTime)) {
                    String b1 = "Allocate";
                    String b2 = "_start_job:";
                    String b3 = "_slurm_rpc_allocate_resources";
                    String c = "done";
                    String d = "error: This association";
                    boolean check1 = line.contains(b1);
                    boolean check2 = line.contains(c);
                    boolean check3 = line.contains(d);
                    boolean check4 = line.contains(b2);
                    boolean check5 = line.contains(b3);

                    if (true) {
                        if (check1 || check4 || check5) {
                            count1++;
                        }
                        if (check2) {
                            count2++;
                        }
                        if (check3) {
                            count3++;
                        }
                    }
                }
            }

            System.out.println("\nNumber of jobs started within the time range\t: " + count1);
            System.out.println("Number of jobs completed within the time range\t: " + count2);
            System.out.println("Number of errors occured within the time range\t: " + count3);

            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
