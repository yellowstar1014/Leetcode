package src;

/**
 * Created by yellowstar on 10/29/15.
 */
public class test {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(Integer.MIN_VALUE - 1);
        int a =1;
        for (int i = 0 ;i < 32; i++) {
            a *= 2;
            System.out.println(a);
        }
    }
}
