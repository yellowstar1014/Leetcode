package src;

/**
 * Created by yellowstar on 9/16/15.
 */
public class NutsAndBolts {
    private static class NBComparator {
        public int cmp(String nut, String bolt) {
            return nut.compareToIgnoreCase(bolt);
        }
    }

    public static void main(String[] args) {
        String[] nuts = {"ab","bc","dd","gg"};
        String[] bolts = {"AB","GG","DD","BC"};
        NutsAndBolts nutsAndBolts = new NutsAndBolts();
        nutsAndBolts.sortNutsAndBolts(nuts, bolts, new NBComparator());
    }

    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
        sort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    public void sort(String[] nuts, String[] bolts, NBComparator compare, int start, int end) {
        if (start >= end) return;
        int pivot = partitionNuts(nuts, start, end, bolts[start], compare);
        partitionBolts(bolts, start, end, nuts[pivot], compare);
        sort(nuts, bolts, compare, start, pivot - 1);
        sort(nuts, bolts, compare, pivot + 1, end);
    }

    private int partitionNuts(String[] nuts, int start, int end, String pivot, NBComparator compare) {
        int i = start;
        int j = i;
        while (j < end) {
            if (compare.cmp(nuts[j], pivot) < 0) swap(nuts, i++, j++);
            else if (compare.cmp(nuts[j], pivot) == 0) swap(nuts, j, end);
            else j++;
        }
        swap(nuts, i, end);
        return i;
    }

    private int partitionBolts(String[] bolts, int start, int end, String pivot, NBComparator compare) {
        int i = start;
        int j = i;
        while (j < end) {
            if (compare.cmp(pivot, bolts[j]) > 0) swap(bolts, i++, j++);
            else if (compare.cmp(pivot, bolts[j]) == 0) swap(bolts, j, end);
            else j++;
        }
        swap(bolts, i, end);
        return i;
    }

    private void swap(String[] array, int i, int j) {
        String tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
