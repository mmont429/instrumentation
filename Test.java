
public class Test {
    public static void main(String[] args){
        Instrumentation ins = Instrumentation.getInstance();
        ins.activate(true);
        ins.startTiming("main");
        ins.comment("Start the program");
        int[] arr = populateArray(10000);
        BubbleSort.bubbleSort(arr);
        ins.startTiming("QuickSort");
        QuickSort.sort(arr, 0, arr.length-1);
        ins.stopTiming("QuickSort");
        ins.stopTiming("main");
        ins.dump("C:\\Users\\Michael\\Documents\\SOFT 437\\Assignment 3\\test.log");
    }

    public static int[] populateArray(int size){
        Instrumentation ins = Instrumentation.getInstance();
        ins.startTiming("PopulatingArray");
        int[] array = new int[size];
        
        for (int i=1;i<array.length;i++){
            array[i]=(int)(Math.random()*(100000)+1);
        }
        ins.stopTiming("PopulatingArray");
        return array;
    }
}
