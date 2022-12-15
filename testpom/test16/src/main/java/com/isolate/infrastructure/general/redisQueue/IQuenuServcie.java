package com.isolate.infrastructure.general.redisQueue;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2022/12/15 15:15
 */
public interface IQuenuServcie {

    /**
     *
     * @param messageKey
     * @param message
     * @return
     */
    Long lpush(String messageKey, String message);

    String lpop(String messageKey);

    Long llen(String messageKey);

    Long rpush(String messageKey, String message);

    String rpop(String messageKey);

    Long rlen(String messageKey);

}

