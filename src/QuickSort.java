
// Java program for implementation of QuickSort 
public class QuickSort { 
    public static int partition(int arr[], int low, int high) { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
  
    public static void sort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high); 

            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
    public static void startSort(int arr[], int low, int high){
        Instrumentation ins = Instrumentation.getInstance();
        ins.startTiming("QuickSort");
        sort(arr, low, high);
        ins.stopTiming("QuickSort");
    }
}