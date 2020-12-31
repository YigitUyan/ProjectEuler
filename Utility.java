// Project Euler - Utility Functions:
// Helpful numerical and statistical functions.

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;


/** List of Functions **/

// Utility Functions:
// watchProgress (*) - Reports progress when scanning a large domain for answers.
// checkPalindrome - Checks if a given String is a Palindrome.
// parseMatrix - Parses a given string into a matrix of integers.
// countInstances - Returns the number of instances of the Key in a given String.

// Arithmetic Functions:
// getPrimeDivisors (*) - Returns a list of Prime Divisors of the given input.
// getFactorial (*) - Returns the factorial of the given input.
// checkPrime (*) - Checks if a given Number is Prime.
// getMax (*) - Returns the maximum of a given list of numbers.
// getMin (*) - Returns the minimum of a given list of numbers.
// sieveAbove (*) - Returns a list of entries below the input threshold in input list.
// sieveBelow (*) - Returns a list of entries above the input threshold in input list.
// countAbove (*) - Counts the number of entries above the input threshold in given list.
// countBelow (*) - Counts the number of entries below the input threshold in given list.

// Miscellaneous Functions:
// bigSqrt (*) - Returns the square root of a BigInteger number.
// (*) -> BigInteger Safe Functions.


class Utility {

    private static int counter = 0;
    private static double interval = 0.10;

    enum ArrayFormat {COMBINED, SEPARATE}

    enum Direction {LEFT, RIGHT}


    /** Utility Functions: **/

    static void watchProgress(int _valueMax, int _valueCurrent) {
        if (counter == 0) {
            System.out.println("Starting...");
            counter++;
        } else if (_valueCurrent >= _valueMax * interval * counter) {
            System.out.println("Progress: " + (100 * interval * counter) + "%");
            counter++;
        }
    }

    static boolean checkPalindrome(String _number) {
        boolean isPalindrome = true;

        for (int i = 0; i < _number.length() / 2; i++)
            if (!_number.substring(i, i + 1).equals(_number.substring(_number.length() - i - 1, _number.length() - i)))
                isPalindrome = false;

        return isPalindrome;
    }

    static int[][] parseMatrix(String _matrix, int _width, int _height) {
        int[][] matrix = new int[_height][_width];
        int row = 0;
        int col = 0;
        while (_matrix.length() > 0 && (_matrix.contains("\n") || _matrix.contains(" "))) {
            int endIndex = getMin(sieveBelow(0, _matrix.indexOf("\n"), _matrix.indexOf(" ")));
            matrix[row][col] = Integer.valueOf(_matrix.substring(0, endIndex));
            _matrix = _matrix.substring(endIndex);

            while (_matrix.indexOf(" ") == 0)
                _matrix = _matrix.substring((" ").length());

            while (_matrix.indexOf("\n") == 0)
                _matrix = _matrix.substring(("\n").length());

            col++;
            if (col >= _width) {
                col = 0;
                row++;
            }
        }

        if (_matrix.length() > 0 && !(_matrix.contains("\n") || _matrix.contains(" ")))
            matrix[row][col] = Integer.valueOf(_matrix);

        return matrix;
    }

    static int[][] parseMatrix(String _matrix) {
        int width = countInstances(_matrix.substring(0, _matrix.indexOf("\n")), " ") + 1;
        int height = countInstances(_matrix, "\n") + 1;
        return parseMatrix(_matrix, width, height);
    }

    static int[][] parseMatrixPlus(String _matrix) {
        Scanner s = new Scanner(_matrix);
        int width = _matrix.substring(_matrix.lastIndexOf("\n")).split(" ").length;
        int height = Utility.countInstances(_matrix, "\n") + 1;
        int[][] matrix = new int[height][width];
        int lineCounter = 0;
        while (s.hasNextLine())
            matrix[lineCounter++] = Utility.stringToNumbers(s.nextLine(), " ");
        return matrix;
    }

    static int countInstances(String _string, String _key) {
        int count = 0;
        while (_string.contains(_key)) {
            _string = _string.substring(_string.indexOf(_key) + _key.length());
            count++;
        }
        return count;
    }

    static int[] shiftArray(int[] _array, Direction _direction) {
        if (_direction == Direction.LEFT) {
            for (int n = _array.length - 1; n > 0; n--)
                _array[n] = _array[n - 1];

            _array[0] = 0;
        } else if (_direction == Direction.RIGHT) {
            for (int n = 0; n < _array.length - 1; n++)
                _array[n] = _array[n + 1];

            _array[_array.length - 1] = 0;
        }

        return _array;
    }

    static String formatArray(int[] _array, ArrayFormat _format) {
        String s = "";

        if (_format == ArrayFormat.SEPARATE) s += "[";
        for (int n = _array.length - 1; n >= 0; n--) {
            s += String.valueOf(_array[n]);
            if (_format == ArrayFormat.SEPARATE && n != 0) s += ",";
        }
        if (_format == ArrayFormat.SEPARATE) s += "]";
        return s;
    }

    static int[] stringToNumbers(String _string, String _delimiter) {
        String[] stringNumbers = _string.split(_delimiter);
        int numbers[] = new int[stringNumbers.length];

        for (int i = 0; i < numbers.length; i++)
            numbers[i] = Integer.parseInt(stringNumbers[i]);

        return numbers;
    }

    static void printMatrix(int[] _matrix) {
        for (int i = 0; i < _matrix.length - 1; i++)
            System.out.print(_matrix[i] + " ");
        System.out.println(_matrix[_matrix.length - 1]);
    }

    static void printMatrix(int[][] _matrix) {
        for (int i = 0; i < _matrix.length; i++)
            printMatrix(_matrix[i]);
    }

    static int sumDigits(String _string) {
        int sum = 0;
        for (int i = 0; i < _string.length(); i++)
            sum += Character.getNumericValue(_string.charAt(i));
        return sum;
    }


    /** Arithmetic Functions: **/

    static int getFactorial(int _number) {
        int factorial = _number;
        while (_number > 1) {
            _number--;
            factorial *= _number;
        }
        return factorial;
    }

    static ArrayList<Integer> getPrimeDivisors(int _number) {
        ArrayList<Integer> primeDivisors = new ArrayList<>();
        int currentPrime = 2;

        while (_number > 1) {
            if (_number % currentPrime == 0) {
                primeDivisors.add(currentPrime);
                _number /= currentPrime;
            } else {
                do {
                    currentPrime++;
                }
                while (!checkPrime(currentPrime));
            }
        }
        return primeDivisors;
    }

    static boolean checkPrime(int _number) {
        boolean isPrime = true;

        if (_number == 1)
            isPrime = false;

        for (int i = 2; i <= Math.sqrt(_number); i++)
            if (_number % i == 0)
                isPrime = false;

        return isPrime;
    }

    static int getMax(int... _numbers) {
        int maxNumber = _numbers[0];
        for (int currentNumber : _numbers)
            if (currentNumber > maxNumber)
                maxNumber = currentNumber;
        return maxNumber;
    }

    static int getMin(int... _numbers) {
        int minNumber = _numbers[0];
        for (int currentNumber : _numbers)
            if (currentNumber < minNumber)
                minNumber = currentNumber;
        return minNumber;
    }

    static int[] sieveAbove(int _threshold, int... _numbers) {
        int[] numbers = new int[countBelow(_threshold, _numbers)];
        int currentIndex = 0;

        for (int currentNumber : _numbers)
            if (currentNumber < _threshold) {
                numbers[currentIndex] = currentNumber;
                currentIndex++;
            }
        return numbers;
    }

    static int[] sieveBelow(int _threshold, int... _numbers) {
        int[] numbers = new int[countAbove(_threshold, _numbers)];
        int currentIndex = 0;

        for (int currentNumber : _numbers)
            if (currentNumber > _threshold) {
                numbers[currentIndex] = currentNumber;
                currentIndex++;
            }
        return numbers;
    }

    static int countAbove(int _threshold, int... _numbers) {
        int countAbove = 0;
        for (int currentNumber : _numbers)
            if (currentNumber > _threshold)
                countAbove++;
        return countAbove;
    }

    static int countBelow(int _threshold, int... _numbers) {
        int countAbove = 0;
        for (int currentNumber : _numbers)
            if (currentNumber < _threshold)
                countAbove++;
        return countAbove;
    }

    static int sumDigits(int _number) {
        return sumDigits(String.valueOf(_number));
    }


    /** BigInteger Functions: **/

    static void watchProgress(BigInteger _valueMax, BigInteger _valueCurrent) {
        if (counter == 0) {
            System.out.println("Starting...");
            counter++;
        } else if (_valueCurrent.compareTo(
                _valueMax.divide(BigInteger.valueOf((long) (1.00 / interval)))
                        .multiply(BigInteger.valueOf(counter))) >= 0) {
            System.out.println("Progress: " + (100 * interval * counter) + "%");
            counter++;
        }
    }

    static ArrayList<BigInteger> getDivisors(BigInteger _number) {
        ArrayList<BigInteger> divisors = new ArrayList<>();
        BigInteger currentDivisor = new BigInteger("1");
        BigInteger maxDivisor = bigSqrt(_number);

        while (currentDivisor.compareTo(maxDivisor) == -1) {
            if (_number.mod(currentDivisor).compareTo(BigInteger.ZERO) == 0) {
                if (_number.divide(currentDivisor).compareTo(currentDivisor) == 0) {
                    divisors.add(currentDivisor);
                } else {
                    divisors.add(currentDivisor);
                    divisors.add(_number.divide(currentDivisor));
                }
            }
            if (currentDivisor.compareTo(maxDivisor) == -1)
                currentDivisor = currentDivisor.add(BigInteger.ONE);
        }
        return divisors;
    }

    static ArrayList<BigInteger> getPrimeDivisors(BigInteger _number) {
        ArrayList<BigInteger> primeDivisors = new ArrayList<>();
        BigInteger currentPrime = new BigInteger("2");

        while (_number.compareTo(BigInteger.ONE) == 1) {
            if (_number.mod(currentPrime).compareTo(BigInteger.ZERO) == 0) {
                primeDivisors.add(currentPrime);
                _number = _number.divide(currentPrime);
            } else {
                do {
                    currentPrime = currentPrime.add(BigInteger.ONE);
                }
                while (!checkPrime(currentPrime));
            }
        }
        return primeDivisors;
    }

    static boolean checkPrime(BigInteger _number) {
        boolean isPrime = true;

        if (_number.compareTo(BigInteger.ONE) == 0)
            isPrime = false;

        for (BigInteger i = new BigInteger("2"); i.compareTo(bigSqrt(_number)) <= 0; i = i.add(new BigInteger("1")))
            if (_number.mod(i).compareTo(new BigInteger("0")) == 0)
                isPrime = false;

        return isPrime;
    }

    static BigInteger getMax(BigInteger... _numbers) {
        BigInteger maxNumber = _numbers[0];
        for (BigInteger currentNumber : _numbers)
            if (currentNumber.compareTo(maxNumber) == 1)
                maxNumber = currentNumber;
        return maxNumber;
    }

    static BigInteger getMin(BigInteger... _numbers) {
        BigInteger minNumber = _numbers[0];
        for (BigInteger currentNumber : _numbers)
            if (currentNumber.compareTo(minNumber) == -1)
                minNumber = currentNumber;
        return minNumber;
    }

    static BigInteger[] sieveAbove(BigInteger _threshold, BigInteger... _numbers) {
        BigInteger[] numbers = new BigInteger[countBelow(_threshold, _numbers)];
        int currentIndex = 0;

        for (BigInteger currentNumber : _numbers)
            if (currentNumber.compareTo(_threshold) == -1) {
                numbers[currentIndex] = currentNumber;
                currentIndex++;
            }
        return numbers;
    }

    static BigInteger[] sieveBelow(BigInteger _threshold, BigInteger... _numbers) {
        BigInteger[] numbers = new BigInteger[countAbove(_threshold, _numbers)];
        int currentIndex = 0;

        for (BigInteger currentNumber : _numbers)
            if (currentNumber.compareTo(_threshold) == 1) {
                numbers[currentIndex] = currentNumber;
                currentIndex++;
            }
        return numbers;
    }

    static int countAbove(BigInteger _threshold, BigInteger... _numbers) {
        int countAbove = 0;
        for (BigInteger currentNumber : _numbers)
            if (currentNumber.compareTo(_threshold) == 1)
                countAbove++;
        return countAbove;
    }

    static int countBelow(BigInteger _threshold, BigInteger... _numbers) {
        int countAbove = 0;
        for (BigInteger currentNumber : _numbers)
            if (currentNumber.compareTo(_threshold) == -1)
                countAbove++;
        return countAbove;
    }

    static BigInteger getFactorial(BigInteger _number) {
        BigInteger factorial = _number;
        while (_number.compareTo(BigInteger.ONE) > 0) {
            _number = _number.subtract(BigInteger.ONE);
            factorial = factorial.multiply(_number);
        }
        return factorial;
    }


    /** Miscellaneous Functions: **/

    private static BigInteger bigSqrt(BigInteger _number) {
        BigInteger div = BigInteger.ZERO.setBit(_number.bitLength() / 2);
        BigInteger div2 = div;

        while (true) {
            BigInteger sqrt = div.add(_number.divide(div)).shiftRight(1);
            if (sqrt.equals(div) || sqrt.equals(div2))
                return sqrt;
            div2 = div;
            div = sqrt;
        }
    }

    public static String numberToWord(int _number) {
        String delimiter = "";
        String seperator = "and" + delimiter;

        if (_number < 0)
            return "negative" + delimiter + numberToWord(_number * -1, delimiter, seperator);
        else if (_number > 0)
            return numberToWord(_number, delimiter, seperator);
        else
            return "zero";
    }

    private static String numberToWord(int _number, String _delimiter, String _separator) {
        int digitCount = String.valueOf(_number).length();

        if (digitCount > 6) {
            return "NumberTooLarge";
        } else if (digitCount >= 4) {
            return numberToWord(_number / 1000, _delimiter, _separator) + _delimiter + "thousand"
                    + _delimiter + numberToWord(_number % 1000, _delimiter, _separator);
        } else if (digitCount >= 3) {
            if (_number % 100 == 0)
                return numberToWord(_number / 100, _delimiter, _separator) + _delimiter + "hundred"
                        + _delimiter + numberToWord(_number % 100, _delimiter, _separator);
            else
                return numberToWord(_number / 100, _delimiter, _separator) + _delimiter + "hundred"
                        + _delimiter + _separator + numberToWord(_number % 100, _delimiter, _separator);
        } else if (digitCount == 2) {
            int digitFirst = _number / 10;
            int digitSecond = _number % 10;

            switch (digitFirst) {
                case 0: return numberToWord(digitSecond);
                case 2: return "twenty" + _delimiter + numberToWord(digitSecond, _delimiter, _separator);
                case 3: return "thirty" + _delimiter + numberToWord(digitSecond, _delimiter, _separator);
                case 4: return "forty" + _delimiter + numberToWord(digitSecond, _delimiter, _separator);
                case 5: return "fifty" + _delimiter + numberToWord(digitSecond, _delimiter, _separator);
                case 6: return "sixty" + _delimiter + numberToWord(digitSecond, _delimiter, _separator);
                case 7: return "seventy" + _delimiter + numberToWord(digitSecond, _delimiter, _separator);
                case 8: return "eighty" + _delimiter + numberToWord(digitSecond, _delimiter, _separator);
                case 9: return "ninety" + _delimiter + numberToWord(digitSecond, _delimiter, _separator);
                default:
                    switch (digitSecond) {
                        case 0: return "ten";
                        case 1: return "eleven";
                        case 2: return "twelve";
                        case 3: return "thirteen";
                        case 5: return "fifteen";
                        case 8: return "eighteen";
                        default: return numberToWord(digitSecond, _delimiter, _separator) + "teen";
                    }
            }
        } else if (digitCount == 1) {
            switch (_number) {
                case 1: return "one";
                case 2: return "two";
                case 3: return "three";
                case 4: return "four";
                case 5: return "five";
                case 6: return "six";
                case 7: return "seven";
                case 8: return "eight";
                case 9: return "nine";
                default: return "";
            }
        } else {
            return "Fatal Error";
        }
    }
}
