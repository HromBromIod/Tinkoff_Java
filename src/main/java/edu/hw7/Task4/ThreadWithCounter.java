package edu.hw7.Task4;

public class ThreadWithCounter extends Thread {
    private int counter = 0;

    public ThreadWithCounter(Runnable oneThreadPILambda) {
        super(oneThreadPILambda);
        incrementCounter();
    }

    private void incrementCounter() {
        counter += 1;
    }

    public int getCounter() {
        return counter;
    }
}
