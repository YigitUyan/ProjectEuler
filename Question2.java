
// Project Euler - Question 2:
// By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

public class Question2
{
    public static void main(String [] _Arguments)
    {
        int t1 = 1;
        int t2 = 2;
        int sum = 2;

        while(t1 + t2 < 4000000)
        {
            int t0 = t1;
            t1 = t2;
            t2 = t0 + t1;

            if(t2 % 2 == 0)
            {
                sum += t2;
            }
        }

        System.out.println("Answer: " + sum);
    }
}
