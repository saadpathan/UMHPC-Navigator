# UMHPC Navigator

Welcome to the UMHPC Navigator GitHub repository! This project is designed to analyze and visualize job execution data on a High-Performance Computing (HPC) cluster, specifically tailored for the University of Management and High-Performance Computing (UMHPC). The UMHPC Navigator is a Java application that offers various analytical tools to assist researchers and system administrators in monitoring and optimizing the performance of their HPC resources.

## Features

### Main Menu Options
1. **Display The Number Of Jobs In A Specific Time Range**:
   - Input the start and end times to analyze the number of jobs executed within that period.
   
2. **Display The Number Of Jobs By Partitions**:
   - Analyze job distribution across different HPC partitions such as GPU, EPYC, Opteron, and specific GPU models (V100s, K40c, K10, Titan).

3. **Display The Number Of Errors And Responsible Users**:
   - Identify and count errors that occurred during job executions, and attribute these errors to the corresponding users.

4. **Display The Average Execution Time Of The Jobs At UMHPC**:
   - Compute and display the average execution time of jobs across the HPC cluster.

5. **Display The Overall Extracted Useful Information**:
   - Launch a JavaFX application to visualize the extracted data using combined charts.

6. **Display The Extra Statistical Data**:
   - Perform a detailed analysis of the log file to provide additional statistical insights.

7. **Exit**:
   - Exit the application and display team details.

### Additional Functionalities
- **Job Analysis**:
  - Analyzes job execution within specified time ranges using the `JobAnalyzer` class.
  
- **Partition Analysis**:
  - Counts jobs in various partitions using classes like `GPUCounter`, `OpteronCounter`, `EPYCCounter`, `gpuv100sCounter`, `gpuk40cCounter`, `gpuk10Counter`, and `gputitanCounter`.

- **Error Analysis**:
  - Processes log files to identify errors and responsible users using the `ErrorAnalyzer` class.

- **Time Analysis**:
  - Computes average job execution time using the `TimeAnalyzer` class.

- **Log Analysis**:
  - Conducts comprehensive log analysis to extract and display useful statistical data using the `LogAnalyzer` class.

### Resources
- **Description File**:
  - Provides an overview of the UMHPC Navigator application.

- **Team Details File**:
  - Lists the team members involved in developing this application.

### Visualization
- The application integrates JavaFX for graphical representation of data, offering interactive charts and visual aids for better understanding and interpretation.

## How to Run
1. Ensure you have Java and JavaFX installed on your system.
2. Clone the repository:
   ```
   git clone https://github.com/yourusername/umhpc-navigator.git
   ```
3. Navigate to the project directory and compile the code:
   ```
   javac -d bin -sourcepath src src/umhpcnavigator/UMHPCNavigator.java
   ```
4. Run the application:
   ```
   java -cp bin umhpcnavigator.UMHPCNavigator
   ```

## Contributing
We welcome contributions from the community. If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request. Make sure to follow our contributing guidelines.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments
We extend our gratitude to the University of Malaya and DICC for providing the resources and support needed to develop this application. Special thanks to our team for their dedication and hard work.

---

This repository serves as a comprehensive tool for HPC job analysis, helping users to efficiently monitor and manage their HPC resources. We hope you find UMHPC Navigator useful and look forward to your feedback!
