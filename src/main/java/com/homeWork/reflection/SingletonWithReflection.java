package com.homeWork.reflection;

import java.lang.reflect.Constructor;

class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}

public class SingletonWithReflection {
    public static void main(String[] args) {
        Singleton singletonInstance = Singleton.getInstance();
        Singleton reflectionInstance = null;

        try {
            Constructor[] constructors = Singleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                reflectionInstance = (Singleton) constructor.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Singleton Instance hashCode: " + singletonInstance);
        System.out.println("Reflection Instance hashCode: " + reflectionInstance);

        if(singletonInstance != reflectionInstance){
            System.out.println("Different instance");
        }
    }
}
