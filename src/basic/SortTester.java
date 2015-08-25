package src.basic;

/**
 * Created by yellowstar on 8/8/15.
 */
public class SortTester {
    public static void main(String[] args) {
        int[] array = {9, 9};
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
