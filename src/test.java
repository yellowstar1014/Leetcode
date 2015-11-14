package src;

/**
 * Created by yellowstar on 10/29/15.
 */
public class test {
    public static void main(String[] args) {
        int i = 200;
        int j = 100;
        long l = (long)i << 32 | j;
        System.out.println(l);
        i = (int)(l >> 32);
        j = (int)l;
        System.out.println(i);
        System.out.println(j);
    }
}
