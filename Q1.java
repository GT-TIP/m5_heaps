package m5;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Jenna Kwon
 * www.bit.ly/gttip_fall2016_m5
 *
 * Compute median of streaming data
 * Given a stream of numbers, how would you keep 	a running median?
 * Ex. 	Input: 	    1, 	0, 	3, 	5, 	2,	0, 	1…..
 *      Output: 	1, 	0.5, 	1, 	2, 	2, 	1.5, 	1
 *
 */
public class Q1 {
    // min heap

    public static void onlineMedian(InputStream sequence) {
        // larger half
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // smaller half
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(16, Collections.reverseOrder());

        Scanner s = new Scanner(sequence);

        while (s.hasNextInt()) {
            int x = s.nextInt();
            System.out.println("Current Element = " + x);
            if (minHeap.isEmpty()) {
                minHeap.add(x);
            } else {
                if (minHeap.peek() <= x) {
                    minHeap.add(x);
                } else {
                    maxHeap.add(x);
                }
            }

            // always ensure that minHeap has one more element than maxHeap
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.add(minHeap.remove());
            } else if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.remove());
            }

            System.out.println("Current Median = " + (minHeap.size() == maxHeap.size() ?
                            0.5 * (minHeap.peek() + maxHeap.peek()) : (double) minHeap.peek()));
        }
    }

    public static void main(String[] args) {
        File file = new File("fileInputStream");
        System.out.println(file.exists());

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        onlineMedian(fis);
    }

}
