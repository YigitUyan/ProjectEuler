// Project Euler - Question 17:
//   If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
// If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
// NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.

public class Question17 {

    public static void main(String[] _Arguments) {
        int limit = 1000;
        int sum = 0;

        for (int i = 1; i <= limit; i++)
            sum += Utility.numberToWord(i).length();

        System.out.println("Answer: " + sum);
    }
}
