package edu.hw7.Task2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static BigInteger factorial(int number) {
        if (number < 0) {
            return new BigInteger("-1");
        }
        List<BigInteger> intList = new ArrayList<>();
        for (int i = 1; i <= number; ++i) {
            intList.add(new BigInteger(String.valueOf(i)));
        }
        return intList.parallelStream().reduce(new BigInteger("1"), BigInteger::multiply);
    }
}
