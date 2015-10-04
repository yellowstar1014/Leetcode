package src;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yellowstar on 9/30/15.
 */
public class PermuationSequence {
    public static void main(String[] args) {
        PermuationSequence permuationSequence = new PermuationSequence();
        BigInteger ret = permuationSequence.sequenceOrder("baaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(ret);
    }
    public BigInteger sequenceOrder(String s) {
        char[] cs = s.toCharArray();
        int len = cs.length;
        Arrays.sort(cs);
        List<Character> clist = new LinkedList<>();
        List<Integer> nlist = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < len - 1; i++) {
            count++;
            if (cs[i] != cs[i + 1]) {
                clist.add(cs[i]);
                nlist.add(count);
                count = 0;
            }
        }
        count++;
        clist.add(cs[len - 1]);
        nlist.add(count);
        return helper(clist, nlist, s.toCharArray(), 0);
    }

    private BigInteger helper(List<Character> clist, List<Integer> nlist, char[] cs, int cur) {
        if (cur == cs.length) return BigInteger.ZERO;
        BigInteger count = BigInteger.ZERO;
        int i = 0;
        for (; i < clist.size() && cs[cur] > clist.get(i); i++) {
            if (nlist.get(i) == 0) continue;
            nlist.set(i, nlist.get(i) - 1);
            count = count.add(sequenceNumber(nlist));
            nlist.set(i, nlist.get(i) + 1);
        }
        nlist.set(i, nlist.get(i) - 1);
        count.add(helper(clist, nlist, cs, cur + 1));
        return count;
    }

    private BigInteger sequenceNumber(List<Integer> nlist) {
        int count = 0;
        BigInteger divider = BigInteger.ONE;
        for (Integer i : nlist) {
            count += i;
            if (i >= 2) {
                divider = divider.multiply(A(i));
            }
        }
        return A(count).divide(divider);
    }

    private BigInteger A(int n) {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            ret = ret.multiply(BigInteger.valueOf(i));
        }
        return ret;
    }
}
