package src;

import java.util.Arrays;

/**
 * Created by yellowstar on 9/30/15.
 */
public class SellTicket {
    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
        int[] a = new int[] {5, 5, 5, 5, 6, 8, 4};
        int ret = sellTicket.sell(4, a);
        System.out.println(ret);
    }
    //n m a[]
    public int sell(int m, int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int total = 0;
        while (m > 0) {
            int i = n - 1;
            int cur = a[n - 1];
            while (m > 0 && i >= 0 && a[i] == cur) {
                total += cur;
                m--;
                a[i--]--;
            }
        }
        return total;
    }
}
