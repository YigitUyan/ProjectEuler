
// Project Euler - Question 5:
//   2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
// What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?

import java.util.ArrayList;

public class Question5
{
    public static void main(String [] _Arguments)
    {
        ArrayList<Integer> uniquePrimeDivisors = new ArrayList<>();

        for(int i = 2; i <= 20; i++)
        {
            ArrayList<Integer> currentPrimeDivisors = Utility.getPrimeDivisors(i);
            uniquePrimeDivisors.addAll(getUniquePrimeDivisors(new ArrayList<>(uniquePrimeDivisors), currentPrimeDivisors));
        }

        int primeProduct = 1;

        while(uniquePrimeDivisors.size() > 0)
        {
            primeProduct *= uniquePrimeDivisors.get(0);
            uniquePrimeDivisors.remove(0);
        }

        System.out.println("Answer: " + primeProduct);
    }

    private static ArrayList<Integer> getUniquePrimeDivisors(ArrayList<Integer> _UniqueList, ArrayList<Integer> _CurrentList)
    {
        ArrayList<Integer> newPrimeDivisors = new ArrayList<>();

        while(_CurrentList.size() > 0)
        {
            if(_UniqueList.contains(_CurrentList.get(0)))
                _UniqueList.remove(_CurrentList.get(0));
            else
                newPrimeDivisors.add(_CurrentList.get(0));

            _CurrentList.remove(0);
        }
        return newPrimeDivisors;
    }
}
