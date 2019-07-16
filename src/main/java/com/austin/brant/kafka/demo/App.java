package com.austin.brant.kafka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.austin.brant.kafka.demo.provider.KafkaProducer;

/**
 * @author austin-brant
 * @since 2019/7/15 18:08
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        KafkaProducer producer = context.getBean(KafkaProducer.class);

        String topic = context.getEnvironment().getProperty("topic.name");

        Thread.sleep(10000);
        for (int i = 0; i < 10; i++) {
            producer.send(topic, "" + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

