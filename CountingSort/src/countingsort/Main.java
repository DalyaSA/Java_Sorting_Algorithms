package countingsort;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
public class Main {

    // Driver method to test above
    public static void main(String args[]) throws InterruptedException {
        //int[] arr = new int[400000]; //sorting 400000 random integers
        int [] arr = new int[Integer.parseInt(args[0])];
        double start;
        double end;
        long mem0;
        long mem1;
        int cpuCount;
        long startCPUTime;
        long startNS;
        double cpuPercent;

        Runtime runtime = Runtime.getRuntime();
        CountingSortAlgorithm obj = new CountingSortAlgorithm();
        obj.CreateRandoms(arr);
        Thread.sleep(3000);



        // number of available processors
        cpuCount = ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
        //CPU start
        startCPUTime = ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
        startNS = System.nanoTime();
        //memory start
        mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //Time start
        start = System.currentTimeMillis();

        //Sort Start
        obj.sort(arr);

        //Time end
        end = System.currentTimeMillis();
        //Memory end
        mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //CPU end
        cpuPercent = obj.calcCPU(startCPUTime, startNS, cpuCount)/100.0;



        System.out.println("The estimated Time: " + obj.estimatedTotalTime(start, end));
        System.out.println("memoryConsumption: " + obj.getUsedMemory(mem0, mem1));
        System.out.println("CPUConsumption: " +  cpuPercent);


        obj.writeToFile("countingSortTime.csv",obj.estimatedTotalTime(start, end));
        obj.writeToFile("countingSortCPU.csv", cpuPercent);
        obj.writeToFile("countingSortMemory.csv",obj.getUsedMemory(mem0, mem1));
        Thread.sleep(3000);
        runtime.gc();


          /*
         //print sorted array
         System.out.println("Sorted array: " + Arrays.toString(arr));
          */

        System.exit(0);


    }
}