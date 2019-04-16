package com.homeWork.reflection;



import com.homeWork.reflection.annotation.AfterMethod;
import com.homeWork.reflection.annotation.BeforeMethod;
import com.homeWork.reflection.annotation.Ignore;
import com.homeWork.reflection.annotation.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class EJUnitRunner {
    public static void main(String[] args) throws Exception {
        Class<TestClass> testClazz = TestClass.class;

        Constructor<TestClass> constructor = testClazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        //instance
        TestClass instance = constructor.newInstance();

        Method[] methods = testClazz.getDeclaredMethods();

        List<Method> beforeMethods = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(BeforeMethod.class))
                .collect(toList());

        List<Method> testMethods = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(Test.class))
                .filter(method -> !method.isAnnotationPresent(Ignore.class))
                .collect(toList());

        List<Method> afterMethod = Arrays.stream(methods)
                .filter(method -> method.isAnnotationPresent(AfterMethod.class))
                .collect(toList());

        //run

        for (Method method: testMethods) {
            runSingleMethod(instance, method);
        }

    }

    private static void runSingleMethod(Object instance, Method method) {
        String methodName = method.getName();
        Class<? extends Throwable> expected = method.getDeclaredAnnotation(Test.class).expected();
        try {
            method.invoke(instance);
        } catch (Exception e) {
            if (expected.equals(e.getCause().getClass())) {
                System.out.println("Method " + methodName + " throws exception");
            }

        }
    }
}
