package edu.hubu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * created by Sugar  2020/12/6 21:34
 */
@EnableBinding(Sink.class)
@Component
@Slf4j
public class StreamController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
      log.info("我是消费者2号，本次接收到的消息为：" + message.getPayload() + "，端口号为--->" + serverPort);
    }
}
