package com.austin.brant.kafka.demo.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.austin.brant.kafka.demo.model.Message;

import lombok.extern.slf4j.Slf4j;

/**
 * 生产者
 *
 * @author austin-brant
 * @since 2019/7/15 19:39
 */
@Component
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic,
                Message.builder()
                        .id(System.currentTimeMillis())
                        .msg(message)
                        .sendTime(new Date()).build().toString());
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.error("send message [{}] to topic [{}] failed, ", message, topic);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                log.info("send message [{}] to topic [{}] success, ", message, topic);
            }
        });
        log.info("send message end");
    }

    public void batchSend(String topic, List<String> message) {
        message.forEach(it -> kafkaTemplate.send(topic, it));
    }
}
