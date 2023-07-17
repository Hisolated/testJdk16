package com.isolate;

import cn.hutool.core.convert.Convert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
     * 思路：
     * 将运算表达式以一个string传入aop，在aop中获取该表达式，并进行一个运算
     * 利用ScriptEngineManager  ScriptEngine 等脚本执行类执行
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

    /**
     * 爬楼梯
     */
    @Test
    public void testClimbingStairs() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入楼梯的阶数：");
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println("爬楼梯的方法数为：" + dp[n]);
    }

//    public static void main(String[] args) {
//        String s = "2021-01-26T01:50:01.000+0000";
//        LocalDateTime parse = LocalDateTime.parse(s.replace("T"," ").substring(0, 19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(Convert.toLong("1680162368580") / 1000, 0, ZoneOffset.ofHours(8));
//        System.out.println(parse + ":" + localDateTime);
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 输入第一个时间
        System.out.print("请输入开始日期（yyyy-MM-dd）：");
        LocalDate startDate = LocalDate.parse(scanner.nextLine(), formatter);

        // 输入第二个时间
        System.out.print("请输入结束日期（yyyy-MM-dd）：");
        LocalDate endDate = LocalDate.parse(scanner.nextLine(), formatter);

        // 输出两个时间之间的所有日期
        while (!startDate.isAfter(endDate)) {
            System.out.println(startDate.format(formatter));
            startDate = startDate.plusDays(1);
        }
    }

}
