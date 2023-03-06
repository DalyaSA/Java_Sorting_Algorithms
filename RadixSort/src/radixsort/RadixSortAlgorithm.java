package radixsort;
/* Reference
https://www.youtube.com/watch?v=4ungd6NXFYI
https://github.com/geekific-official/geekific-youtube/
*/
import com.sun.management.OperatingSystemMXBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class RadixSortAlgorithm {
    private  Random random;
    OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public void sort(int arr[]) {

        Map<Boolean, List<Integer>> splitArray = Arrays.stream(arr).boxed().collect(Collectors.partitioningBy(i -> i < 0));
        int[] negativeInts = radixsort(splitArray.get(true).stream().mapToInt(Integer::intValue).map(Math::abs).toArray());
        int[] positiveInts = radixsort(splitArray.get(false).stream().mapToInt(Integer::intValue).toArray());
        for (int i = negativeInts.length - 1, j = 0; i >= 0; i--, j++) arr[j] = -negativeInts[i];
        System.arraycopy(positiveInts, 0, arr, negativeInts.length, positiveInts.length);
    }

    // The main function to that sorts arr[] using Radix Sort
    public  int[] radixsort(int arr[])
    {
        // Find the maximum number to know number of digits
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);

        // Do counting sort for every digit.
        for (int exp = 1; max / exp > 0; exp *= 10){
            countSort(arr, exp);
        }
        return arr;
    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    private void countSort(int arr[], int exp) {

        int n = arr.length;

        int output[] = new int[n];
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        //Array copy
        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }



    public void CreateRandoms(int arr[]){
        random = new Random(200);
        for (int i = 0; i < arr.length; i++) {
            // Add 400000 random integers to an array
            arr[i] = random.nextInt(-1000000,1000000);
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


