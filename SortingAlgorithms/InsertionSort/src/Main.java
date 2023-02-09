public class Main {

    public static void main(String args[])
    {
        int[] arr = new int[400000]; //sorting 400000 random integers
        double start;
        double end;
        long mem0;
        long mem1;
        double cpu0;
        double cpu1;
        double usedCPU;

        InsertionSortAlgorithm obj = new InsertionSortAlgorithm();
        obj.CreateRandoms(arr);


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

        //If you want to print out the sorted numbers uncomment this
        /*System.out.println("Sorted array is");
        obj.printArray(arr);*/
    }
}