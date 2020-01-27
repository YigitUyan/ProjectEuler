
// Project Euler - Question 15:
//   Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
// How many such routes are there through a 20×20 grid?

import java.math.BigInteger;

public class Question15
{
    public static void main(String [] _Arguments)
    {
        int latticeWidth = 20;
        int latticeHeight = 20;

        BigInteger lattice [][] = new BigInteger[latticeWidth + 1][latticeHeight + 1];
        for(int i = 0; i < latticeWidth + 1; i++)
        {
            for(int j = 0; j < latticeHeight + 1; j++)
            {
                lattice[j][i] = new BigInteger("0");

                if(j == 0)
                    lattice[j][i] = BigInteger.ONE;
                else if(i == 0)
                    lattice[j][i] = lattice[j][i].add(lattice[j - 1][i]);
                else
                    lattice[j][i] = lattice[j][i].add(lattice[j - 1][i].add(lattice[j][i - 1]));
            }
        }
        System.out.println("Answer: " + lattice[latticeHeight][latticeWidth]);
    }
}
