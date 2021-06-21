package com.unufolio.fondo.modules.mq.amqp;

import com.unufolio.fondo.utils.ThreadPoolUtils;
import org.apache.qpid.jms.JmsConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Base64;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/03/28
 */
public class AmqpConsumer {

    private final static Logger logger = LoggerFactory.getLogger(AmqpConsumer.class);

    private final static String ACCESS_KEY = "";
    private final static String SECRETE_KEY = "";
    private final static String CONSUMER_GROUP_ID = "";
    private final static String IOT_INSTANCE_ID = "";
    private final static String CLIENT_ID = "";

    // 业务处理异步线程池，线程池参数可以根据您的业务特点调整，或者您也可以用其他异步方式处理接收到的消息。
    private final static ExecutorService executorService = ThreadPoolUtils.getExecutorService();

    @EventListener(ApplicationReadyEvent.class)
    public void execute() {
        try {
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() throws Exception {
        String accessKey = ACCESS_KEY;
        String accessSecret = SECRETE_KEY;
        String consumerGroupId = CONSUMER_GROUP_ID;
        String iotInstanceId = IOT_INSTANCE_ID;
        long timeStamp = System.currentTimeMillis();
        // 签名方法：支持 hmacmd5，hmacsha1 和 hmacsha256。
        String signMethod = "hmacsha1";
        // 控制台服务端订阅中消费组状态页客户端ID一栏将显示clientId参数。
        // 建议使用机器 UUID、MAC 地址、IP 等唯一标识等作为 clientId。便于您区分识别不同的客户端。
        String clientId = CLIENT_ID;

        //UserName组装方法，请参见文档：AMQP客户端接入说明。
        String userName = clientId + "|authMode=aksign"
                + ",signMethod=" + signMethod
                + ",timestamp=" + timeStamp
                + ",authId=" + accessKey
                + ",iotInstanceId=" + iotInstanceId
                + ",consumerGroupId=" + consumerGroupId
                + "|";
        //password组装方法，请参见文档：AMQP客户端接入说明。
        String signContent = "authId=" + accessKey + "&timestamp=" + timeStamp;
        String password = doSign(signContent, accessSecret, signMethod);
        //按照qpid-jms的规范，组装连接URL。
        String connectionUrl = "failover:(amqps://${uid}.iot-amqp.${regionId}.aliyuncs.com:5671?amqp.idleTimeout=80000)"
                + "?failover.reconnectDelay=30";

        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("connectionfactory.SBCF", connectionUrl);
        hashtable.put("queue.QUEUE", "default");
        hashtable.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
        Context context = new InitialContext(hashtable);
        ConnectionFactory cf = (ConnectionFactory) context.lookup("SBCF");
        Destination queue = (Destination) context.lookup("QUEUE");
        // Create Connection
        Connection connection = cf.createConnection(userName, password);
        ((JmsConnection) connection).addConnectionListener(new JmsConnectionListenerImpl());
        // Create Session
        // Session.CLIENT_ACKNOWLEDGE: 收到消息后，需要手动调用message.acknowledge()。
        // Session.AUTO_ACKNOWLEDGE: SDK自动ACK（推荐）。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        // Create Receiver Link
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(MESSAGE_LISTENER);
    }

    private static final MessageListener MESSAGE_LISTENER = message -> {
        try {
            //1.收到消息之后一定要ACK。
            // 推荐做法：创建Session选择Session.AUTO_ACKNOWLEDGE，这里会自动ACK。
            // 其他做法：创建Session选择Session.CLIENT_ACKNOWLEDGE，这里一定要调message.acknowledge()来ACK。
            // message.acknowledge();
            //2.建议异步处理收到的消息，确保onMessage函数里没有耗时逻辑。
            // 如果业务处理耗时过程过长阻塞住线程，可能会影响SDK收到消息后的正常回调。
            executorService.submit(() -> processMessage(message));
        } catch (Exception e) {
            logger.error("submit task occurs exception ", e);
        }
    };

    /**
     * 在这里处理您收到消息后的具体业务逻辑。
     */
    private static void processMessage(Message message) {
        try {
            byte[] body = message.getBody(byte[].class);
            String content = new String(body);
            String topic = message.getStringProperty("topic");
            String messageId = message.getStringProperty("messageId");
            logger.info("receive message"
                    + ", topic = " + topic
                    + ", messageId = " + messageId
                    + ", content = " + content);
        } catch (Exception e) {
            logger.error("processMessage occurs error ", e);
        }
    }

    /**
     * password 签名计算方法，请参见文档：AMQP客户端接入说明。
     */
    private static String doSign(String toSignString, String secret, String signMethod) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(), signMethod);
        Mac mac = Mac.getInstance(signMethod);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(toSignString.getBytes());
        return Base64.getEncoder().encodeToString(rawHmac);
    }
}
