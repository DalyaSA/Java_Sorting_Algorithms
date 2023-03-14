package shellsort;

import com.sun.management.OperatingSystemMXBean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.Random;

//https://www.javatpoint.com/shell-sort
public class ShellSortAlgorithm {
    private  Random random;

    /*Function to sort array using insertion sort*/
    public void sort(int arr[]) {

        for (int interval = arr.length/2; interval > 0; interval /= 2) {

            for (int i = interval; i < arr.length; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= interval && arr[j - interval] > temp; j -= interval) {
                    arr[j] = arr[j - interval];
                }
                arr[j] = temp;
            }
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
    public int calcCPU(long cpuStartTime, long elapsedStartTime, int cpuCount){
        long end = System.nanoTime();
        long totalAvailCPUTime = cpuCount * (end-elapsedStartTime);
        long totalUsedCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime()-cpuStartTime;
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
