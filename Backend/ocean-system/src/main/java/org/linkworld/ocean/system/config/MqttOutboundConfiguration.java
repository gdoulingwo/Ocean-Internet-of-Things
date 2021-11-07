package org.linkworld.ocean.system.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * MQTT数据出站
 *
 * @author HALOXIAO
 * @since 2021-11-07 00:20
 **/
@Configuration
public class MqttOutboundConfiguration {


    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{"tcp://broker.emqx.io"});
//        options.setUserName("username");
//        options.setPassword("password".toCharArray());

        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean("MqttOutboundChannel")
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }


    @Bean
    @ServiceActivator(inputChannel = "MqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler("testClient", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("testTopic");

        return messageHandler;
    }


}
