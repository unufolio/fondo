package com.unufolio.fondo.modules.mq.rocket.starter;

import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
// @Component
// @RocketMQMessageListener(topic = "topic", consumerGroup = "consumerGroup")
public class ConsumerObject implements RocketMQListener<Object> {

    private final static Logger logger = LoggerFactory.getLogger(ConsumerObject.class);

    @Override
    public void onMessage(Object object) {
        logger.info("received object: " + object);
    }
}
