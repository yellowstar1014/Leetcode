package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yellowstar on 10/12/15.
 */
class MaxInterval {
    private List<Interval> intervals = new ArrayList<>();
    private int max = 0;
    class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main (String[] args) {
        MaxInterval maxInterval = new MaxInterval();
        int[] array = {1, 99, 2, 97, 98, 50, 5, 100, 3, 4};
        for (int i = 0; i < array.length; i++) {
            int ret = maxInterval.getLongestInterval(array[i]);
        }
    }

    public Integer getLongestInterval(Integer next) {
        Interval newInterval = null;
        if (intervals.size() == 0) {
            newInterval = new Interval(next, next);
            intervals.add(newInterval);
            max = 1;
            return max;
        }
        int index = binarySearch(next);
        Interval pre;
        Interval suc;
        if (index == -1) {
            pre = intervals.get(0);
            if (next + 1 < pre.start) {
                newInterval = new Interval(next, next);
                intervals.add(0, newInterval);
            } else if (next + 1 == pre.start) {
                pre.start = next;
                newInterval = pre;
            }
        } else if (index == intervals.size() - 1) {
            pre = intervals.get(index);
            if (next - 1 > pre.end) {
                newInterval = new Interval(next, next);
                intervals.add(newInterval);
            } else if (next - 1 == pre.end) {
                pre.end = next;
                newInterval = pre;
            }
        } else {
            pre = intervals.get(index);
            suc = intervals.get(index + 1);
            if (next - 1 == pre.end && next + 1 == suc.start ) {
                intervals.remove(suc);
                pre.end = suc.end;
                newInterval = pre;
            } else if (next - 1 == pre.end) {
                pre.end = next;
                newInterval = pre;
            } else if (next + 1 == suc.start) {
                suc.start = next;
                newInterval = suc;
            } else if (next - 1 > pre.end && next + 1 < suc.start) {
                newInterval = new Interval(next, next);
                intervals.add(index + 1, newInterval);
            }
        }
        max = Math.max(max, newInterval.end - newInterval.start + 1);
        return max;
    }

    private int binarySearch(int target) {
        int l = 0;
        int r = intervals.size() - 1;
        while ( l < r) {
            int mid = (l + r + 1) >> 1;
            Interval mInterval = intervals.get(mid);
            if (target < mInterval.end) {
                r = mid - 1;
            } else if (target > mInterval.end) {
                l = mid;
            } else {
                return mid;
            }
        }
        return target < intervals.get(l).end ? -1 : l;
    }
}
