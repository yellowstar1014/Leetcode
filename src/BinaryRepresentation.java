package src;

/**
 * Created by yellowstar on 9/18/15.
 */
public class BinaryRepresentation {
    // negative number
    // number without decimal part
    // integer part = "0" -> return "0"
    // decimal part = "0" -> return ""
    public static void main(String[] args) {
        BinaryRepresentation binaryRepresentation = new BinaryRepresentation();
        String ret = binaryRepresentation.binaryRepresentation("3.5");
    }

    public String binaryRepresentation(String n) {
        int dot = n.indexOf('.');
        if (dot == -1) return parseInt(Integer.parseInt(n));
        int intPart = Integer.parseInt(n.substring(0, dot));
        double decPart = Double.parseDouble(n.substring(dot, n.length()));
        String sDec = parseDec(decPart);
        if (sDec.equals("ERROR")) return sDec;
        String sInt = parseInt(intPart);
        return sDec.isEmpty() ? sInt : sInt + '.' + sDec ;
    }

    private String parseInt(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append((n & 1) == 1 ? '1' : '0');
            n >>= 1;
        }

        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }

    private String parseDec(double decPart) {
        StringBuilder sb = new StringBuilder();
        while (decPart > 0.0 && sb.length() <= 32) {
            decPart *= 2;
            if (decPart >= 1.0) {
                decPart -= 1.0;
                sb.append('1');
            }
            else {
                sb.append('0');
            }
        }
        if (sb.length() > 32) return "ERROR";
        return sb.toString();
    }
}
