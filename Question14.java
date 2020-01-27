
// Project Euler - Question 14:
//   The following iterative sequence is defined for the set of positive integers:
//   n → n/2 (n is even), n → 3n + 1 (n is odd)
//   Using the rule above and starting with 13, we generate the following sequence:
//   13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
// Which starting number, under one million, produces the longest chain?

import java.math.BigInteger;

public class Question14
{
    public static void main(String [] _Arguments)
    {
        BigInteger scanInterval = new BigInteger("1000000");
        BigInteger chainLengthMax = new BigInteger("0");
        BigInteger scanCurrent = new BigInteger("1");
        BigInteger scanBest = new BigInteger("1");

        while(scanCurrent.compareTo(scanInterval) == -1)
        {
            BigInteger chainLength = new BigInteger("1");
            BigInteger currentNumber = scanCurrent;

            while(!(currentNumber.compareTo(BigInteger.ONE) == 0))
            {
                if(currentNumber.mod(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0)
                    currentNumber = currentNumber.divide(new BigInteger("2"));
                else
                    currentNumber = currentNumber.multiply(new BigInteger("3")).add(BigInteger.ONE);

                chainLength = chainLength.add(BigInteger.ONE);
            }

            if(chainLength.compareTo(chainLengthMax) == 1)
            {
                chainLengthMax = chainLength;
                scanBest = scanCurrent;
            }

            scanCurrent = scanCurrent.add(BigInteger.ONE);
            Utility.watchProgress(scanInterval, scanCurrent);
        }

        System.out.println("Answer: " + scanBest);
    }
}
