import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    // sort the left edge and right edge respectively by their x coordinates
    // put each height into map
    // same x or y coordinate
    private final static int left = 0;
    private final static int right = 1;
    private final static int height = 2;
    private class Node implements Comparable<Node>{
        public int x;
        public int y;
        public int type;
        public Node(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(Node node) {
            if (this.x > node.x) return 1;
            else if (this.x < node.x) return -1;
            else if (this.type == left && node.type == left) {
                if (this.y > node.y) return -1;
                else return 1;
            }
            else if (this.type == right && node.type == right) {
                if (this.y > node.y) return 1;
                else return -1;
            }
            else if (this.type == left) return -1;
            else return 1;
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][]buildings = {{0,2,3}, {2,5,3}};
        sol.getSkyline(buildings);
    }
    public List<int[]> getSkyline(int[][] buildings) {
        List<Node> list = new ArrayList();
        for (int[] building : buildings) {
            list.add(new Node(building[left], building[height], left));
            list.add(new Node(building[right], building[height],right));
        }
        Collections.sort(list);
        PriorityQueue<Integer> heightHeap = new PriorityQueue(20, Collections.reverseOrder());
        heightHeap.offer(0);
        List<int[]> rets = new ArrayList<>();
        for (Node node : list) {
            if (node.type == left) {
                if (node.y > heightHeap.peek()) rets.add(new int[]{node.x, node.y});
                heightHeap.offer(node.y);
            }
            else {
                heightHeap.remove(node.y);
                int curH = heightHeap.peek();
                if (node.y != curH) rets.add(new int[]{node.x, curH});
            }
        }
        return rets;
    }
}