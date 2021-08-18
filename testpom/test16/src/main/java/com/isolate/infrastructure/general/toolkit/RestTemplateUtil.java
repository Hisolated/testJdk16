package com.isolate.infrastructure.general.toolkit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/6/16 9:13
 */
@Slf4j
public class RestTemplateUtil {

    /**
     * 封装的get请求，暂时只支持map传参，并且value只支持基本类型和String
     *
     * @param url
     * @param object
     * @return
     */
    public static String getForObject(String url, Object object) {
        StringBuffer stringBuffer = new StringBuffer(url);
        if (object instanceof Map) {
            Iterator iterator = ((Map) object).entrySet().iterator();
            if (iterator.hasNext()) {
                stringBuffer.append("?");
                Object element;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    Map.Entry<String, Object> entry = (Map.Entry) element;
                    //过滤value为null，value为null时进行拼接字符串会变成 "null"字符串
                    if (entry.getValue() != null) {
                        stringBuffer.append(element).append("&");
                    }
                    url = stringBuffer.substring(0, stringBuffer.length() - 1);
                }
            }
        } else {
            throw new RuntimeException("url请求:" + url + "请求参数有误不是map类型");
        }
        log.info("url请求:" + url);
        return new RestTemplate().getForObject(url, String.class);
    }
}
