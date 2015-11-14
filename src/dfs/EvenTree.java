package src.dfs;

/**
 * Created by yellowstar on 11/1/15.
 */
//Problem Statement
//You are given a tree (a simple connected graph with no cycles).
// You have to remove as many edges from the tree as possible to obtain a forest with the condition that :
// Each connected component of the forest should contain an even number of vertices.
//To accomplish this, you will remove some edges from the tree. Find out the number of removed edges.
public class EvenTree {
    private static int count = 0;
    public static void main(String[] args) {
        GraphNode n0 = new GraphNode(0);
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        GraphNode n4 = new GraphNode(4);
        GraphNode n5 = new GraphNode(5);
        GraphNode n6 = new GraphNode(6);
        GraphNode n7 = new GraphNode(7);
        GraphNode n8 = new GraphNode(8);
        GraphNode n9 = new GraphNode(9);
        n0.nexts.add(n1);
        n0.nexts.add(n2);
        n1.nexts.add(n3);
        n1.nexts.add(n4);
        n1.nexts.add(n5);
        n2.nexts.add(n6);
        n3.nexts.add(n7);
        n7.nexts.add(n8);
        n8.nexts.add(n9);
        int ret = removeEage(n0);
        System.out.println(ret);
    }
    public static int removeEage(GraphNode root) {
        int num = dfs(root);
        if ((num&1) == 1) return 0;
        else return count;
    }

    private static int dfs(GraphNode cur) {
        if (cur.nexts.size() == 0) {
            return 1;
        }
        int sum = 1;
        for (GraphNode next : cur.nexts) {
            int num = dfs(next);
            sum += num;
            if ((num&1) == 1) continue;
            count++;
        }
        return sum;
    }
}
