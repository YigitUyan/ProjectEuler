
// Project Euler - Question 9:
//   A Pythagorean triplet is a set of three natural numbers, a < b < c, for which, a2 + b2 = c2
// There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find the product abc.

public class Question9
{
    // (a*b*c)^2 = a^2 + b^2 + c^2 + 2*a*b + 2*a*c + 2*b*c = 1000^2
    // = 2*a^2 + 2*b^2 + 2*a*b + 2*a*sqrt(a^2 + b^2) + 2*b*sqrt(a^2 + b^2)
    // maxValue(b) -> Assume(a = 0) -> 2*b^2 + 2*b*sqrt(b^2) = 1000^2 -> b = 500
    static int maxValue = 500;

    public static void main(String[] _Arguments)
    {
        for(int b = 0; b < maxValue; b++)
        {
            for(int a = 0; a < b; a++)
            {
                double c = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
                if(a + b + c == 1000 && b < c)
                {
                    System.out.println("Answer: " + (int)(a * b * c));
                    return;
                }
            }
        }
    }
}