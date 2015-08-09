package src.basic;

/**
 * Created by yellowstar on 8/8/15.
 */
public class SortTester {
    public static void main(String[] args) {
        int[] array = {6,7,1,3,2,5,8,7,7,9};
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
