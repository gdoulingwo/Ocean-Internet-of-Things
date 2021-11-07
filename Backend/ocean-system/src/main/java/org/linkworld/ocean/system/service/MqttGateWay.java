package org.linkworld.ocean.system.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * MQTT sender
 *
 * @author HALOXIAO
 * @since 2021-11-07 00:50
 **/
@MessagingGateway(defaultRequestChannel = "MqttOutboundChannel")
@Component
public interface MqttGateWay {

    void sendToMqtt(String data);

    void sendToTopic(String payload, @Header(MqttHeaders.TOPIC) String topic);

    void sendToTopic(String payload, @Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos);

}
