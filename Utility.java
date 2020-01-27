
// Project Euler - Utility Functions:
// Helpful numerical and statistical functions.

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/** List of Functions: **/

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
// sieveAbove (*) - Returns a list of entries below the input treshold in input list.
// sieveBelow (*) - Returns a list of entries above the input treshold in input list.
// countAbove (*) - Counts the number of entries above the input treshold in given list.
// countBelow (*) - Counts the number of entries below the input treshold in given list.

// Miscelleneous Functions:
// bigSqrt (*) - Returns the square root of a BigInteger number.
// (*) -> BigInteger Safe Functions.

class Utility
{
    private static int counter = 0;
    private static double interval = 0.10;
    enum ArrayFormat {COMBINED, SEPERATE}
    enum Direction {LEFT, RIGHT}

    /** Utility Functions: **/

    static void watchProgress(int _ValueMax, int _ValueCurrent)
    {
        if(counter == 0)
        {
            System.out.println("Starting...");
            counter++;
        }
        else if(_ValueCurrent >= _ValueMax * interval * counter)
        {
            System.out.println("Progress: " + (100 * interval * counter) + "%");
            counter++;
        }
    }

    static boolean checkPalindrome(String _Number)
    {
        boolean isPalindrome = true;

        for(int i = 0; i < _Number.length()/2; i++)
            if(!_Number.substring(i, i + 1).equals(_Number.substring(_Number.length() - i - 1, _Number.length() - i)))
                isPalindrome = false;

        return isPalindrome;
    }

    static int [][] parseMatrix(String _Matrix, int _Width, int _Height)
    {
        int matrix [][] = new int [_Height][_Width];
        int row = 0;
        int col = 0;
        while(_Matrix.length() > 0 && (_Matrix.contains("\n") || _Matrix.contains(" ")))
        {
            int endIndex = getMin(sieveBelow(0, _Matrix.indexOf("\n"), _Matrix.indexOf(" ")));
            matrix [row][col] = Integer.valueOf(_Matrix.substring(0, endIndex));
            _Matrix = _Matrix.substring(endIndex);

            while(_Matrix.indexOf(" ") == 0)
                _Matrix = _Matrix.substring((" ").length());

            while(_Matrix.indexOf("\n") == 0)
                _Matrix = _Matrix.substring(("\n").length());

            col++;
            if(col >= _Width)
            {
                col = 0;
                row++;
            }
        }

        if(_Matrix.length() > 0 && !(_Matrix.contains("\n") || _Matrix.contains(" ")))
            matrix[row][col] = Integer.valueOf(_Matrix);

        return matrix;
    }

    static int [][] parseMatrix(String _Matrix)
    {
        int width = countInstances(_Matrix.substring(0, _Matrix.indexOf("\n"))," ") + 1;
        int height = countInstances(_Matrix,"\n") + 1;
        return parseMatrix(_Matrix, width, height);
    }

    static int [][] parseMatrixPlus(String _Matrix)
    {
        Scanner s = new Scanner(_Matrix);
        int width = _Matrix.substring(_Matrix.lastIndexOf("\n")).split(" ").length;
        int height = Utility.countInstances(_Matrix, "\n") + 1;
        int matrix [][] = new int [height][width];
        int lineCounter = 0;
        while(s.hasNextLine())
            matrix [lineCounter++] = Utility.stringToNumbers(s.nextLine(), " ");
        return matrix;
    }

    static int countInstances(String _String, String _Key)
    {
        int count = 0;
        while(_String.contains(_Key))
        {
            _String = _String.substring(_String.indexOf(_Key) + _Key.length());
            count++;
        }
        return count;
    }

    static int[] shiftArray(int[] _Array, Direction _Direction)
    {
        if(_Direction == Direction.LEFT)
        {
            for(int n = _Array.length - 1; n > 0; n--)
                _Array[n] = _Array[n - 1];

            _Array[0] = 0;
        }
        else if(_Direction == Direction.RIGHT)
        {
            for(int n = 0; n < _Array.length - 1; n++)
                _Array[n] = _Array[n + 1];

            _Array[_Array.length - 1] = 0;
        }

        return _Array;
    }

    static String formatArray(int[] _Array, ArrayFormat _Format)
    {
        String s = "";

        if(_Format == ArrayFormat.SEPERATE) s += "[";
        for(int n = _Array.length - 1; n >= 0; n--)
        {
            s += String.valueOf(_Array[n]);
            if(_Format == ArrayFormat.SEPERATE && n != 0) s += ",";
        }
        if(_Format == ArrayFormat.SEPERATE) s += "]";
        return s;
    }

    static int[] stringToNumbers(String _String, String _Delimiter)
    {
        String [] stringNumbers = _String.split(_Delimiter);
        int numbers [] = new int [stringNumbers.length];

        for(int i = 0; i < numbers.length; i++)
            numbers [i] = Integer.valueOf(stringNumbers [i]);

        return numbers;
    }

    static void printMatrix(int matrix [])
    {
        for(int i = 0; i < matrix.length - 1; i++)
            System.out.print(matrix [i] + " ");
        System.out.println(matrix[matrix.length - 1]);
    }

    static void printMatrix(int matrix [][])
    {
        for(int i = 0; i < matrix.length; i++)
            printMatrix(matrix [i]);
    }

    static int sumDigits(String _String)
    {
        int sum = 0;
        for(int i = 0; i < _String.length(); i++)
            sum += Character.getNumericValue(_String.charAt(i));
        return sum;
    }


    /** Arithmetic Functions: **/

    static int getFactorial(int _Number)
    {
        int factorial = _Number;
        while (_Number > 1)
        {
            _Number--;
            factorial *= _Number;
        }
        return factorial;
    }

    static ArrayList<Integer> getPrimeDivisors(int _Number)
    {
        ArrayList<Integer> primeDivisors = new ArrayList<>();
        int currentPrime = 2;

        while (_Number > 1)
        {
            if(_Number % currentPrime == 0)
            {
                primeDivisors.add(currentPrime);
                _Number /= currentPrime;
            }
            else
            {
                do {currentPrime++;}
                while(!checkPrime(currentPrime));
            }
        }
        return primeDivisors;
    }

    static boolean checkPrime(int _Number)
    {
        boolean isPrime = true;

        if(_Number == 1)
            isPrime = false;

        for(int i = 2; i <= Math.sqrt(_Number); i++)
            if(_Number % i == 0)
                isPrime = false;

        return isPrime;
    }

    static int getMax(int... _Numbers)
    {
        int maxNumber = _Numbers[0];
        for (int currentNumber : _Numbers)
            if(currentNumber > maxNumber)
                maxNumber = currentNumber;
        return maxNumber;
    }

    static int getMin(int... _Numbers)
    {
        int minNumber = _Numbers[0];
        for (int currentNumber : _Numbers)
            if(currentNumber < minNumber)
                minNumber = currentNumber;
        return minNumber;
    }

    static int[] sieveAbove(int _Treshold, int... _Numbers)
    {
        int [] numbers = new int [countBelow( _Treshold, _Numbers)];
        int currentIndex = 0;

        for(int currentNumber : _Numbers)
            if(currentNumber < _Treshold)
            {
                numbers[currentIndex] = currentNumber;
                currentIndex++;
            }
        return numbers;
    }

    static int[] sieveBelow(int _Treshold, int... _Numbers)
    {
        int [] numbers = new int [countAbove( _Treshold, _Numbers)];
        int currentIndex = 0;

        for(int currentNumber : _Numbers)
            if(currentNumber > _Treshold)
            {
                numbers[currentIndex] = currentNumber;
                currentIndex++;
            }
        return numbers;
    }

    static int countAbove(int _Treshold, int... _Numbers)
    {
        int countAbove = 0;
        for(int currentNumber : _Numbers)
            if(currentNumber > _Treshold)
                countAbove++;
        return countAbove;
    }

    static int countBelow(int _Treshold, int... _Numbers)
    {
        int countAbove = 0;
        for(int currentNumber : _Numbers)
            if(currentNumber < _Treshold)
                countAbove++;
        return countAbove;
    }

    static int sumDigits(int _Number)
    {
        return sumDigits(String.valueOf(_Number));
    }


    /** BigInteger Functions: **/

    static void watchProgress(BigInteger _ValueMax, BigInteger _ValueCurrent)
    {
        if(counter == 0)
        {
            System.out.println("Starting...");
            counter++;
        }
        else if(_ValueCurrent.compareTo(_ValueMax.divide(BigInteger.valueOf((long)(1.00 / interval))).multiply(BigInteger.valueOf(counter))) >= 0)
        {
            System.out.println("Progress: " + (100 * interval * counter) + "%");
            counter++;
        }
    }

    static ArrayList<BigInteger> getDivisors(BigInteger _Number)
    {
        ArrayList<BigInteger> divisors = new ArrayList<>();
        BigInteger currentDivisor = new BigInteger("1");
        BigInteger maxDivisor = bigSqrt(_Number);

        while(currentDivisor.compareTo(maxDivisor) == -1)
        {
            if(_Number.mod(currentDivisor).compareTo(BigInteger.ZERO) == 0)
            {
                if (_Number.divide(currentDivisor).compareTo(currentDivisor) == 0)
                {
                    divisors.add(currentDivisor);
                }
                else
                {
                    divisors.add(currentDivisor);
                    divisors.add(_Number.divide(currentDivisor));
                }
            }
            if(currentDivisor.compareTo(maxDivisor) == -1)
                currentDivisor = currentDivisor.add(BigInteger.ONE);
        }
        return divisors;
    }

    static ArrayList<BigInteger> getPrimeDivisors(BigInteger _Number)
    {
        ArrayList<BigInteger> primeDivisors = new ArrayList<>();
        BigInteger currentPrime = new BigInteger("2");

        while (_Number.compareTo(BigInteger.ONE) == 1)
        {
            if(_Number.mod(currentPrime).compareTo(BigInteger.ZERO) == 0)
            {
                primeDivisors.add(currentPrime);
                _Number = _Number.divide(currentPrime);
            }
            else
            {
                do {currentPrime = currentPrime.add(BigInteger.ONE);}
                while(!checkPrime(currentPrime));
            }
        }
        return primeDivisors;
    }

    static boolean checkPrime(BigInteger _BigInteger)
    {
        boolean isPrime = true;

        if(_BigInteger.compareTo(BigInteger.ONE) == 0)
            isPrime = false;

        for(BigInteger i = new BigInteger("2"); i.compareTo(bigSqrt(_BigInteger)) <= 0; i = i.add(new BigInteger("1")))
            if(_BigInteger.mod(i).compareTo(new BigInteger("0")) == 0)
                isPrime = false;

        return isPrime;
    }

    static BigInteger getMax(BigInteger... _Numbers)
    {
        BigInteger maxNumber = _Numbers[0];
        for (BigInteger currentNumber : _Numbers)
            if(currentNumber.compareTo(maxNumber) == 1)
                maxNumber = currentNumber;
        return maxNumber;
    }

    static BigInteger getMin(BigInteger... _Numbers)
    {
        BigInteger minNumber = _Numbers[0];
        for (BigInteger currentNumber : _Numbers)
            if(currentNumber.compareTo(minNumber) == -1)
                minNumber = currentNumber;
        return minNumber;
    }

    static BigInteger[] sieveAbove(BigInteger _Treshold, BigInteger... _Numbers)
    {
        BigInteger[] numbers = new BigInteger[countBelow( _Treshold, _Numbers)];
        int currentIndex = 0;

        for(BigInteger currentNumber : _Numbers)
            if(currentNumber.compareTo(_Treshold) == -1)
            {
                numbers[currentIndex] = currentNumber;
                currentIndex++;
            }
        return numbers;
    }

    static BigInteger[] sieveBelow(BigInteger _Treshold, BigInteger... _Numbers)
    {
        BigInteger[] numbers = new BigInteger[countAbove( _Treshold, _Numbers)];
        int currentIndex = 0;

        for(BigInteger currentNumber : _Numbers)
            if(currentNumber.compareTo(_Treshold) == 1)
            {
                numbers[currentIndex] = currentNumber;
                currentIndex++;
            }
        return numbers;
    }

    static int countAbove(BigInteger _Treshold, BigInteger... _Numbers)
    {
        int countAbove = 0;
        for(BigInteger currentNumber : _Numbers)
            if(currentNumber.compareTo(_Treshold) == 1)
                countAbove++;
        return countAbove;
    }

    static int countBelow(BigInteger _Treshold, BigInteger... _Numbers)
    {
        int countAbove = 0;
        for(BigInteger currentNumber : _Numbers)
            if(currentNumber.compareTo(_Treshold) == -1)
                countAbove++;
        return countAbove;
    }

    static BigInteger getFactorial(BigInteger _Number)
    {
        BigInteger factorial = _Number;
        while (_Number.compareTo(BigInteger.ONE) > 0)
        {
            _Number = _Number.subtract(BigInteger.ONE);
            factorial = factorial.multiply(_Number);
        }
        return factorial;
    }


    /** Miscellaneous Functions: **/

    private static BigInteger bigSqrt(BigInteger _Number)
    {
        BigInteger div = BigInteger.ZERO.setBit(_Number.bitLength() / 2);
        BigInteger div2 = div;

        while(true)
        {
            BigInteger sqrt = div.add(_Number.divide(div)).shiftRight(1);
            if (sqrt.equals(div) || sqrt.equals(div2))
                return sqrt;
            div2 = div;
            div = sqrt;
        }
    }

    public static String numberToWord(int _Number)
    {
        String delimiter = "";
        String seperator = "and" + delimiter;

        if(_Number < 0)
            return "negative" + delimiter + numberToWord(_Number * -1, delimiter, seperator);
        else if (_Number > 0)
            return numberToWord(_Number, delimiter, seperator);
        else
            return "zero";
    }

    private static String numberToWord(int _Number, String _Delimiter, String _Seperator)
    {
        int digitCount = String.valueOf(_Number).length();

        if(digitCount > 6)
        {
            return "NumberTooLarge";
        }
        else if (digitCount >= 4)
        {
            return numberToWord(_Number / 1000, _Delimiter, _Seperator) + _Delimiter + "thousand"
                    + _Delimiter + numberToWord(_Number % 1000, _Delimiter, _Seperator);
        }
        else if (digitCount >= 3)
        {
            if(_Number % 100 == 0)
                return numberToWord(_Number / 100, _Delimiter, _Seperator) + _Delimiter + "hundred"
                        + _Delimiter + numberToWord(_Number % 100, _Delimiter, _Seperator);
            else
                return numberToWord(_Number / 100, _Delimiter, _Seperator) + _Delimiter + "hundred"
                        + _Delimiter + _Seperator + numberToWord(_Number % 100, _Delimiter, _Seperator);
        }
        else if(digitCount == 2)
        {
            int digitFirst = _Number / 10;
            int digitSecond = _Number % 10;

            switch (digitFirst)
            {
                case 0:
                    return numberToWord(digitSecond);
                case 2:
                    return "twenty" + _Delimiter + numberToWord(digitSecond, _Delimiter, _Seperator);
                case 3:
                    return "thirty" + _Delimiter + numberToWord(digitSecond, _Delimiter, _Seperator);
                case 4:
                    return "forty" + _Delimiter + numberToWord(digitSecond, _Delimiter, _Seperator);
                case 5:
                    return "fifty" + _Delimiter + numberToWord(digitSecond, _Delimiter, _Seperator);
                case 6:
                    return "sixty" + _Delimiter + numberToWord(digitSecond, _Delimiter, _Seperator);
                case 7:
                    return "seventy" + _Delimiter + numberToWord(digitSecond, _Delimiter, _Seperator);
                case 8:
                    return "eighty" + _Delimiter + numberToWord(digitSecond, _Delimiter, _Seperator);
                case 9:
                    return "ninety" + _Delimiter + numberToWord(digitSecond, _Delimiter, _Seperator);
                default:
                    switch (digitSecond)
                    {
                        case 0:
                            return "ten";
                        case 1:
                            return "eleven";
                        case 2:
                            return "twelve";
                        case 3:
                            return "thirteen";
                        case 5:
                            return "fifteen";
                        case 8:
                            return "eighteen";
                        default:
                            return numberToWord(digitSecond, _Delimiter, _Seperator) + "teen";
                    }
            }
        }
        else if(digitCount == 1)
        {
            switch(_Number)
            {
                case 1:
                    return "one";
                case 2:
                    return "two";
                case 3:
                    return "three";
                case 4:
                    return "four";
                case 5:
                    return "five";
                case 6:
                    return "six";
                case 7:
                    return "seven";
                case 8:
                    return "eight";
                case 9:
                    return "nine";
                default:
                    return "";
            }
        }
        else
        {
            return "Fatal Error";
        }
    }
}
