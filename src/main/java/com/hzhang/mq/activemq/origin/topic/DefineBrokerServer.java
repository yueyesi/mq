package com.hzhang.mq.activemq.origin.topic;

import org.apache.activemq.broker.BrokerService;

public class DefineBrokerServer {
    public static void main(String[] args) {
        BrokerService brokerService= new BrokerService();
        try{
            brokerService.setUseJmx(true);
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
