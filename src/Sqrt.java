package src;

/**
 * Created by yellowstar on 9/27/15.
 */
public class Sqrt {
    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.sqrt(2, 0.00001f));
        System.out.println(sqrt.sqrt2(2, 0.00001f));
        System.out.println(Math.sqrt(2));
    }
    float sqrt(float x, float eps) {
        float val = x;//最终
        float last;//保存上一个计算的值
        do {
            last = val;
            val =(val + x / val) / 2;
        } while(Math.abs(val - last) > eps);
        return val;
    }

    float sqrt2(float x, float eps) {
        if(x < 0) throw new IllegalArgumentException();
        float mid,last;
        float low = 0;
        float up = x;
        mid = (low + up) / 2;
        do {
            if (mid * mid > x)
                up = mid;
            else
                low = mid;
            last = mid;
            mid = (up + low) / 2;
        } while(Math.abs(mid - last) > eps);
        return mid;
    }
}
