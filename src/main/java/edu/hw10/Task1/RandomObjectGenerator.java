package edu.hw10.Task1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class RandomObjectGenerator {
    public RandomObjectGenerator() {
    }

    public Object nextObject(Class<?> className)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] constructors = className.getConstructors();
        Constructor<?> constructor = constructors[ThreadLocalRandom.current().nextInt(constructors.length)];
        Parameter[] parameters = constructor.getParameters();
        Object[] randomParameters = getRandomParameters(parameters);
        return constructor.newInstance(randomParameters);
    }

    public Object nextObject(Class<?> className, String methodName)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method method = null;
        Method[] methods = className.getMethods();
        for (var md : methods) {
            if (md.getName().equals(methodName)) {
                method = md;
                break;
            }
        }
        if (method != null) {
            Parameter[] parameters = method.getParameters();
            Object[] randomParameters = getRandomParameters(parameters);
            Object object = className.getConstructor().newInstance();
            return method.invoke(object, randomParameters);
        }
        return null;
    }

    @SuppressWarnings("CyclomaticComplexity")
    private Object[] getRandomParameters(Parameter[] parameters) {
        Object[] randomParameters = new Object[parameters.length];
        for (int i = 0; i < parameters.length; ++i) {
            switch (parameters[i].getType().getSimpleName()) {
                case "int", "Integer" -> {
                    randomParameters[i] =
                        ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsInt(parameters, randomParameters, i);
                    }
                }
                case "char", "Character" -> {
                    randomParameters[i] =
                        (char) ThreadLocalRandom.current().nextInt(Character.MIN_VALUE, Character.MAX_VALUE);
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsCh(parameters, randomParameters, i);
                    }
                }
                case "boolean", "Boolean" -> {
                    randomParameters[i] =
                        ThreadLocalRandom.current().nextBoolean();
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsBool(parameters, randomParameters, i);
                    }
                }
                case "byte", "Byte" -> {
                    randomParameters[i] =
                        (byte) ThreadLocalRandom.current().nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE);
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsByte(parameters, randomParameters, i);
                    }
                }
                case "short", "Short" -> {
                    randomParameters[i] =
                        (short) ThreadLocalRandom.current().nextInt(Short.MIN_VALUE, Short.MAX_VALUE);
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsShort(parameters, randomParameters, i);
                    }
                }
                case "long", "Long" -> {
                    randomParameters[i] =
                        ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsLong(parameters, randomParameters, i);
                    }
                }
                case "float", "Float" -> {
                    randomParameters[i] =
                        ThreadLocalRandom.current().nextFloat(Float.MIN_VALUE, Float.MAX_VALUE);
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsFloat(parameters, randomParameters, i);
                    }
                }
                case "double", "Double" -> {
                    randomParameters[i] =
                        ThreadLocalRandom.current().nextDouble(Double.MIN_VALUE, Double.MAX_VALUE);
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsDouble(parameters, randomParameters, i);
                    }
                }
                case "String" -> {
                    randomParameters[i] =
                        parameters[ThreadLocalRandom.current().nextInt(parameters.length)].getType().getSimpleName();
                    if (parameters[i].getAnnotations().length > 0) {
                        workWithAnnotationsStr(parameters, randomParameters, i);
                    }
                }
                default -> {
                }
            }
        }
        return randomParameters;
    }

    private void workWithAnnotationsInt(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = 0;
            }
        }
        if (parameters[index].isAnnotationPresent(Min.class)) {
            int annotationValue = (int) parameters[index].getAnnotation(Min.class).value();
            if ((int) randomParameters[index] < annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
        if (parameters[index].isAnnotationPresent(Max.class)) {
            int annotationValue = (int) parameters[index].getAnnotation(Max.class).value();
            if ((int) randomParameters[index] > annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
    }

    private void workWithAnnotationsCh(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = 0;
            }
        }
        if (parameters[index].isAnnotationPresent(Min.class)) {
            char annotationValue = (char) parameters[index].getAnnotation(Min.class).value();
            if ((char) randomParameters[index] < annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
        if (parameters[index].isAnnotationPresent(Max.class)) {
            char annotationValue = (char) parameters[index].getAnnotation(Max.class).value();
            if ((char) randomParameters[index] > annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
    }

    private void workWithAnnotationsBool(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = 0;
            }
        }
    }

    private void workWithAnnotationsByte(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = 0;
            }
        }
        if (parameters[index].isAnnotationPresent(Min.class)) {
            byte annotationValue = (byte) parameters[index].getAnnotation(Min.class).value();
            if ((byte) randomParameters[index] < annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
        if (parameters[index].isAnnotationPresent(Max.class)) {
            byte annotationValue = (byte) parameters[index].getAnnotation(Max.class).value();
            if ((byte) randomParameters[index] > annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
    }

    private void workWithAnnotationsShort(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = 0;
            }
        }
        if (parameters[index].isAnnotationPresent(Min.class)) {
            short annotationValue = (short) parameters[index].getAnnotation(Min.class).value();
            if ((short) randomParameters[index] < annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
        if (parameters[index].isAnnotationPresent(Max.class)) {
            short annotationValue = (short) parameters[index].getAnnotation(Max.class).value();
            if ((short) randomParameters[index] > annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
    }

    private void workWithAnnotationsLong(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = 0;
            }
        }
        if (parameters[index].isAnnotationPresent(Min.class)) {
            long annotationValue = parameters[index].getAnnotation(Min.class).value();
            if ((long) randomParameters[index] < annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
        if (parameters[index].isAnnotationPresent(Max.class)) {
            long annotationValue = parameters[index].getAnnotation(Max.class).value();
            if ((long) randomParameters[index] > annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
    }

    private void workWithAnnotationsFloat(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = 0;
            }
        }
        if (parameters[index].isAnnotationPresent(Min.class)) {
            float annotationValue = (float) parameters[index].getAnnotation(Min.class).value();
            if ((float) randomParameters[index] < annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
        if (parameters[index].isAnnotationPresent(Max.class)) {
            float annotationValue = (float) parameters[index].getAnnotation(Max.class).value();
            if ((float) randomParameters[index] > annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
    }

    private void workWithAnnotationsDouble(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = 0;
            }
        }
        if (parameters[index].isAnnotationPresent(Min.class)) {
            double annotationValue = (double) parameters[index].getAnnotation(Min.class).value();
            if ((double) randomParameters[index] < annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
        if (parameters[index].isAnnotationPresent(Max.class)) {
            double annotationValue = (double) parameters[index].getAnnotation(Max.class).value();
            if ((double) randomParameters[index] > annotationValue) {
                randomParameters[index] = annotationValue;
            }
        }
    }

    private void workWithAnnotationsStr(Parameter[] parameters, Object[] randomParameters, int index) {
        if (parameters[index].isAnnotationPresent(NotNull.class)) {
            if (randomParameters[index] == null) {
                randomParameters[index] = "";
            }
        }
    }
}
