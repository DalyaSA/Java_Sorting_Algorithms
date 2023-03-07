package quicksort;

import com.sun.management.OperatingSystemMXBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.Random;


public class QuickSortAlgorithm {
    private static int array[];
    private  Random random;
    OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static void sort(int[] arr) {

        if (arr == null || arr.length == 0) {
            return;
        }
        array = arr;
        quickSort(0, array.length-1);
    }

    public static void quickSort(int left, int right) {

        int i = left;
        int j = right;
        // pivot is the mid is this case
        int pivot = array[left+(right-left)/2];

        while (i <= j) {
            /*
             * In each iteration, we will increment left until we find element greater than pivot
             * We will decrement right until we find element less than pivot
             */
            while (array[i] < pivot) { i++; } while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (left < j)
            quickSort(left, j);
        if (i < right)
            quickSort(i, right);
    }

    public static void exchange(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
