package src;

import java.util.Stack;

/**
 * Created by yellowstar on 10/28/15.
 */
public class MaxRectangle {
    public static void main(String[] args) {
        area(4, 4, "0,0|0,1|0,2|0,3|2,0|2,1|2,2|2,3");
    }
    static int area(int width, int height, String water) {
        if (water.isEmpty()) return height * width;
        String[] coordinates = water.split("\\|");
        char[][] matrix = new char[height][width];
        for (String coordinate : coordinates) {
            String[] pair = coordinate.split(",");
            int i = Integer.valueOf(pair[1]);
            int j = Integer.valueOf(pair[0]);
            matrix[i][j] = '0';
        }
        int[] h = new int[width];
        int ret = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] != '0') {
                    h[j]++;
                } else {
                    h[j] = 0;
                }
            }
            ret = Math.max(ret, maxInSubmatrix(h));
        }
        return ret;
    }

    static int maxInSubmatrix(int[] h) {
        int len = h.length;
        int ret = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < len; i++) {
            while (stack.peek() != -1 && h[stack.peek()] > h[i]) {
                int cur = stack.pop();
                ret = Math.max(ret, h[cur] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int cur = stack.pop();
            ret = Math.max(ret, h[cur] * (len - stack.peek() - 1));
        }
        return ret;
    }
}
