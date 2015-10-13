package src.String;

/**
 * Created by yellowstar on 10/11/15.
 */
public class RandomWordsInString {
    public static void main(String[] args) {
        RandomWordsInString randomWordsInString = new RandomWordsInString();
        String ret = randomWordsInString.random("I abcedfg you so much");
        System.out.println(ret);
    }

    public String random(String original) {
        String[] ss = original.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : ss) {
            sb.append(shuffle(s));
            sb.append(" ");
        }
        return sb.toString();
    }

    private String shuffle(String word) {
        if (word.length() <= 2) return word;
        char[] cs = word.toCharArray();
        for (int i = word.length() - 2; i >= 1; i--) {
            int j = (int)(Math.random() * i) + 1;
            swap(cs, i, j);
        }
        return new String(cs);
    }

    private void swap(char[] cs, int i, int j) {
        char tmp = cs[i];
        cs[i] = cs[j];
        cs[j] = tmp;
    }
}
