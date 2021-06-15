package com.unufolio.fondo.modules.mq.rocket.basic;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
public abstract class AbstractRocketMQOrderlyConsumer {

    private final static Logger logger = LoggerFactory.getLogger(AbstractRocketMQOrderlyConsumer.class);

    private final String tag;
    private final String topic;
    private final String consumerGroup;
    private final String namesrvAddr;

    public AbstractRocketMQOrderlyConsumer(String tag, String topic, String groupName, String consumerGroup) {
        this.tag = tag;
        this.topic = topic;
        this.consumerGroup = groupName;
        this.namesrvAddr = consumerGroup;
    }

    protected void listen() {
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        // Specify name server addresses.
        consumer.setNamesrvAddr(namesrvAddr);
        // Subscribe one more more topics to consume.
        try {
            consumer.subscribe(topic, tag);
        } catch (MQClientException e) {
            logger.error(e.getErrorMessage());
        }
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(this::consumeMessage);
        // Launch the consumer instance.
        try {
            consumer.start();
        } catch (MQClientException e) {
            logger.error(e.getErrorMessage());
        }
        logger.info("Consumer Started.");
    }

    private ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                ConsumeOrderlyContext context) {
        for (MessageExt msg : msgs) {
            consume(msg);
        }
        return ConsumeOrderlyStatus.SUCCESS;
    }

    protected void consume(MessageExt msg) {
    }
}
