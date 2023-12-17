package edu.hw10.Task2Test.Fibonacci;

import edu.hw10.Task2.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    public long fib(int number);
}
