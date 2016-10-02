package m5;

import java.util.*;

/**
 * @author Jenna Kwon
 * www.bit.ly/gttip_fall2016_m5
 *
 * Merge sorted sequences
 * Given a set of sorted sequences, compute the union of these sequences as a sorted sequence.
 * Ex. 	Input: <3, 5, 7>, <0, 6>, <0, 6, 28> <0, 0, 3, 5, 5, 6, 7, 28>, <5, 4, 6, 8, 29>,<1, 2>
 *      OutpuT: <0, 0, 0, 1, 2, 3, 3, 4, 5, 5, 5, 5, 6, 6, 6, 6,7, 7, 8, 28, 28, 29?
 *
 */
public class Q3 {

    public static class ArrayElement implements Comparable<ArrayElement> {
        public Integer value;
        public Integer arrayId; // id of the array the element belongs to.

        public ArrayElement(Integer value, Integer arrayId) {
            this.value = value;
            this.arrayId = arrayId;
        }

        public int compareTo(ArrayElement o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static ArrayList<Integer> mergeSortedSeq(List<ArrayList<Integer>> sequences) {

        PriorityQueue<ArrayElement> q = new PriorityQueue<>();

        List<Iterator<Integer>> iters = new ArrayList<>(sequences.size());

        for (List<Integer> array : sequences) {
            iters.add(array.iterator());
        }


        for (int i = 0; i < iters.size(); ++i) {
            if (iters.get(i).hasNext()) {
                q.add(new ArrayElement(iters.get(i).next(), i));
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (!q.isEmpty()) {
            ArrayElement headEntry = q.poll();
            result.add(headEntry.value);
            if (iters.get(headEntry.arrayId).hasNext()) {
                q.add(new ArrayElement(iters.get(headEntry.arrayId).next(),
                        headEntry.arrayId));
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> f1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> f2 = new ArrayList<>(Arrays.asList(0, 6));
        ArrayList<Integer> f3 = new ArrayList<>(Arrays.asList(0, 6, 28));
        ArrayList<Integer> f4 = new ArrayList<>(Arrays.asList(0, 0, 3, 5, 5, 6, 7, 28));
        ArrayList<Integer> f5 = new ArrayList<>(Arrays.asList(5, 4, 6, 8, 29));
        ArrayList<Integer> f6 = new ArrayList<>(Arrays.asList(1, 2));

        LinkedList<ArrayList<Integer>> sequences = new LinkedList<>();
        sequences.add(f1);
        sequences.add(f2);
        sequences.add(f3);
        sequences.add(f4);
        sequences.add(f5);
        sequences.add(f6);
        
        ArrayList<Integer> merged = mergeSortedSeq(sequences);

        System.out.println(merged.toString());
    }
}
