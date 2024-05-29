
package ass5;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JobCountData {
    private final StringProperty jobCount;
    private final StringProperty jobStatus;

    public JobCountData(String jobCount, String jobStatus) {
        this.jobCount = new SimpleStringProperty(jobCount);
        this.jobStatus = new SimpleStringProperty(jobStatus);
    }

    public String getJobCount() {
        return jobCount.get();
    }

    public StringProperty jobCountProperty() {
        return jobCount;
    }

    public void setJobCount(String jobCount) {
        this.jobCount.set(jobCount);
    }

    public String getJobStatus() {
        return jobStatus.get();
    }

    public StringProperty jobStatusProperty() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus.set(jobStatus);
    }
}