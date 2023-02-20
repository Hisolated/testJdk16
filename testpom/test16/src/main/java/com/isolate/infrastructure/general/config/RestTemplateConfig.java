package com.isolate.infrastructure.general.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description: restful请求配置类
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/6/10 10:02
 */
@Configuration
public class RestTemplateConfig {

    public static final int CONNECT_TIMEOUT = 10 * 1000;

    public static final int READ_TIMEOUT = 30 * 1000;

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);

        // 支持中文编码
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        // 设置读取超时时间 单位为ms
        factory.setReadTimeout(READ_TIMEOUT);

        // 设置连接超时时间 单位为ms
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        return factory;
    }
}
