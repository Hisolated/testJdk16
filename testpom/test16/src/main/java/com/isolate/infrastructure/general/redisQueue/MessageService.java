//package com.isolate.infrastructure.general.redisQueue;
//
//import com.alibaba.fastjson.JSON;
//import com.mysql.cj.protocol.Message;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.concurrent.Executor;
//
///**
// * @description: some desc
// * @author: isolate
// * @email: 15071340963@163.com
// * @date: 2022/12/15 16:41
// */
//@Service
//@Slf4j
//public class MessageService implements IMessage {
//    @Resource
//    private IQuenuServcie iQuenuServcie;
//
//    @Resource
//    @Qualifier(value = "myTaskExecutor")
//    private Executor executor;
//
//    @Override
//    public void put(Message message) {
//        String json = JSON.toJSONString(message);
//        Long res = iQuenuServcie.lpush(message.getTopic(), json);
//        log.info("push msg :{} res:{}", json, res);
//    }
//
//    @Override
//    public void putHeader(Message message) {
//        String json = JSON.toJSONString(message);
//        iQuenuServcie.rpush(message.getTopic(), json);
//        log.info("push msg :{}", json);
//    }
//
//    @Override
//    public Executor getExecutor() {
//        return executor;
//    }
//
//    @Override
//    public Long getMessageSize(String topic) {
//        return iQuenuServcie.llen(topic);
//    }
//}
