package src.basic;

/**
 * Created by yellowstar on 10/20/15.
 */
public class MoveZero {
    public static void main(String[] args) {
        int[] array = {1, 0, 0, 1};
        System.out.println(moveZero(array));
    }

    public static int moveZero(int[] array) {
        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            while (l <= r && array[l] != 0) l++;
            while (l <= r && array[r] == 0) r--;
            if (l < r) array[l++] = array[r--];
        }
        return l;
    }
}
