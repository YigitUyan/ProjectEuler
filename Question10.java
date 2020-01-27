
// Project Euler - Question 10:
//   The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
// Find the sum of all the primes below two million.

import java.math.BigInteger;

public class Question10
{
    public static void main(String[] _Arguments)
    {
        BigInteger primeSum = new BigInteger("0");
        int maxValue = 2000000;

        for(int i = 0; i < maxValue; i++)
        {
            if(Utility.checkPrime(i))
            {
                primeSum = primeSum.add(BigInteger.valueOf(i));
            }
            Utility.watchProgress(maxValue, i);
        }
        System.out.println("Answer: " + primeSum);
    }
}
