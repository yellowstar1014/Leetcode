package src.IntervalProblem;

import java.util.*;

/**
 * Created by yellowstar on 10/19/15.
 */
public class MaxOverlappedInterval {

    class Point {
        int x;
        boolean isStart;
        int id;
        Point(int id, int x, boolean isStart) {
            this.x = x;
            this.isStart = isStart;
            this.id = id;
        }
    }

    public static void main(String[] args) {
        MaxOverlappedInterval maxOverlappedInterval = new MaxOverlappedInterval();
        List<int[]> list = new ArrayList<>();
        int[] s1 = {1, 4};
        int[] s2 = {2, 4};
        int[] s3 = {3, 5};
        int[] s4 = {4, 5};
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        int[] ret =  maxOverlappedInterval.maxOverlappedInterval(list);
    }

    public int[] maxOverlappedInterval(List<int[]> segments) {
        int len = segments.size();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int[] seg = segments.get(i);
            Point start = new Point(i, seg[0], true);
            Point end = new Point(i, seg[1], false);
            points.add(start);
            points.add(end);
        }
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.x == o2.x) {
                    if (o1.isStart) return -1;
                    else return 1;
                }
                return o1.x - o2.x;
            }
        });
        int[] overlap = new int[len];
        Map<Integer, Integer> map = new HashMap<>();
        for (Point point : points) {
            if (point.isStart) {
                update(map);
                map.put(point.id, 0);
            } else {
                overlap[point.id] = map.remove(point.id);
            }
        }
        int max = 0;
        int id = -1;
        for (int i = 0; i < len; i++) {
            if (overlap[i] > max) {
                max = overlap[i];
                id = i;
            }
        }
        return segments.get(id);
    }

    private void update(Map<Integer, Integer>map) {
        for (Integer key : map.keySet()) {
            map.put(key, map.get(key) + 1);
        }
    }
}
