package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yellowstar on 10/23/15.
 */
public class MeetingRoom {
    public static void main(String[] args) {
        int[][] times = {{1, 2},{1, 3}, {2, 6}, {3, 7}, {4, 8}};
        System.out.println(minRoomNumber(times));
    }
    public static int minRoomNumber(int[][] times) {
        List<Pair> pairList = new ArrayList<>();
        for (int[] time : times) {
            Pair start = new Pair(time[0], true);
            pairList.add(start);
            Pair end = new Pair(time[1], false);
            pairList.add(end);
        }
        Collections.sort(pairList, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.time == o2.time) {
                    return o1.isStart ? 1 : -1;
                }
                return o1.time - o2.time;
            }
        });
        int max = 0;
        int count = 0;
        for (Pair pair : pairList) {
            if (pair.isStart) {
                count++;
                max = Math.max(max, count);
            } else {
                count--;
            }
        }
        return max;
    }

    static class Pair {
        int time;
        boolean isStart;
        Pair (int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }
    }
}
