package edu.hw10.Task2Test.Fibonacci;

public class FibCalculatorImp implements FibCalculator {
    @Override
    public long fib(int number) {
        if (number <= 2) {
            return 1;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
