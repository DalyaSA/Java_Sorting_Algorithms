package countingsort;

import com.sun.management.OperatingSystemMXBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Random;

public class CountingSortAlgorithm {
    private  Random random;
    OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public void sort(int arr[])
    {
        // Find the maximum and minimum element from the array
        int min = Arrays.stream(arr).min().orElse(0);
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);


        int count[] = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int output[] = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        //Array copy
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
    public void CreateRandoms(int arr[]){
        random = new Random(200);
        for (int i = 0; i < arr.length; i++) {
            // Add 400000 random integers to an array
            arr[i] = random.nextInt(2000001) - 1000000;
        }
    }
    public double estimatedTotalTime(double start, double end) {

        return end - start;
    }
    public int getusedCPU(long cpuStartTime, long elapsedStartTime, int cpuCount){
        long end = System.nanoTime();
        long totalAvailCPUTime = cpuCount * (end-elapsedStartTime);
        long totalUsedCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime()-cpuStartTime;
        //log("Total CPU Time:" + totalUsedCPUTime + " ns.");
        //log("Total Avail CPU Time:" + totalAvailCPUTime + " ns.");
        float per = ((float)totalUsedCPUTime*100)/(float)totalAvailCPUTime;
        return (int)per;

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
