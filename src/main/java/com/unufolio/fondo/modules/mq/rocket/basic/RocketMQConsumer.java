package com.unufolio.fondo.modules.mq.rocket.basic;

import org.apache.rocketmq.common.message.MessageExt;

/**
 /**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
public class RocketMQConsumer extends AbstractRocketMQConsumer {

    public RocketMQConsumer(String tag, String topic, String groupName, String consumerGroup) {
        super(tag, topic, groupName, consumerGroup);
    }

    // @EventListener(ApplicationReadyEvent.class)
    public void execute() {
        super.listen();
    }

    @Override
    protected void consume(MessageExt msg) {
        System.out.println(msg);
    }
}
