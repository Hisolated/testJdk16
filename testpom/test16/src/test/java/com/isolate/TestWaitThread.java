package com.isolate;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2023/3/13 10:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWaitThread {

    public static final ThreadPoolExecutor themeThreadPool = new ThreadPoolExecutor(
            12,
            100,
            8L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(5000),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void testWait() throws InterruptedException {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 控制所有线程的完成情况
        CountDownLatch countDownLatch = new CountDownLatch(list.size());
        long startTime = System.currentTimeMillis();
        System.out.println("线程对象：" + JSON.toJSONString(countDownLatch) + ", 当前时间：" + startTime);

        for (Integer i : list) {

            themeThreadPool.execute(() -> {

                System.out.println("当前线程名称：" + Thread.currentThread().getName());

                if ( i == 2 || i == 4) {
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                countDownLatch.countDown();
                System.out.println("还剩" + JSON.toJSONString(countDownLatch) + "线程没有执行");
            });
        }

        // 等待所有线程完成
        boolean awaitResult = countDownLatch.await(5L, TimeUnit.SECONDS);

        System.out.println("执行结果 " + awaitResult + "执行耗时：" + (System.currentTimeMillis() - startTime));
    }
}
