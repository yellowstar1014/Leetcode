package src.basic;

/**
 * Created by yellowstar on 8/8/15.
 */
public class SortTester {
    public static void main(String[] args) {
        int[] array = {6,6,9,4,3,1,3,5,6,3,2,7,7,7};
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
