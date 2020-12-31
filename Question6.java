// Project Euler - Question 6:
//   The difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
// Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

public class Question6 {

    public static void main(String[] _Arguments) {
        int maxRange = 100;
        int sumOfSquares = 0;
        int squareOfSums = 0;

        for (int i = 0; i <= maxRange; i++) {
            sumOfSquares += i * i;
            squareOfSums += i;
        }
        squareOfSums *= squareOfSums;

        System.out.println("Answer: " + (squareOfSums - sumOfSquares));
    }
}
