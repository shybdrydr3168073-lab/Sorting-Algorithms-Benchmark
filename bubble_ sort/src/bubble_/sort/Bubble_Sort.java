/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubble_.sort;

import java.util.Arrays;
import java.util.Random;


public class Bubble_Sort {

    
 
static int N = 100000;

public static void bubbleSort(int[] a) {
    for (int i = 0; i < a.length - 1; i++) {
        for (int j = 0; j < a.length - 1 - i; j++) {
            if (a[j] > a[j + 1]) {
                int temp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = temp;
            }
        }
    }
}

public static long measure(int[] original, String caseName) {
int[] testArray = Arrays.copyOf(original, original.length);

long startTime = System.nanoTime();

bubbleSort(testArray);

long endTime = System.nanoTime();

long durationInMicroseconds = (endTime - startTime) / 1000;

System.out.printf("Bubble Sort - %s: %,d µs%n",
        caseName, durationInMicroseconds);

return durationInMicroseconds;
}

public static void main(String[] args) {

    int[] random = new int[N];
    Random r = new Random();

    for (int i = 0; i < N; i++) {
        random[i] = r.nextInt(N);
    }

    int[] sorted = random.clone();
    Arrays.sort(sorted);

    int[] reverse = sorted.clone();

    for (int i = 0; i < N / 2; i++) {
        int temp = reverse[i];
        reverse[i] = reverse[N - 1 - i];
        reverse[N - 1 - i] = temp;
    }

    int[] partial = sorted.clone();

    for (int i = 0; i < N / 10; i++) {
        int x = r.nextInt(N);
        int y = r.nextInt(N);

        int temp = partial[x];
        partial[x] = partial[y];
        partial[y] = temp;
    }

    System.out.println("Bubble Sort Results");


 measure(random, "Random");
measure(reverse, "Reverse");
measure(partial, "Partial");
measure(sorted, "Sorted");
}
}
    

    

