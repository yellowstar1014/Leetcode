package src;

/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/
public class SolutionValidPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("Aa"));
    }
    public static boolean isPalindrome(String s) {
        if (s.equals("")) return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (!isAlphanumeric(s.charAt(left))) left++;
            else if (!isAlphanumeric(s.charAt(right))) right--;
            else if (isEqual(s.charAt(left), s.charAt(right))) {
                left++;
                right--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphanumeric(char c) {
        if (c <= '9' && c >= '0' || c <= 'z' && c >= 'A') return true;
        else return false;
    }

    public static boolean isEqual(char c1, char c2) {
        if (c1 == c2) return true;
        else if (c1 <= 'z' && c1 >= 'a' && c2 <= 'Z' && c2 >= 'A' && c1 == (c2 + 32)) {
            return true;
        }
        else if (c1 <= 'Z' && c1 >= 'A' && c2 <= 'z' && c2 >= 'a' && c1 == (c2 - 32)) {
            return true;
        }
        else return false;
    }
}
