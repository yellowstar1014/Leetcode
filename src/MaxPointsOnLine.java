package src;

import java.util.HashMap;

/**
 * Created by yellowstar on 8/11/15.
 */
public class MaxPointsOnLine {
    public static void  main(String[] args){
        Point[] points = {new Point(1,1), new Point(1,1), new Point(1, 1)};
        MaxPointsOnLine maxPointsOnLine = new MaxPointsOnLine();
        int ret = maxPointsOnLine.maxPoints(points);
        System.out.println(ret);
    }
    public int maxPoints(Point[] points) {
        // y = ax + b
        // if we have (x1, y1) (x2, y2) on one line
        // y1 = ax1 + b  y2 = ax2 + b
        // (y2 - y1) = a (x2 - x1)  -> a = (y2 - y1) / (x2 - x1)
        // if (x3, y3) on the same line , y3 = ax3 + b
        // (y3 - y1) / (x3 - x1) = (y2 - y1) / (x2 - x1) = a
        // let a = y0 / x0; y0,x0 are integer, we can use x0, y0 as key of each line
        if (points == null || points.length == 0) return 0;
        HashMap<Long, Integer> map = new HashMap<>();
        int overlap = 0;
        int max = 1;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                int a = getGCD(x, y);
                if (a != 0) {
                    x = x / a;
                    y = y / a;
                }
                long key = (x << 32) | y;
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                }
                else map.put(key, 2);
            }
            for (int v : map.values()) {
                max = Math.max(max, v);
            }
            max += overlap;
            map.clear();
            overlap = 0;
        }
        return max;
    }

    private int getGCD(int x, int y) {
        if (y == 0) return x;
        return getGCD(y, x % y);
    }
}
