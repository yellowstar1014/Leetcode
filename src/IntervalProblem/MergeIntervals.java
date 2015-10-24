package src.IntervalProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yellowstar on 7/22/15.
 */
public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ret = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return ret;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return Integer.compare(i1.start, i2.start);
            }
        });
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for (Interval interval : intervals) {
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            }
            else {
                ret.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        ret.add(new Interval(start, end));
        return ret;
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        List<Interval> intervals = new ArrayList<>();
        Interval i1 = new Interval(1,4);
        Interval i2 = new Interval(1,4);
        intervals.add(i1);
        intervals.add(i2);
        List<Interval> ret = mergeIntervals.merge(intervals);
    }
}
