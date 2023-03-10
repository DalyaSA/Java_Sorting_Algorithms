package mergesort;

import java.util.Arrays;
public class Main {
    public static void main(String args[]) throws InterruptedException {
        //int[] arr = new int[400000]; //sorting 400000 random integers
        int [] arr = new int[Integer.parseInt(args[0])];
        double start;
        double end;
        long mem0;
        long mem1;
        double cpu0;
        double cpu1;
        double usedCPU;

        Runtime runtime = Runtime.getRuntime();
        MergeSortAlgorithm obj = new MergeSortAlgorithm();
        obj.CreateRandoms(arr);
        Thread.sleep(3000);

        cpu0 = obj.getUsedCPU();
        mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        start = System.currentTimeMillis();
        obj.sort(arr, 0, arr.length-1);
        end = System.currentTimeMillis();
        mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        cpu1 =  obj.getUsedCPU();
        usedCPU = cpu1 - cpu0;

        System.out.println("The estimated Time: " + obj.estimatedTotalTime(start, end));
        System.out.println("memoryConsumption: " + obj.getUsedMemory(mem0, mem1));
        System.out.println("CPUConsumption: " + usedCPU);

        obj.writeToFile("mergeSortTime.csv", obj.estimatedTotalTime(start, end));
        obj.writeToFile("mergeSortCPU.csv", usedCPU);
        obj.writeToFile("mergeSortMemory.csv", obj.getUsedMemory(mem0, mem1));
        Thread.sleep(3000);
        runtime.gc();

          /*
         //print sorted array
         System.out.println("Sorted array: " + Arrays.toString(arr));
          */
        System.exit(0);

    }
}