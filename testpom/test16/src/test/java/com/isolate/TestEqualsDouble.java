package com.isolate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.print.attribute.IntegerSyntax;
import java.lang.reflect.Field;
import java.time.MonthDay;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.runner.Request.method;

/**
 * @description: 如何让 a == 1 && a == 2 && a == 3 返回true
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/9/7 17:12
 */
@PrepareForTest(Integer.class)
@RunWith(PowerMockRunner.class)
public class TestEqualsDouble {

    @Before
    public  void before(){
        // value is just a place to store an incrementing integer
        AtomicInteger value = new AtomicInteger(1);
//        replace(method(Integer.class,"intValue"))
//                .with((proxy,method,args)-> value.getAndIncrement());
    }

    @Test
    public void test(){
        Integer a= 1;
        if (a == 1 && a == 2 && a == 3){
            System.out.println("success");
        }else {
            Assert.fail("(a == 1 && a == 2 && a == 3) != true, a = " + a.intValue());
        }
    }

    /**
     *  利用integer，使1,2,3数值在inter中是一致的
     * @param args
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = cache.getDeclaredField("cache");
        c.setAccessible(true);
        Integer[] array = (Integer[]) c.get(cache);
// array[129] is 1
        array[130] = array[129];
// Set 2 to be 1
        array[131] = array[129];
// Set 3 to be 1
        Integer a = 1;
        if(a == (Integer)1 && a == (Integer)2 && a == (Integer)3){
            System.out.println("Success");
        }
    }
}
