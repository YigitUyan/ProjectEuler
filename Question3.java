
// Project Euler - Question 3:
// What is the largest prime factor of the number 600851475143 ?

import java.math.BigInteger;

public class Question3
{
    public static void main(String [] _Arguments)
    {
        BigInteger number = new BigInteger("600851475143");
        BigInteger root = new BigInteger("775146");

        BigInteger lFactor = new BigInteger("0");
        BigInteger xFactor = new BigInteger("2");

        while(lFactor.compareTo(number) == -1 && xFactor.compareTo(root) == -1)
        {
            if(number.mod(xFactor).intValue() == 0)
            {
                lFactor = xFactor;
            }
            xFactor = xFactor.add(new BigInteger("1"));

            while(!Utility.checkPrime(xFactor) && xFactor.compareTo(root) == -1)
            {
                xFactor = xFactor.add(new BigInteger("1"));
            }
            Utility.watchProgress(root, xFactor);
        }

        System.out.println("Answer: " + lFactor.toString());
    }
}
