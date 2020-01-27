
// Project Euler - Question 16:
//   2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
// What is the sum of the digits of the number 2^1000?

import java.math.BigInteger;

public class Question16
{
    public static void main(String [] _Arguments)
    {
        BigInteger number = new BigInteger("1");
        int limit = 1000;

        for(int i = 0; i < limit; i++)
            number = number.multiply(new BigInteger("2"));

        BigInteger digitSum = new BigInteger("0");
        while(number.compareTo(BigInteger.ZERO) == 1)
        {
            digitSum = digitSum.add(number.mod(new BigInteger("10")));
            number = number.divide(new BigInteger("10"));
        }
        System.out.println("Answer: " + digitSum);
    }
}
