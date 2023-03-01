package selectionsort;

import com.sun.management.OperatingSystemMXBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.concurrent.ThreadLocalRandom;

public class SelectionSortAlgorithm {
    OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public void sort(int arr[])
    {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    public void CreateRandoms(int arr[]){
        for (int i = 0; i < arr.length; i++) {
            // Add random integers in an array
            arr[i] = ThreadLocalRandom.current().nextInt(-1000000,100000);
        }
    }

    public double estimatedTotalTime(double start, double end) {

        return end - start;
    }
    public double getUsedCPU() {
        return operatingSystemMXBean.getProcessCpuLoad();
    }
    public double getUsedMemory(long mem0, long mem1) {
        return (mem1 - mem0)/(1024.0*1024.0);
    }

    public void writeToFile(String fileName, double value) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true))) {
            pw.println(value);
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
