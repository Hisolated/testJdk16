package com.isolate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/8/12 9:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAnnotionFunction {

    /**
     * 测试注解上直接获取方法参数，进行一个运算
     *  思路：
     *      将运算表达式以一个string传入aop，在aop中获取该表达式，并进行一个运算
     *      利用ScriptEngineManager  ScriptEngine 等脚本执行类执行
     */
    @Test
    public void testSamething() throws ScriptException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入表达式");
        String str = sc.next();
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine daqi = sem.getEngineByName("nashorn");
        Object eval = daqi.eval(str);
        System.out.println(eval.toString());

    }

}
