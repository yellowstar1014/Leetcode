package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yellowstar on 10/4/15.
 */
// ['+', 1, 2] --> 3
// ['*', 3, ['+', 1, 10]] --> 33
// ['+', 1000, 1200] --> 2200
// ['/', 6, ['+', 1, ['*', 1, 1]]] --> 3
// ['+', 1, 2, 3] --> 6
// ['+', ['*', 1, 2], 3] --> 5
// ['-', ['*', 5, 5], ['*', 2, 2]] --> 21
public class ExpressionEval {
    public static void main(String[] args) {
        List l1 = new ArrayList<>();
        l1.add('*');
        l1.add(1);
        l1.add(2);
        List l2 = new ArrayList<>();
        l2.add('+');
        l2.add(1);
        l2.add(l1);
        List l3 = new ArrayList<>();
        l3.add('/');
        l3.add(6);
        l3.add(l2);
        ExpressionEval expressionEval = new ExpressionEval();
        System.out.println(expressionEval.evaluation(l3));

    }
    public int evaluation(List exp) {
        char op = (char)exp.get(0);
        for (int i = 1; i < exp.size(); i++) {
            if (exp.get(i) instanceof List) {
                int opd = evaluation((List)exp.get(i));
                exp.set(i, opd);
            }
        }
        int ret = 0;
        switch (op) {
            case '*':
                ret = 1;
                for (int i = 1; i < exp.size(); i++) {
                    ret *= (int)exp.get(i);
                }
                break;
            case '+':
                ret = 0;
                for (int i = 1; i < exp.size(); i++) {
                    ret += (int)exp.get(i);
                }
                break;
            case '-':
                ret = (int)exp.get(1) - (int)exp.get(2);
                break;
            case '/':
                int divider = (int) exp.get(2);
                if (divider == 0) throw new IllegalArgumentException();
                ret =  (int)exp.get(1) / divider;
                break;
            default:
                throw new IllegalArgumentException();

        }
        return ret;
    }
}
