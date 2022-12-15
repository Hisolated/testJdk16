package com.isolate.infrastructure.general.redisQueue;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2022/12/15 16:32
 */

import org.springframework.data.redis.connection.Pool;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: 赵超
 * @Copyright: Copyright (c) 浙江日报
 * @Date: 2019-06-22 16:11
 * @Version: 1.0
 */

@Service
public class QueueServiceImpl implements IQuenuServcie {

    @Resource
    private Pool<Jedis> jedisPool;


    @Override
    public Long lpush(String messageKey, String message) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lpush(messageKey, message);
        }
    }

    @Override
    public String lpop(String messageKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.lpop(messageKey);
        }
    }

    @Override
    public Long llen(String messageKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.llen(messageKey);
        }
    }

    @Override
    public Long rpush(String messageKey, String message) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.rpush(messageKey, message);
        }
    }

    @Override
    public String rpop(String messageKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.rpop(messageKey);
        }
    }

    @Override
    public Long rlen(String messageKey) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.llen(messageKey);
        }
    }


}

