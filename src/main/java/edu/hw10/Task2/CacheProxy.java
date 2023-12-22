package edu.hw10.Task2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private Object object1;

    private static Map<String, Object[]> cache;

    CacheProxy(Object object) {
        this.object1 = object;
        cache = new HashMap<>();
    }

    public static Map<String, Object[]> getCache() {
        return cache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            for (var key : cache.keySet()) {
                if (key.equals(Arrays.toString(args))) {
                    return cache.get(key)[0];
                }
            }
            var result = method.invoke(object1, args);
            cache.putIfAbsent(Arrays.toString(args), new Object[] {result, method.getName()});
            if (method.getAnnotation(Cache.class).persist()) {
                File tempFile =
                    File.createTempFile("temp_", ".txt", Path.of("src/main/java/edu/hw10/Task2")
                        .toFile());
                tempFile.deleteOnExit();
                try (
                    BufferedWriter bufferedWriter =
                        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)))) {
                    for (var key : cache.keySet()) {
                        bufferedWriter.write(key + " - " + Arrays.toString(cache.get(key)) + "\n");
                    }
                }
            }
            return result;
        } else {
            return method.invoke(object1, args);
        }
    }

    public static Object create(Object object, Class<?> className) {
        ClassLoader objectClassLoader = className.getClassLoader();
        Class[] interfaces = className.getInterfaces();
        Object proxyObject =
            Proxy.newProxyInstance(objectClassLoader, interfaces, new CacheProxy(object));
        return proxyObject;
    }
}
