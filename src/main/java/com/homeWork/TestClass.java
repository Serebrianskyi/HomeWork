package com.homeWork;


import com.homeWork.annotation.BeforeMethod;
import com.homeWork.annotation.AfterMethod;
import com.homeWork.annotation.Ignore;
import com.homeWork.annotation.Test;

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
    @Test
    public void shouldBeIgnored() {
        System.out.println("test1 should be ignored");
        }

    @Test(expected = ArithmeticException.class)
    public void shouldTrowExeption(){
        throw new ArithmeticException();
    }
}
