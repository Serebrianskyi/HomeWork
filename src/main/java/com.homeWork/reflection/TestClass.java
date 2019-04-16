package com.homeWork.reflection;


import com.homeWork.reflection.annotation.AfterMethod;
import com.homeWork.reflection.annotation.BeforeMethod;
import com.homeWork.reflection.annotation.Ignore;
import com.homeWork.reflection.annotation.Test;

public class TestClass {

    @BeforeMethod
    public void init() {
        System.out.println("before method");
    }

    @AfterMethod
    public void clean() {
        System.out.println("after method");
    }

    @Ignore
    @Test(expected = ArithmeticException.class)
    public void shouldBeIgnored() {
        System.out.println("test1 should be ignored");
        }

    @Test(expected = ArithmeticException.class)
    public void shouldTrowExeption(){
        throw new ArithmeticException();
    }
}
