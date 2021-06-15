package com.unufolio.fondo.modules.mq.rocket;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
public class RocketProducer {

    private final static Logger logger = LoggerFactory.getLogger(RocketProducer.class);

    private DefaultMQProducer producer = new DefaultMQProducer();

    public RocketProducer() {
    }

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }

    /**
     * normal message
     *
     * @param topic
     * @param tag
     * @param keys
     * @param content
     * @return
     */
    public SendResult sendNormalMessage(String topic,
                                        String tag,
                                        String keys,
                                        String content) {
        Message msg = new Message(topic, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        try {
            send = producer.send(msg);
            logger.info("topic : {}, tag : {}, keys : {}, content : {}", topic, tag, keys, content);
        } catch (MQClientException
                | RemotingException
                | MQBrokerException
                | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return send;
    }

    /**
     * sequence message
     *
     * @param topic
     * @param tag
     * @param keys
     * @param content
     * @return
     */
    public SendResult sendSequenceMessage(String topic,
                                          String tag,
                                          String keys,
                                          String content) {
        Message msg = new Message(topic, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        try {
            send = producer.send(msg, (mqs, msg1, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, keys);
            logger.info("topic : {}, tag : {}, keys : {}, content : {}", topic, tag, keys, content);
        } catch (MQClientException
                | RemotingException
                | MQBrokerException
                | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return send;
    }

    /**
     * 1  2  3   4   5  6  7  8  9  10 11 12 13 14  15  16  17 18
     * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     *
     * @param topic
     * @param tag
     * @param keys
     * @param content
     */
    public SendResult sendDelayMessage(String topic,
                                       String tag,
                                       String keys,
                                       String content,
                                       int timeLevel) {
        Message msg = new Message(topic, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        msg.setDelayTimeLevel(timeLevel);
        try {
            send = producer.send(msg);
            logger.info("topic : {}, tag : {}, keys : {}, content : {}", topic, tag, keys, content);
        } catch (MQClientException
                | RemotingException
                | MQBrokerException
                | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return send;
    }

    /**
     * timing message
     * community rocket message queue not support
     * @param topic
     * @param tag
     * @param keys
     * @param content
     * @param deliverTime
     * @return
     */
//     public SendResult sendTimingMessage(String topic,
//                                        String tag,
//                                        String keys,
//                                        String content,
//                                        long deliverTime) {
//         Message msg = new Message(topic, tag, keys, content.getBytes(StandardCharsets.UTF_8));
//         SendResult send = null;
//         msg.setStartDeliverTime(timeStamp);
//         try {
//             send = producer.send(msg);
//             logger.info("topic : {}, tag : {}, keys : {}, content : {}", topic, tag, keys, content);
//         } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
//             e.printStackTrace();
//         }
//         return send;
//     }
}
