package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by yellowstar on 9/21/15.
 */
public class SlidingWindowMedian {
    public ArrayList<Integer> medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        ArrayList<Integer> ret = new ArrayList<>();
        if (len == 0) return ret;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(100, Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        if (k > len) k = len;
        maxHeap.offer(nums[0]);
        int i = 1;
        for (; i < k; i++) {
            int median = maxHeap.peek();
            if (nums[i] < median) {
                maxHeap.offer(nums[i]);
            }
            else {
                minHeap.offer(nums[i]);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
            else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        ret.add(maxHeap.peek());
        for (; i < len; i++) {
            int median = maxHeap.peek();
            if (nums[i - k] <= median) {
                maxHeap.remove(nums[i - k]);
            }
            else {
                minHeap.remove(nums[i - k]);
            }
            if (nums[i] < median) {
                maxHeap.offer(nums[i]);
            }
            else {
                minHeap.offer(nums[i]);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
            else if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
            ret.add(maxHeap.peek());
        }
        return ret;
    }
}
