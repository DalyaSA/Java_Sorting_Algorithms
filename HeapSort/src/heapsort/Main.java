package heapsort;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
public class Main {
    public static void main(String args[]) throws InterruptedException {
        int[] arr = new int[400000]; //sorting 400000 random integers
        // int [] arr = new int[Integer.parseInt(args[0])];
        double startMS;
        long startNS;
        double end;
        long mem0;
        long mem1;
        float cpuPercent;
        int cpuCount;


        Runtime runtime = Runtime.getRuntime();
        HeapSortAlgorithm obj = new HeapSortAlgorithm();
        //number of available processors;
        cpuCount = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
        obj.CreateRandoms(arr);

        Thread.sleep(3000);



        //CPU start
        long startCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
        startNS = System.nanoTime();
        //memory start
        mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //Time start
        startMS = System.currentTimeMillis();

        //Sort Start
        obj.sort(arr);

        //CPU end
        cpuPercent = obj.getusedCPU(startCPUTime, startNS, cpuCount);
        //Memory end
        mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //Time end
        end = System.currentTimeMillis();



        System.out.println("The estimated Time: " + obj.estimatedTotalTime(startMS, end));
        System.out.println("memoryConsumption: " + obj.getUsedMemory(mem0, mem1));
        System.out.println("CPUConsumption: " +  cpuPercent);

        System.gc();

        System.exit(0);
/*
        obj.writeToFile("heapSortTime.csv",obj.estimatedTotalTime(start, end));
        obj.writeToFile("heapSortCPU.csv", usedCPU);
        obj.writeToFile("heapSortMemory.csv",obj.getUsedMemory(mem0, mem1));
        Thread.sleep(3000);
        runtime.gc();

        /*
         //print sorted array
         System.out.println("Sorted array: " + Arrays.toString(arr));
          */

        System.exit(0);

    }


}
