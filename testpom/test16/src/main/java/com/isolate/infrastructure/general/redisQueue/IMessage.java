package com.isolate.infrastructure.general.redisQueue;

import com.mysql.cj.protocol.Message;

import java.util.concurrent.Executor;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2022/12/15 16:33
 */
public interface IMessage {


    void put(Message message);

    void putHeader(Message message);

    Executor getExecutor();

    Long getMessageSize(String topic);
}

