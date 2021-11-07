package org.linkworld.ocean.system.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.mqtt.event.MqttSubscribedEvent;
import org.springframework.stereotype.Component;

/**
 * @author HALOXIAO
 * @since 2021-11-06 23:26
 **/
@Component
@Slf4j
public class MqttSubscribedEventListener implements ApplicationListener<MqttSubscribedEvent> {

    @Override
    public void onApplicationEvent(MqttSubscribedEvent event) {
        log.info("Mqtt Event status:{}", event.getMessage());

    }
}
