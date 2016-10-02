package m5;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Jenna Kwon
 * www.bit.ly/gttip_fall2016_m5
 *
 * Sort an almost sorted (k-sorted) array
 * Given an int[] where each integer is at most k away from its correctly sorted position,
 * how would you efficiently sort the array?
 * Ex. 	Input: [3, -1, 2, 6, 4, 5, 8], k = 2
 * (Every element is at most 2 positions away from its final sorted position)
 *
 */
public class Q2 {

    public static void kSort(int[] arr, int k) {

        // Allocate a PQ with k + 1 elements and a min comparator
        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1, (Integer s1, Integer s2) -> s1 - s2);

        for (int i = 0; i <= k; i++) {
            heap.add(arr[i]);
        }

        int counter = 0;
        for (int i = k + 1; i < arr.length + k; i++) {
            arr[counter++] = heap.poll();
            if (i < arr.length) { // last k elements are in the heap
                heap.add(arr[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3 ,-1, -4, 2 ,6 ,4 ,5 ,8, 7, 9, 13, 10, 12, 16};

        kSort(arr, 3);
        System.out.println(Arrays.toString(arr));

    }
}
