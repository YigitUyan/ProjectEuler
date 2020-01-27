
// Project Euler - Question 7:
//   By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
// What is the 10001st prime number?

import java.math.BigInteger;

public class Question7
{
    public static void main(String[] _Arguments)
    {
        BigInteger currentPrime = new BigInteger("1");

        int primeCount = 0;
        int primeTarget = 10001;

        while(primeCount < primeTarget)
        {
            do
            {
                currentPrime = currentPrime.add(BigInteger.ONE);
            }
            while(!Utility.checkPrime(currentPrime));

            Utility.watchProgress(primeTarget, primeCount);

            primeCount++;
        }

        System.out.println("Answer: " + currentPrime);
    }
}
