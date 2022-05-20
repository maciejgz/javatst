package pl.mg.javatst.codewars;

import java.math.BigInteger;

public class SumFct {

    public static void main(String[] args) {
        System.out.println(perimeter(BigInteger.valueOf(5)));
    }


    public static BigInteger perimeter(BigInteger n) {
        BigInteger previousNumber = BigInteger.valueOf(0);
        BigInteger nextNumber = BigInteger.valueOf(1);
        BigInteger totalSum = BigInteger.valueOf(1);
        BigInteger sum;
        for (int i = 1; i <= n.intValue(); ++i) {
            sum = previousNumber.add(nextNumber);
            previousNumber = nextNumber;
            nextNumber = sum;
            totalSum = totalSum.add(sum);
        }
        return totalSum.multiply(BigInteger.valueOf(4));
    }

}
