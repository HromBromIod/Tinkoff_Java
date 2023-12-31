package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task4 {
    private Task4() {
    }

    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        LOGGER.info(callingInfo());
    }

    public static CallingInfo callingInfo() {
        String className;
        String methodName;
        StackTraceElement[] ste = new Throwable().getStackTrace();
        className = ste[1].getClassName();
        methodName = ste[1].getMethodName();
        return new CallingInfo(className, methodName);
    }

    public record CallingInfo(String className, String methodName) {
    }
}
