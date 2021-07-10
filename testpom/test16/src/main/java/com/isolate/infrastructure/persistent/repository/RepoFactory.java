package com.isolate.infrastructure.persistent.repository;

import com.isolate.infrastructure.general.toolkit.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;

/**
 * @description: 简化版的仓储工厂--用来统一获取仓储的实现Bean
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/6/19 10:42
 */
@Slf4j
public class RepoFactory {

    /**
     * 根据仓储接口类型获取对应实现且默认取值第一个
     *
     * @param tClass 具体目标类型
     * @param <T>    仓储接口类型
     * @return 如果不是指定实现，默认获得第一个实现Bean
     */
    public static <T> T get(Class<? extends T> tClass) {

        Map<String, ? extends T> map = ApplicationContextUtil.getApplicationContext().getBeansOfType(tClass);
        Collection<? extends T> collection = map.values();
        if (collection.isEmpty()) {
            log.info("未找到仓储接口或其指定的实现:" + tClass.getSimpleName() );
        }
        return collection.stream().findFirst().get();
    }
}