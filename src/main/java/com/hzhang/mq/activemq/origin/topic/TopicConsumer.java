package com.hzhang.mq.activemq.origin.topic;

import com.hzhang.mq.activemq.origin.Config;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+ Config.ACTIVE_MQ_IP+":"+Config.ACTIVE_MQ_PORT);
        Connection connection =null;
        try {
            connection=connectionFactory.createConnection();
            connection.setClientID("mqclient-3");
            connection.start();
            Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("first-topic");
            // 持久化订阅
            TopicSubscriber consumer=session.createDurableSubscriber(topic,"mqclient-3");
            TextMessage message= (TextMessage) consumer.receive();
            System.out.println("接收到消息: "+message.getText());
//            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
