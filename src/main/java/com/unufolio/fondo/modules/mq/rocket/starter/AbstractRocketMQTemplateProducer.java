package com.unufolio.fondo.modules.mq.rocket.starter;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.core.MessagePostProcessor;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * https://github.com/apache/rocketmq-spring
 *
 * @author Unufolio unufolio@gmail.com
 * @date 2021/03/28
 */
public abstract class AbstractRocketMQTemplateProducer<T, R> {

    private final RocketMQTemplate rocketMQTemplate;

    public AbstractRocketMQTemplateProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    private void sendMessage(Message<T> message) {
        rocketMQTemplate.send(message);
    }

    private void sendMessage(String topic, Message<T> message) {
        rocketMQTemplate.send(topic, message);
    }

    private SendResult syncSend(String destination, Object payload) {
        return rocketMQTemplate.syncSend(destination, payload);
    }

    public <M extends Message<T>> SendResult syncSendCollection(String destination, Collection<M> messages) {
        return rocketMQTemplate.syncSend(destination, messages);
    }

    public <M extends Message<T>> SendResult syncSendCollectionTimeout(String destination, Collection<M> messages,
                                                                       long timeout) {
        return rocketMQTemplate.syncSend(destination, messages, timeout);
    }

    private SendResult syncSendTimeout(String destination, Object payload, long timeout) {
        return rocketMQTemplate.syncSend(destination, payload, timeout);
    }

    private SendResult syncSendMessage(String destination, Message<T> message) {
        return rocketMQTemplate.syncSend(destination, message);
    }

    private SendResult syncSendMessageTimeout(String destination, Message<T> message, long timeout) {
        return rocketMQTemplate.syncSend(destination, message, timeout);
    }

    private SendResult syncSendMessageDelay(String destination, Message<T> message, long timeout, int delayLevel) {
        return rocketMQTemplate.syncSend(destination, message, timeout, delayLevel);
    }

    private SendResult syncSendOrderly(String destination, Object payload, String hashKey) {
        return rocketMQTemplate.syncSendOrderly(destination, payload, hashKey);
    }

    private SendResult syncSendTimeoutOrderly(String destination, Object payload, String hashKey, long timeout) {
        return rocketMQTemplate.syncSendOrderly(destination, payload, hashKey);
    }

    private SendResult syncSendMessageOrderly(String destination, Message<T> message, String hashKey) {
        return rocketMQTemplate.syncSendOrderly(destination, message, hashKey);
    }

    private SendResult syncSendMessageTimeoutOrderly(String destination, Message<T> message, String hashKey, long timeout) {
        return rocketMQTemplate.syncSendOrderly(destination, message, hashKey, timeout);
    }

    private void sendOneWay(String destination, Object payload) {
        rocketMQTemplate.sendOneWay(destination, payload);
    }

    private void sendOneWayMessage(String destination, Message<T> message) {
        rocketMQTemplate.sendOneWay(destination, message);
    }

    private void sendOneWayOrderly(String destination, Object payload, String hashkey) {
        rocketMQTemplate.sendOneWayOrderly(destination, payload, hashkey);
    }

    private void sendOneWayMessageOrderly(String destination, Message<T> message, String hashkey) {
        rocketMQTemplate.sendOneWayOrderly(destination, message, hashkey);
    }

    private void convertAndSend(Object payload) {
        rocketMQTemplate.convertAndSend(payload);
    }

    private void convertAndSend(String destination, Object payload) {
        rocketMQTemplate.convertAndSend(destination, payload);
    }

    private void convertAndSend(String destination, Object payload, Map<String, Object> headers) {
        rocketMQTemplate.convertAndSend(destination, payload, headers);
    }

    public void convertAndSend(Object payload, @Nullable MessagePostProcessor postProcessor) {
        rocketMQTemplate.convertAndSend(payload, postProcessor);
    }

    public void convertAndSend(String destination, Object payload, @Nullable MessagePostProcessor postProcessor) throws MessagingException {
        rocketMQTemplate.convertAndSend(destination, payload, postProcessor);
    }

    public void convertAndSend(String destination, Object payload, @Nullable Map<String, Object> headers, @Nullable MessagePostProcessor postProcessor) throws MessagingException {
        rocketMQTemplate.convertAndSend(destination, payload, headers, postProcessor);
    }

    public R sendMessageAndReceive(String destination, Message<T> message, Type type) {
        return rocketMQTemplate.sendAndReceive(destination, message, type);
    }

    public R sendAndReceive(String destination, Object payload, Type type) {
        return rocketMQTemplate.sendAndReceive(destination, payload, type);
    }

    public R sendMessageAndReceiveTimeout(String destination, Message<T> message, Type type, long timeout) {
        return rocketMQTemplate.sendAndReceive(destination, message, type, timeout);
    }

    public R sendAndReceiveTimeout(String destination, Object payload, Type type, long timeout) {
        return rocketMQTemplate.sendAndReceive(destination, payload, type, timeout);
    }

    public R sendMessageAndReceiveTimeoutDelay(String destination, Message<T> message, Type type, long timeout,
                                               int delayLevel) {
        return rocketMQTemplate.sendAndReceive(destination, message, type, timeout, delayLevel);
    }

    public R sendAndReceiveTimeoutDelay(String destination, Object payload, Type type, long timeout, int delayLevel) {
        return rocketMQTemplate.sendAndReceive(destination, payload, type, timeout, delayLevel);
    }

    public R sendMessageAndReceiveOrderly(String destination, Message<T> message, Type type,
                                          String hashKey) {
        return rocketMQTemplate.sendAndReceive(destination, message, type, hashKey);
    }

    public R sendAndReceiveOrderly(String destination, Object payload, Type type,
                                   String hashKey) {
        return rocketMQTemplate.sendAndReceive(destination, payload, type, hashKey);
    }

    public R sendMessageAndReceiveTimeoutOrderly(String destination, Message<T> message, Type type,
                                                 String hashKey, long timeout) {
        return rocketMQTemplate.sendAndReceive(destination, message, type, hashKey, timeout);
    }

    public R sendAndReceiveTimeoutOrderly(String destination, Object payload, Type type,
                                          String hashKey, long timeout) {
        return rocketMQTemplate.sendAndReceive(destination, payload, type, hashKey, timeout);
    }

    public R sendMessageAndReceiveTimeoutOrderly(String destination, Message<T> message, Type type,
                                                 String hashKey, long timeout, int delayLevel) {
        return rocketMQTemplate.sendAndReceive(destination, message, type, hashKey, timeout, delayLevel);
    }

    public R sendAndReceiveTimeoutDelayOrderly(String destination, Object payload, Type type,
                                               String hashKey, long timeout, int delayLevel) {
        return rocketMQTemplate.sendAndReceive(destination, payload, type, hashKey, timeout, delayLevel);
    }

    public void sendMessageAndCallback(String destination, Message<?> message,
                                       RocketMQLocalRequestCallback rocketMQLocalRequestCallback) {
        rocketMQTemplate.sendAndReceive(destination, message, rocketMQLocalRequestCallback);
    }

    public void sendAndCallback(String destination, Object payload,
                                RocketMQLocalRequestCallback rocketMQLocalRequestCallback) {
        rocketMQTemplate.sendAndReceive(destination, payload, rocketMQLocalRequestCallback);
    }

    public void sendMessageAndCallbackTimeout(String destination, Message<?> message,
                                              RocketMQLocalRequestCallback rocketMQLocalRequestCallback, long timeout) {
        rocketMQTemplate.sendAndReceive(destination, message, rocketMQLocalRequestCallback, timeout);
    }

    public void sendAndCallbackTimeout(String destination, Object payload,
                                       RocketMQLocalRequestCallback rocketMQLocalRequestCallback, long timeout) {
        rocketMQTemplate.sendAndReceive(destination, payload, rocketMQLocalRequestCallback, timeout);
    }

    public void sendMessageAndCallbackTimeoutDelay(String destination, Message<?> message,
                                                   RocketMQLocalRequestCallback rocketMQLocalRequestCallback
            , long timeout, int delayLevel) {
        rocketMQTemplate.sendAndReceive(destination, message, rocketMQLocalRequestCallback, timeout, delayLevel);
    }

    public void sendAndCallbackTimeoutDelay(String destination, Object payload,
                                            RocketMQLocalRequestCallback rocketMQLocalRequestCallback
            , long timeout, int delayLevel) {
        rocketMQTemplate.sendAndReceive(destination, payload, rocketMQLocalRequestCallback, timeout, delayLevel);
    }

    public void sendAndMessageCallbackOrderly(String destination, Message<T> message,
                                              RocketMQLocalRequestCallback rocketMQLocalRequestCallback, String hashKey) {
        rocketMQTemplate.sendAndReceive(destination, message, rocketMQLocalRequestCallback, hashKey);
    }

    public void sendAndCallbackOrderly(String destination, Object payload,
                                       RocketMQLocalRequestCallback rocketMQLocalRequestCallback, String hashKey) {
        rocketMQTemplate.sendAndReceive(destination, payload, rocketMQLocalRequestCallback, hashKey);
    }

    public void sendAndMessageCallbackTimeoutOrderly(String destination, Message<T> message,
                                                     RocketMQLocalRequestCallback rocketMQLocalRequestCallback,
                                                     String hashKey, long timeout) {
        rocketMQTemplate.sendAndReceive(destination, message, rocketMQLocalRequestCallback, hashKey, timeout);
    }

    public void sendAndCallbackTimeoutOrderly(String destination, Object payload,
                                              RocketMQLocalRequestCallback rocketMQLocalRequestCallback,
                                              String hashKey, long timeout) {
        rocketMQTemplate.sendAndReceive(destination, payload, rocketMQLocalRequestCallback, hashKey, timeout);
    }

    public void sendMessageAndCallbackOrderly(String destination, Message<T> message,
                                              RocketMQLocalRequestCallback rocketMQLocalRequestCallback,
                                              String hashKey) {
        rocketMQTemplate.sendAndReceive(destination, message, rocketMQLocalRequestCallback, hashKey);
    }

    public void sendAndCallbackDelayOrderly(String destination, Object payload,
                                            RocketMQLocalRequestCallback rocketMQLocalRequestCallback, String hashKey, int delayLevel) {
        rocketMQTemplate.sendAndReceive(destination, payload, rocketMQLocalRequestCallback, hashKey, delayLevel);
    }

    public void sendAndCallbackTimeoutDelayOrderly(String destination, Object payload,
                                                   RocketMQLocalRequestCallback rocketMQLocalRequestCallback, String hashKey
            , long timeout, int delayLevel) {
        rocketMQTemplate.sendAndReceive(destination, payload, rocketMQLocalRequestCallback, hashKey, timeout, delayLevel);
    }

    public void sendMessageAndCallbackTimeoutDelayOrderly(String destination, Message<T> message,
                                                          RocketMQLocalRequestCallback rocketMQLocalRequestCallback, String hashKey
            , long timeout, int delayLevel) {
        rocketMQTemplate.sendAndReceive(destination, message, rocketMQLocalRequestCallback, hashKey, timeout, delayLevel);
    }
}
