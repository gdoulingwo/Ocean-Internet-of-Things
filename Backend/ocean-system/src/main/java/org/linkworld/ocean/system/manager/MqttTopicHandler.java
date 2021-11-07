package org.linkworld.ocean.system.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Component;

/**
 * @author HALOXIAO
 * @since 2021-11-07 00:39
 **/
@Component
public class MqttTopicHandler {

    @Autowired
    private MqttPahoMessageDrivenChannelAdapter channelAdapter;

    public void removeTopic(String... topics) {
        channelAdapter.removeTopic();
    }


    public void addTopic(String topic) {
        channelAdapter.addTopic(topic, 1);
    }


    public void addTopic(String topic, int qos) {
        channelAdapter.addTopic(topic, qos);
    }

}
