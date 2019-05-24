package com.hzhang.mq.activemq.origin.queue;

import com.hzhang.mq.activemq.origin.Config;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProvider {
    public static void main(String []args){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://"+ Config.ACTIVE_MQ_IP+":"+Config.ACTIVE_MQ_PORT);
        Connection connection =null;
        try {
            connection=connectionFactory.createConnection();
            connection.start();
            Session session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination =session.createQueue("first-queue");
            MessageProducer producer= session.createProducer(destination);

            TextMessage message = session.createTextMessage("你好，hzhang");
            producer.send(message);
            System.out.println("成功发送消息");
            session.commit();
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
