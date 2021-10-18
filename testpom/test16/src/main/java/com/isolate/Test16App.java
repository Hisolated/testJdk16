package com.isolate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/6/19 10:15
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(value = "org.artofsolving.*")
public class Test16App {
    public static void main(String[] args) {
        SpringApplication.run(Test16App.class,args);
    }
}
