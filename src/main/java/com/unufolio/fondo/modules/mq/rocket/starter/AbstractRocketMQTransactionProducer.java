package com.unufolio.fondo.modules.mq.rocket.starter;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
public abstract class AbstractRocketMQTransactionProducer<T> {

    private final RocketMQTemplate rocketMQTemplate;

    public AbstractRocketMQTransactionProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    private SendResult sendMessageInTransaction(String destination, Message<T> message, Object arg) {
        return rocketMQTemplate.sendMessageInTransaction(destination, message, arg);
    }
}
