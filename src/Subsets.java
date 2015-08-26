package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yellowstar on 8/25/15.
 */
public class Subsets {
    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = {1,2,3,4,5,6,7,8,10,0};
        subsets.subsets(nums);
    }
    // it could be seen as a graph with every two nodes linked and all nodes are linked to end node;
    // the problem -> find every path from any node to end node // rule -> nodes on the path have to be non-descending
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> rets = new ArrayList<>();
        dfs(nums, 0, rets, new ArrayList<Integer>());
        return rets;
    }

    private void dfs(int[] nums, int cur, List<List<Integer>> rets, List<Integer> oneSol) {
        if (cur == nums.length) {
            rets.add(oneSol);
            return;
        }
        for (int i = cur; i < nums.length; i++) {
            List<Integer> newSol = new ArrayList<>(oneSol);
            newSol.add(nums[i]);
            dfs(nums, i + 1, rets, newSol);
        }
        rets.add(oneSol);
    }
}
