package org.linkworld.ocean.system.common;

import org.linkworld.ocean.system.persist.dto.MessageHeaderDTO;
import org.springframework.messaging.MessageHeaders;

/**
 * @author HALOXIAO
 * @since 2021-11-07 15:11
 **/
public class MqttHeaderSupport {


    public static MessageHeaderDTO resolveHeader(MessageHeaders headers) {
        MessageHeaderDTO dto = new MessageHeaderDTO();
        dto.setTopic(headers.get("mqtt_receivedTopic", String.class));
        dto.setQos(headers.get("mqtt_receivedQos", Integer.class));
        dto.setDuplicate(headers.get("mqtt_duplicate", Boolean.class));
        dto.setTimestamp(headers.getTimestamp());
        dto.setReceivedRetained(headers.get("mqtt_receivedRetained", Boolean.class));
        return dto;
    }

}
