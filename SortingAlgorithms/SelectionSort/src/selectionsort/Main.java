package selectionsort;

public class Main {

    // Driver method to test above
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
        SelectionSortAlgorithm obj = new SelectionSortAlgorithm();
        obj.CreateRandoms(arr);
        Thread.sleep(3000);

        cpu0 = obj.getUsedCPU();
        mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        start = System.currentTimeMillis();
        obj.sort(arr);
        end = System.currentTimeMillis();
        mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        cpu1 =  obj.getUsedCPU();
        usedCPU = cpu1 - cpu0;

        System.out.println("The estimated Time: " + obj.estimatedTotalTime(start, end));
        System.out.println("memoryConsumption: " + obj.getUsedMemory(mem0, mem1));
        System.out.println("CPUConsumption: " + usedCPU);

        obj.writeToFile("selectionSortTime.csv",obj.estimatedTotalTime(start, end));
        obj.writeToFile("selectionSortCPU.csv", usedCPU);
        obj.writeToFile("selectionSortMemory.csv",obj.getUsedMemory(mem0, mem1));
        Thread.sleep(3000);
        runtime.gc();

        System.exit(0);

        /*
        System.out.println("Sorted array is");
        obj.printArray(arr);
        */

    }
}