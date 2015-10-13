package src.ExpressionTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yellowstar on 10/9/15.
 */
public class AddOperator {
    public static void main(String[] args) {
        AddOperator addOperator = new AddOperator();
        int[] nums = {4,5,1,4,6,2};
        List<String> ret = addOperator.addOperator(nums, 8);
        for (String s : ret) {
            System.out.println(s);
        }
    }
    //11.  Given 6 integers and 1 target value,write a function to get the target value using 6 integers with any on
    // these operations +,*,-,/
    public List<String> addOperator(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) throw new IllegalArgumentException();
        List<Character> sol = new ArrayList<>();
        List<List<Character>> rets = new ArrayList<>();
        helper(sol, rets, nums, 1, nums[0], nums[0], target);
        List<String> result = new ArrayList<>();
        for (List<Character> s : rets) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0, j = 0; i < len - 1; i++, j++) {
                sb.append(nums[i]);
                sb.append(s.get(j));
            }
            sb.append(nums[len - 1]);
            result.add(sb.toString());
        }
        return result;
    }

    public void helper(List<Character> sol, List<List<Character>> rets, int[] nums, int cur, int history, int value, int target) {
        if (cur == nums.length) {
            if (value == target) {
                rets.add(sol);
            }
            return;
        }
        List<Character> sol1 = new ArrayList<>(sol);
        sol1.add('+');
        helper(sol1, rets, nums, cur + 1, nums[cur], value + nums[cur], target);
        List<Character> sol2 = new ArrayList<>(sol);
        sol2.add('-');
        helper(sol2, rets, nums, cur + 1, -nums[cur], value - nums[cur], target);
        List<Character> sol3 = new ArrayList<>(sol);
        sol3.add('*');
        helper(sol3, rets, nums, cur + 1, history * nums[cur], value - history + history * nums[cur], target);
        if (nums[cur] != 0) {
            List<Character> sol4 = new ArrayList<>(sol);
            sol4.add('/');
            helper(sol4, rets, nums, cur + 1, history / nums[cur], value - history + history / nums[cur], target);
        }
    }
}
