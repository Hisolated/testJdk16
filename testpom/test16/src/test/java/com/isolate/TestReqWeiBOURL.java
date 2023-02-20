package com.isolate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2023/2/17 10:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestReqWeiBOURL {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testReqWeiBoHot() {
        // todo: 需要携带一个key来访问，否则访问不到数据
        String forObject = restTemplate.getForObject("https://weibo.com/ajax/statuses/news", String.class);
        System.out.println(forObject);
    }

}
