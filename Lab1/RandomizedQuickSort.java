import java.util.Random;

public class RandomizedQuickSort {
    public static int count = 0;
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition(int[] arr, int p, int r) {
        int x = arr[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            count++;
            if (arr[j] <= x) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r);
        return i + 1;
    }

    public static int randomizedPartition(int[] arr, int p, int r) {
        int i = p + new Random().nextInt(r - p + 1);
        swap(arr, i, r);
        return partition(arr, p, r);
    }

    public static void randomizedQuickSort(int[] arr, int p, int r) {
        if (p < r) {
            int q = randomizedPartition(arr, p, r);
            randomizedQuickSort(arr, p, q - 1);
            randomizedQuickSort(arr, q + 1, r);
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i;
        }
        int p = 0, r = arr.length - 1;
        randomizedQuickSort(arr, p, r);
        System.out.println("Number of comparisons is: " + count);

    }
}