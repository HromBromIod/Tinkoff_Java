package edu.hw11.Task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static net.bytebuddy.matcher.ElementMatchers.returns;

public class Task2 {
    private Task2() {
    }

    public static Object changeMethod(Class<?> fromType, Method fromMethod, Class<?> toType)
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return new ByteBuddy()
            .subclass(fromType)
            .method(named(fromMethod.getName())
                .and(isDeclaredBy(fromType)
                    .and(returns(fromMethod.getReturnType()))))
            .intercept(MethodDelegation.to(toType))
            .make()
            .load(Task2.class.getClassLoader())
            .getLoaded()
            .getDeclaredConstructor()
            .newInstance();
    }
}
