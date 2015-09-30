package src;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by yellowstar on 9/28/15.
 */
public class MinDiffSubArray {
    // deque
    public static void main(String[] args) {
        MinDiffSubArray minDiffSubArray = new MinDiffSubArray();
        int[] A = new int[] {4, 1, 8, 9, 13, 10, 12, 12, 12};
        int ret = minDiffSubArray.subarray(A, 3);
        System.out.println(ret);
    }

    public int subarray(int[] A, int  k) {
        Deque<Integer> maxQueue = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();
        int i = 0;
        for (; i < k; i++) {
            while (!maxQueue.isEmpty() && A[maxQueue.peekLast()] <= A[i]) {
                maxQueue.pollLast();
            }
            maxQueue.offerLast(i);
            while (!minQueue.isEmpty() && A[minQueue.peekLast()] >= A[i]) {
                minQueue.pollLast();
            }
            minQueue.offerLast(i);
        }
        long minDiff = Long.MAX_VALUE;
        long diff = A[maxQueue.peekFirst()] - A[minQueue.peekFirst()];
        if (diff < minDiff) {
            minDiff = diff;
        }
        for (; i < A.length; i++) {
            while (!maxQueue.isEmpty() && A[maxQueue.peekLast()] <= A[i]) {
                maxQueue.pollLast();
            }
            maxQueue.offerLast(i);
            while (maxQueue.peekFirst() < i - k + 1) {
                maxQueue.pollFirst();
            }
            while (!minQueue.isEmpty() &&A[minQueue.peekLast()] >= A[i]) {
                minQueue.pollLast();
            }
            minQueue.offerLast(i);
            while (minQueue.peekFirst() < i - k + 1) {
                minQueue.pollFirst();
            }
            diff = A[maxQueue.peekFirst()] - A[minQueue.peekFirst()];
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return (int)minDiff;
    }
}
