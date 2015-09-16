package src;

/**
 * Created by yellowstar on 9/4/15.
 */
public class Game {
    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(game.whoWin(14 ,5, 0));
    }
    public int whoWin(int a, int b, int cur) {
        if (a == b) return cur;
        if (a < b) return whoWin(b ,a, cur);
        if (a >= b * 2) return cur;
        return whoWin(b, a - b, cur == 0 ? 1 : 0);
    }
}
