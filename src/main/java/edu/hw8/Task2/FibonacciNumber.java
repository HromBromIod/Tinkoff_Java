package edu.hw8.Task2;

public class FibonacciNumber implements Runnable {
    private final int num;

    public FibonacciNumber(int num) {
        this.num = num;
    }

    public long findFibonacciNumber(int num) {
        if (num < 1) {
            return 0;
        }
        if (num <= 2) {
            return 1;
        }
        return findFibonacciNumber(num - 1) + findFibonacciNumber(num - 2);
    }

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] looking for the " + num + " Fibonacci number");
        System.out.println("[" + Thread.currentThread().getName() + "] result = " + findFibonacciNumber(num));
    }
}
