import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.Random;

public class InsertionSortAlgorithm {
    private Random random;
    OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    /*Function to sort array using insertion sort*/
    public void sort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public void CreateRandoms(int arr[]){
        random = new Random(200);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(); // Add random integers in an array
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


    //If you want to print out the sorted numbers, uncomment this method
   /* static void printArray(int arr[])
    {
        int N = arr.length;

        for (int i = 0; i < N; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }*/

}
