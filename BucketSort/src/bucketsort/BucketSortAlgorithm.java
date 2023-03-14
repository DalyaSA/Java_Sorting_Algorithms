package bucketsort;
import com.sun.management.OperatingSystemMXBean;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/* https://www.javatpoint.com/bucket-sort-in-java
   https://www.w3resource.com/java-exercises/sorting/java-sorting-algorithm-exercise-19.php
 */

public class BucketSortAlgorithm {
    private  Random random;
    public static void sort(int[] arr) {

        int min = Arrays.stream(arr).min().orElse(0);
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);

        /* number of buckets */
        int numberOfBuckets = max - min + 1;

        List<Integer>[] buckets = new List[numberOfBuckets];
        /* init buckets */
        for (int i = 0; i < numberOfBuckets; ++i) {
            buckets[i] = new ArrayList<>();
        }
        /* store elements to buckets */
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            int hash = hash(value, min, numberOfBuckets);
            buckets[hash].add(value);
        }
        /* sort individual bucket */
        for (int i = 0; i < buckets.length; i++) {
            List<Integer> bucket = buckets[i];
            insertionSort(bucket);
        }
        /* concatenate buckets to origin array */
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            List<Integer> bucket = buckets[i];
            for (int j = 0; j < bucket.size(); j++) {
                int value = bucket.get(j);
                arr[index++] = value;
            }
        }
    }

    /*
     * Get index of bucket which of our elements gets placed into it.
     *  elem the element of array to be sorted
     *  min value of array
     *  numberOfBucket the number of bucket
     *  index of bucket
     */
    private static int hash(int elem, int min, int numberOfBucket) {
        return (elem - min) / numberOfBucket;
    }

    private static void insertionSort(List<Integer> arr) {

        for (int i = 1; i < arr.size(); i++) {
            int key = arr.get(i);
            int j = i - 1;

            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);
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
