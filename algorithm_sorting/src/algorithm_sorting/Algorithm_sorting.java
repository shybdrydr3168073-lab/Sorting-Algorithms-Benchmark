/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm_sorting;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author lenovo
 */
public class Algorithm_sorting {


    // Find the smallest number and put it in its place
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    // Insert each number in the correct position
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Compare neighboring numbers
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Divide the array into two parts
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] a = Arrays.copyOfRange(arr, left, mid + 1);
        int[] b = Arrays.copyOfRange(arr, mid + 1, right + 1);
        int i = 0, j = 0, k = left;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) arr[k++] = a[i++];
            else arr[k++] = b[j++];
        }
        while (i < a.length) arr[k++] = a[i++];
        while (j < b.length) arr[k++] = b[j++];
    }

    // Use the last number as pivot
    public static void quickSort(int[] arr, int low, int high) {
        // Sort the smaller side first to avoid deep recursion
        while (low < high) {
            int p = partition(arr, low, high);
            if (p - low < high - p) {
                quickSort(arr, low, p - 1);
                low = p + 1;
            } else {
                quickSort(arr, p + 1, high);
                high = p - 1;
            }
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Copy the original data, then measure sorting only
    public static long measureTime(int[] original, int algorithm) {
        int[] arr = Arrays.copyOf(original, original.length);
        long start = System.nanoTime();
        if (algorithm == 1) selectionSort(arr);
        else if (algorithm == 2) insertionSort(arr);
        else if (algorithm == 3) bubbleSort(arr);
        else if (algorithm == 4) mergeSort(arr, 0, arr.length - 1);
        else if (algorithm == 5) quickSort(arr, 0, arr.length - 1);
        long end = System.nanoTime();
        return (end - start) / 1000;
    }

    public static void main(String[] args) {
        int n = 100000;
        Random rand = new Random(10);
        int[] random = new int[n];
        int[] reverse = new int[n];
        int[] partial = new int[n];
        int[] sorted = new int[n];

        for (int i = 0; i < n; i++) {
            random[i] = rand.nextInt(n);
            reverse[i] = n - i;
            partial[i] = i + 1;
            sorted[i] = i + 1;
        }

        // Make the sorted array partially unsorted
        for (int i = 0; i < n / 10; i++) {
            int a = rand.nextInt(n);
            int b = rand.nextInt(n);
            int temp = partial[a];
            partial[a] = partial[b];
            partial[b] = temp;
        }

        String[] names = {"Selection Sort", "Insertion Sort", "Bubble Sort", "Merge Sort", "Quick Sort"};
        int[][] cases = {random, reverse, partial, sorted};
        long[][] results = new long[5][4];

        System.out.println("Sorting Algorithms Benchmark");
        System.out.println("Array Size: " + n);
        System.out.println("Running tests, please wait...");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                results[i][j] = measureTime(cases[j], i + 1);
            }
        }

        System.out.printf("%-18s %15s %15s %15s %15s%n", "Algorithm", "Random", "Reverse", "Partial", "Sorted");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%-18s", names[i]);
            for (int j = 0; j < 4; j++) System.out.printf(" %,14d", results[i][j]);
            System.out.println();
        }
        System.out.println("Time unit: Microseconds (us)");
    }
}

    /**
     * @param args the command line arguments
     */
    
    

