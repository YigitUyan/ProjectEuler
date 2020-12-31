// Project Euler - Question 20:
// Find the sum of the digits in the number 100!

import java.math.BigInteger;

public class Question20 {

    public static void main(String[] _Arguments) {
        int target = 100;

        BigInteger factorial = Utility.getFactorial(BigInteger.valueOf(target));
        System.out.println("Answer: " + Utility.sumDigits(factorial.toString()));
    }
}
