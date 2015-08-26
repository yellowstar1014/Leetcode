package src.basic;

/**
 * Created by yellowstar on 8/8/15.
 */
public class SortTester {
    public static void main(String[] args) {
        int[] array = {2,1,3,4,5,9,5,9,5,8,8};
        Sorter sorter = new QuickSort();
        sorter.sort(array);
        print(array);
    }

    private static void print(int[] array) {
        for(int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
