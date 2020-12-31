// Project Euler - Question 4:
//   A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
// Find the largest palindrome made from the product of two 3-digit numbers.

public class Question4 {

    public static void main(String[] _Arguments) {
        int largestPalindrome = 0;

        for (int i = 100; i < 999; i++) {
            for (int j = 100; j < 999; j++) {
                if (Utility.checkPalindrome(String.valueOf(i * j)) && largestPalindrome < (i * j))
                    largestPalindrome = i * j;

                Utility.watchProgress(900 * 900, (i - 100) * 900 + (j - 100));
            }
        }

        System.out.println("Answer: " + largestPalindrome);
    }
}
