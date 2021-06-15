package com.unufolio.fondo.modules.mq.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
public class KafkaConsumer {

    private final Consumer<String, String> consumer;

    public KafkaConsumer(Consumer<String, String> consumer) {
        this.consumer = consumer;
    }

    public void listen(String topic) {
//        consumer.subscribe();
    }
}
