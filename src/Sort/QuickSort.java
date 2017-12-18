package Sort;


public class QuickSort {

    public void qSort(int a[], int low, int high) {
        if (low < high) {
            int pivot = partition(a, low, high);
            qSort(a, low, pivot - 1);
            qSort(a, pivot + 1, high);
        }
    }

    int partition(int a[], int low, int high) {
        int i = low - 1;
        int pivot = a[high];
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                swap(a, j, i);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    public void swap(int a[], int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String args[]) {
     int a[]= {10,80,40,30,90,70};
     QuickSort quickSort=new QuickSort();
     quickSort.qSort(a,0,a.length-1);
     for(int i=0;i<a.length;i++){
         System.out.print(a[i]+" ");
     }
    }
}
